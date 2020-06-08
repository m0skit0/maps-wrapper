package org.m0skit0.android.mapswrapper

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class MapView : FrameLayout {

    private lateinit var mapView: FrameLayout

    constructor(context: Context): super(context) {
        resolveMapView(context, null, 0, 0)
        addView(mapView)
    }

    constructor(context: Context, attr: AttributeSet?): super(context, attr) {
        resolveMapView(context, attr, 0, 0)
        addView(mapView)
    }

    constructor(context: Context, attr: AttributeSet?, @AttrRes defStyleAttr: Int): super(context, attr, defStyleAttr) {
        resolveMapView(context, attr, defStyleAttr, 0)
        addView(mapView)
    }

    constructor(context: Context, attr: AttributeSet?, @AttrRes defStyleAttr: Int, @StyleRes defStyleRes: Int): super(context, attr, defStyleAttr, defStyleRes) {
        resolveMapView(context, attr, defStyleAttr, defStyleRes)
        addView(mapView)
    }

    private fun resolveMapView(context: Context, attr: AttributeSet?, @AttrRes defStyleAttr: Int, @StyleRes defStyleRes: Int) {
        context.theme.obtainStyledAttributes(
            attr,
            R.styleable.mapResolution,
            defStyleAttr,
            defStyleRes
        ).apply {
                val strategy = getText(R.styleable.mapResolution_type)
                    ?.let { MapResolverStrategy.fromValue(it.toString())  }
                    ?: MapResolverStrategy.GOOGLE_THEN_HUAWEI
                mapView = mapViewFromResolverType(context, attr, defStyleAttr, strategy)
        }.recycle()
    }

    private fun isGoogleMap(): Boolean = mapView is com.google.android.gms.maps.MapView

    private fun googleMap(): com.google.android.gms.maps.MapView = mapView as com.google.android.gms.maps.MapView

    private fun isHuaweiMap(): Boolean = mapView is com.huawei.hms.maps.MapView

    private fun huaweiMap(): com.google.android.gms.maps.MapView = mapView as com.google.android.gms.maps.MapView

    fun getMapAsync(callback: OnMapReadyCallback) {
        when {
            isGoogleMap() -> googleGetMapAsync(callback)
            isHuaweiMap() -> huaweiGetMapAsync(callback)
        }
    }

    fun getMapAsync(callback: (CommonMap) -> Unit) {
        val onMapReadyCallback = object : OnMapReadyCallback {
            override fun onMapReady(map: CommonMap) {
                callback(map)
            }
        }
        getMapAsync(onMapReadyCallback)
    }

    suspend fun mapAsync(): CommonMap =
        suspendCoroutine { continuation ->
            object : OnMapReadyCallback {
                override fun onMapReady(map: CommonMap) {
                    continuation.resume(map)
                }
            }.let { callback -> getMapAsync(callback) }
        }

    private fun googleGetMapAsync(callback: OnMapReadyCallback) {
        googleMap().getMapAsync { CommonMap(it).let { commonMap -> callback.onMapReady(commonMap) } }
    }

    private fun huaweiGetMapAsync(callback: OnMapReadyCallback) {
        huaweiMap().getMapAsync { CommonMap(it).let { commonMap -> callback.onMapReady(commonMap) } }
    }

    fun onCreate(bundle: Bundle?) {
        when {
            isGoogleMap() -> googleMap().onCreate(bundle)
            isHuaweiMap() -> huaweiMap().onCreate(bundle)
        }
    }

    fun onResume() {
        when {
            isGoogleMap() -> googleMap().onResume()
            isHuaweiMap() -> huaweiMap().onResume()
        }
    }

    fun onPause() {
        when {
            isGoogleMap() -> googleMap().onPause()
            isHuaweiMap() -> huaweiMap().onPause()
        }
    }

    fun onStart() {
        when {
            isGoogleMap() -> googleMap().onStart()
            isHuaweiMap() -> huaweiMap().onStart()
        }
    }

    fun onStop() {
        when {
            isGoogleMap() -> googleMap().onStop()
            isHuaweiMap() -> huaweiMap().onStop()
        }
    }

    fun onDestroy() {
        when {
            isGoogleMap() -> googleMap().onDestroy()
            isHuaweiMap() -> huaweiMap().onDestroy()
        }
    }

    fun onLowMemory() {
        when {
            isGoogleMap() -> googleMap().onLowMemory()
            isHuaweiMap() -> huaweiMap().onLowMemory()
        }
    }

    fun onSaveInstanceState(bundle: Bundle?) {
        when {
            isGoogleMap() -> googleMap().onSaveInstanceState(bundle)
            isHuaweiMap() -> huaweiMap().onSaveInstanceState(bundle)
        }
    }
}
