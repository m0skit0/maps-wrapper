package org.m0skit0.android.mapswrapper

class GroundOverlay(
    internal val google: com.google.android.gms.maps.model.GroundOverlay?,
    internal val huawei: com.huawei.hms.maps.model.GroundOverlay?
) {

    var tag: Any?
        get() = google?.tag ?: huawei?.tag
        set(value) {
            google?.tag = value
            huawei?.tag = value
        }
}
