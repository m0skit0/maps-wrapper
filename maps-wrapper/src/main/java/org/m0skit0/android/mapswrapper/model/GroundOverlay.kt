package org.m0skit0.android.mapswrapper.model

import org.m0skit0.android.mapswrapper.throwUnableToResolveGoogleOrHuawei

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

    var zIndex: Float
        get() = google?.zIndex ?: huawei?.zIndex ?: throwUnableToResolveGoogleOrHuawei()
        set(value) {
            google?.zIndex = value
            huawei?.zIndex = value
        }

    var isVisible: Boolean
        get() = google?.isVisible ?: huawei?.isVisible ?: throwUnableToResolveGoogleOrHuawei()
        set(value) {
            google?.isVisible = value
            huawei?.isVisible = value
        }

    fun remove() {
        google?.remove()
        huawei?.remove()
    }
}
