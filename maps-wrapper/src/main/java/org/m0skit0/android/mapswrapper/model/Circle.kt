package org.m0skit0.android.mapswrapper.model

import org.m0skit0.android.mapswrapper.throwUnableToResolveGoogleOrHuawei

class Circle internal constructor(
    internal val google: com.google.android.gms.maps.model.Circle?,
    internal val huawei: com.huawei.hms.maps.model.Circle?
) {

    var tag: Any?
        get() = google?.tag ?: huawei?.tag
        set(value) {
            google?.tag = value
            huawei?.setTag(value)
        }

    var center: LatLng
        get() = google?.center?.let { LatLng(it.latitude, it.longitude) }
            ?: huawei?.center?.let { LatLng(it.latitude, it.longitude) }
            ?: throwUnableToResolveGoogleOrHuawei()
        set(value) {
            google?.center = value.google
            huawei?.center = value.huawei
        }

    var radius: Double
        get() = google?.radius ?: huawei?.radius ?: throwUnableToResolveGoogleOrHuawei()
        set(value) {
            google?.radius = value
            huawei?.radius = value
        }

    var strokeWidth: Float
        get() = google?.strokeWidth ?: huawei?.strokeWidth ?: throwUnableToResolveGoogleOrHuawei()
        set(value) {
            google?.strokeWidth = value
            huawei?.strokeWidth = value
        }

    var strokeColor: Int
        get() = google?.strokeColor ?: huawei?.strokeColor ?: throwUnableToResolveGoogleOrHuawei()
        set(value) {
            google?.strokeColor = value
            huawei?.strokeColor = value
        }

    var fillColor: Int
        get() = google?.fillColor ?: huawei?.fillColor ?: throwUnableToResolveGoogleOrHuawei()
        set(value) {
            google?.fillColor = value
            huawei?.fillColor = value
        }

    var strokePattern: List<PatternItem>?
        get() = google?.strokePattern?.map { PatternItem(it, null) }
            ?: huawei?.strokePattern?.map { PatternItem(null, it) }
        set(value) {
            google?.strokePattern = value?.map { it.google }
            huawei?.strokePattern = value?.map { it.huawei }
        }

    var isClickable: Boolean
        get() = google?.isClickable ?: huawei?.isClickable ?: throwUnableToResolveGoogleOrHuawei()
        set(value) {
            google?.isClickable = value
            huawei?.isClickable = value
        }
}
