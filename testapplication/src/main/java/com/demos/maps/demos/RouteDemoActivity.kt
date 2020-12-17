package com.demos.maps.demos

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.demos.maps.R
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.*
import org.m0skit0.android.mapswrapper.CameraUpdateFactory
import org.m0skit0.android.mapswrapper.CommonMap
import org.m0skit0.android.mapswrapper.SupportMapFragment
import org.m0skit0.android.mapswrapper.model.LatLng
import org.m0skit0.android.mapswrapper.model.MarkerOptions
import org.m0skit0.android.mapswrapper.model.PolylineOptions
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.io.IOException

class RouteDemoActivity : AppCompatActivity() {

    data class Directions(
        @SerializedName("geocoded_waypoints")
        val geocodedWaypoints: List<GeocodedWaypoint> = listOf(),
        @SerializedName("routes")
        val routes: List<Route> = listOf(),
        @SerializedName("status")
        val status: String = ""
    )

    data class GeocodedWaypoint(
        @SerializedName("geocoder_status")
        val geocoderStatus: String = "",
        @SerializedName("place_id")
        val placeId: String = "",
        @SerializedName("types")
        val types: List<String> = listOf()
    )

    data class Route(
        @SerializedName("bounds")
        val bounds: Bounds = Bounds(),
        @SerializedName("copyrights")
        val copyrights: String = "",
        @SerializedName("legs")
        val legs: List<Leg> = listOf(),
        @SerializedName("overview_polyline")
        val overviewPolyline: OverviewPolyline = OverviewPolyline(),
        @SerializedName("summary")
        val summary: String = "",
        @SerializedName("warnings")
        val warnings: List<Any> = listOf(),
        @SerializedName("waypoint_order")
        val waypointOrder: List<Int> = listOf()
    )

    data class Bounds(
        @SerializedName("northeast")
        val northeast: Location = Location(),
        @SerializedName("southwest")
        val southwest: Location = Location()
    )

    data class TextValue(
        @SerializedName("text")
        val text: String = "",
        @SerializedName("value")
        val value: Int = 0
    )

    data class Location(
        @SerializedName("lat")
        val lat: Double = 0.0,
        @SerializedName("lng")
        val lng: Double = 0.0
    )

    data class Leg(
        @SerializedName("distance")
        val distance: TextValue = TextValue(),
        @SerializedName("duration")
        val duration: TextValue = TextValue(),
        @SerializedName("end_address")
        val endAddress: String = "",
        @SerializedName("end_location")
        val endLocation: Location = Location(),
        @SerializedName("start_address")
        val startAddress: String = "",
        @SerializedName("start_location")
        val startLocation: Location = Location(),
        @SerializedName("steps")
        val steps: List<Step> = listOf(),
        @SerializedName("traffic_speed_entry")
        val trafficSpeedEntry: List<Any> = listOf(),
        @SerializedName("via_waypoint")
        val viaWaypoint: List<Any> = listOf()
    )

    data class OverviewPolyline(
        @SerializedName("points")
        val points: String = ""
    )

    data class Polyline(
        @SerializedName("points")
        val points: String = ""
    )

    data class Step(
        @SerializedName("distance")
        val distance: TextValue = TextValue(),
        @SerializedName("duration")
        val duration: TextValue = TextValue(),
        @SerializedName("end_location")
        val endLocation: Location = Location(),
        @SerializedName("html_instructions")
        val htmlInstructions: String = "",
        @SerializedName("maneuver")
        val maneuver: String = "",
        @SerializedName("polyline")
        val polyline: Polyline = Polyline(),
        @SerializedName("start_location")
        val startLocation: Location = Location(),
        @SerializedName("travel_mode")
        val travelMode: String = ""
    )

    interface DirectionsApi {
        @GET("json")
        fun getDirections(
            @Query("origin") origin: String,
            @Query("destination") destination: String,
            @Query("key") key: String
        ): Call<Directions>
    }

    private val BASE_URL = "https://maps.googleapis.com/maps/api/directions/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private var map: CommonMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_route_demo)

        (supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment).getMapAsync {
            map = it
        }

        GlobalScope.launch(Dispatchers.Main) {
            askDirectionsAsync().await()
                ?.setMarkers()
                ?.drawRoute() ?: toast(R.string.error)
        }
    }

    private fun askDirectionsAsync(): Deferred<Directions?> =
        GlobalScope.async(Dispatchers.IO) {
            try {
                retrofit.create(DirectionsApi::class.java)
                    .getDirections("Toronto", "Montreal", getString(R.string.google_maps_key))
                    .execute()
                    .run {
                        if (isSuccessful) {
                            body()
                        } else {
                            logError(errorBody()?.string())
                            null
                        }
                    }
            } catch (e: IOException) {
                logError(e.message, e)
                null
            }
        }

    private fun Directions.setMarkers(): Directions = apply {
        routes[0].legs[0].run {
            val startLocation = LatLng(startLocation.lat, startLocation.lng)
            val startMarker = MarkerOptions().position(startLocation)
            map?.addMarker(startMarker)

            val endLocation = LatLng(endLocation.lat, endLocation.lng)
            val endMarker = MarkerOptions().position(endLocation)
            map?.addMarker(endMarker)

            val cameraUpdate = CameraUpdateFactory.newLatLngZoom(startLocation, 5f)
            map?.moveCamera(cameraUpdate)
        }
    }

    private fun Directions.drawRoute(): Directions = apply {
        val polylineOptions = PolylineOptions()
            .geodesic(true)
            .color(resources.getColor(R.color.colorPrimary))
            .addAll(routes[0].overviewPolyline.points.decodePath())
        map?.addPolyline(polylineOptions)
    }

    private fun String.decodePath(): List<LatLng> {
        val len = length
        // For speed we preallocate to an upper bound on the final length, then
        // truncate the array before returning.
        val path: MutableList<LatLng> = mutableListOf()
        var index = 0
        var lat = 0
        var lng = 0
        while (index < len) {
            var result = 1
            var shift = 0
            var b: Int
            do {
                b = this[index++].toInt() - 63 - 1
                result += b shl shift
                shift += 5
            } while (b >= 0x1f)
            lat += if (result and 1 != 0) (result shr 1).inv() else result shr 1
            result = 1
            shift = 0
            do {
                b = this[index++].toInt() - 63 - 1
                result += b shl shift
                shift += 5
            } while (b >= 0x1f)
            lng += if (result and 1 != 0) (result shr 1).inv() else result shr 1
            path.add(LatLng(lat * 1e-5, lng * 1e-5))
        }
        return path
    }

    private fun toast(@StringRes id: Int) {
        Toast.makeText(this, id, Toast.LENGTH_LONG).show()
    }

    private fun logError(message: String?, error: Throwable? = null) {
        error?.run {
            Log.e(javaClass.simpleName, message, error)
        } ?: Log.e(javaClass.simpleName, message ?: "")
    }
}
