package org.m0skit0.android.mapswrapper

class Polygon(
    internal val google: com.google.android.gms.maps.model.Polygon?,
    internal val huawei: com.huawei.hms.maps.model.Polygon?
) {

    var strokeColor: Int
        get() = google?.strokeColor ?: huawei?.strokeColor ?: throwUnableToResolveGoogleOrHuawei()
        set(value) {
            google?.strokeColor = value
            huawei?.strokeColor = value
        }

    var strokeJointType: Int
        get() = google?.strokeJointType ?: huawei?.strokeJointType ?: throwUnableToResolveGoogleOrHuawei()
        set(value) {
            google?.strokeJointType = value
            huawei?.strokeJointType = value
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

    var fillColor: Int
        get() = google?.fillColor ?: huawei?.fillColor ?: throwUnableToResolveGoogleOrHuawei()
        set(value) {
            google?.fillColor = value
            huawei?.fillColor = value
        }

    var strokeWidth: Float
        get() = google?.strokeWidth ?: huawei?.strokeWidth ?: throwUnableToResolveGoogleOrHuawei()
        set(value) {
            google?.strokeWidth = value
            huawei?.strokeWidth = value
        }

    var tag: Any?
        get() = google?.tag ?: huawei?.tag
        set(value) {
            google?.tag = value
            huawei?.tag = value
        }
}
