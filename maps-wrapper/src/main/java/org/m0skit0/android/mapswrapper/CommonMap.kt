package org.m0skit0.android.mapswrapper

import com.google.android.gms.maps.GoogleMap
import com.huawei.hms.maps.HuaweiMap

class CommonMap(private val map: Any) {

    private val googleMap: GoogleMap
        get() = map as GoogleMap

    private val huaweiMap: HuaweiMap
        get() = map as HuaweiMap

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
}
