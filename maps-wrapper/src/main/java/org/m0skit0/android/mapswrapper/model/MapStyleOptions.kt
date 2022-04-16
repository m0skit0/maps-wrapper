package org.m0skit0.android.mapswrapper.model

class MapStyleOptions(json: String) {
    internal var google = com.google.android.gms.maps.model.MapStyleOptions(json)
    internal var huawei = com.huawei.hms.maps.model.MapStyleOptions(json)
}
