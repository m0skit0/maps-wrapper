package org.m0skit0.android.mapswrapper.model

class GroundOverlay(
    internal val google: com.google.android.gms.maps.model.GroundOverlay?,
    internal val huawei: com.huawei.hms.maps.model.GroundOverlay?
) {

    internal constructor(google: com.google.android.gms.maps.model.GroundOverlay?) : this(google, null)
    internal constructor(huawei: com.huawei.hms.maps.model.GroundOverlay?) : this(null, huawei)

    var tag: Any?
        get() = google?.tag ?: huawei?.tag
        set(value) {
            google?.tag = value
            huawei?.tag = value
        }
}
