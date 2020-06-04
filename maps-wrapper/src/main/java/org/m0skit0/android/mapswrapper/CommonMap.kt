package org.m0skit0.android.mapswrapper

import com.google.android.gms.maps.GoogleMap
import com.huawei.hms.maps.HuaweiMap

class CommonMap(private val map: Any) {

    private val googleMap: GoogleMap
        get() = map as GoogleMap

    private val huaweiMap: HuaweiMap
        get() = map as HuaweiMap

    private val notGoogleNotHuaweiMap: IllegalStateException
        get() = IllegalStateException("Map is neither Google nor Huawei")

    private fun isGoogle(): Boolean = map is GoogleMap

    private fun isHuawei(): Boolean = map is HuaweiMap

    fun clear() {
        when {
            isGoogle() -> googleMap.clear()
            isHuawei() -> huaweiMap.clear()
        }
    }

    fun moveCamera(cameraUpdate: CameraUpdate) {
        when {
            isGoogle() -> googleMap.moveCamera(cameraUpdate.googleCameraUpdate)
            isHuawei() -> huaweiMap.moveCamera(cameraUpdate.huaweiCameraUpdate)
        }
    }

    fun animateCamera(cameraUpdate: CameraUpdate) {
        when {
            isGoogle() -> googleMap.animateCamera(cameraUpdate.googleCameraUpdate)
            isHuawei() -> huaweiMap.animateCamera(cameraUpdate.huaweiCameraUpdate)
        }
    }

    fun getMapType(): Int {
        return when {
            isGoogle() -> googleMap.mapType
            isHuawei() -> huaweiMap.mapType
            else -> -1
        }
    }

    fun isMyLocationEnabled(): Boolean {
        return when {
            isGoogle() -> googleMap.isMyLocationEnabled
            isHuawei() -> huaweiMap.isMyLocationEnabled
            else -> false
        }
    }

    fun setMyLocationEnabled(enabled: Boolean) {
        when {
            isGoogle() -> googleMap.isMyLocationEnabled = enabled
            isHuawei() -> huaweiMap.isMyLocationEnabled = enabled
        }
    }

    fun getCameraPosition(): CameraPosition =
        when {
            isGoogle() -> googleMap.cameraPosition.let { CameraPosition(it, null) }
            isHuawei() -> huaweiMap.cameraPosition.let { CameraPosition(null, it) }
            else -> throw notGoogleNotHuaweiMap
        }

    fun addCircle(circleOptions: CircleOptions): Circle =
        when {
            isGoogle() -> googleMap.addCircle(circleOptions.google).let { Circle(it, null) }
            isHuawei() -> huaweiMap.addCircle(circleOptions.huawei).let { Circle(null, it) }
            else -> throw notGoogleNotHuaweiMap
        }

    fun addMarker(markerOptions: MarkerOptions): Marker =
        when {
            isGoogle() -> googleMap.addMarker(markerOptions.google).let { Marker(it, null) }
            isHuawei() -> huaweiMap.addMarker(markerOptions.huawei).let { Marker(null, it) }
            else -> throw notGoogleNotHuaweiMap
        }

    fun addPolyline(polylineOptions: PolylineOptions): Polyline =
        when {
            isGoogle() -> googleMap.addPolyline(polylineOptions.google).let { Polyline(it, null) }
            isHuawei() -> huaweiMap.addPolyline(polylineOptions.huawei).let { Polyline(null, it) }
            else -> throw notGoogleNotHuaweiMap
        }
}
