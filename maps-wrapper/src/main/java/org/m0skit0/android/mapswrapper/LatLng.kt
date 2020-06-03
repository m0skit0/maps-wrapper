package org.m0skit0.android.mapswrapper

class LatLng(latitude: Double, longitude: Double) {
    internal val google = com.google.android.gms.maps.model.LatLng(latitude, longitude)
    internal val huawei = com.huawei.hms.maps.model.LatLng(latitude, longitude)
}
