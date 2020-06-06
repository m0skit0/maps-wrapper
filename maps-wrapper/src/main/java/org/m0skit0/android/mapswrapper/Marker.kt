package org.m0skit0.android.mapswrapper

class Marker internal constructor(
    internal val google: com.google.android.gms.maps.model.Marker?,
    internal val huawei: com.huawei.hms.maps.model.Marker?
) {

    var position: LatLng
        get() = google?.position?.let { LatLng(it.latitude, it.longitude) }
            ?: huawei?.position?.let { LatLng(it.latitude, it.longitude) } ?: LatLng(0.0, 0.0)
        set(value) {
            google?.position = value.google
            huawei?.position = value.huawei
        }
}
