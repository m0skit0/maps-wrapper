package org.m0skit0.android.mapswrapper

data class LatLng(val latitude: Double, val longitude: Double) {
    internal val google by lazy { com.google.android.gms.maps.model.LatLng(latitude, longitude) }
    internal val huawei by lazy { com.huawei.hms.maps.model.LatLng(latitude, longitude) }
}
