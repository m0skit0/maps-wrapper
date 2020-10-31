package org.m0skit0.android.mapswrapper.model

import org.m0skit0.android.mapswrapper.throwUnableToResolveGoogleOrHuawei

class Polyline internal constructor(
    internal val google: com.google.android.gms.maps.model.Polyline?,
    internal val huawei: com.huawei.hms.maps.model.Polyline?
) {

    var color: Int
        get() = google?.color ?: huawei?.color ?: throwUnableToResolveGoogleOrHuawei()
        set(value) {
            google?.color = value
            huawei?.color = value
        }

    var startCap: Cap
        get() = google?.startCap?.let { Cap(it, null) }
                ?: huawei?.startCap?.let { Cap(null, it) }
                ?: throwUnableToResolveGoogleOrHuawei()
        set(value) {
            value.google?.run { google?.startCap = this }
            huawei?.startCap = value.huawei
        }

    var endCap: Cap
        get() = google?.endCap?.let { Cap(it, null) }
            ?: huawei?.endCap?.let { Cap(null, it) }
            ?: throwUnableToResolveGoogleOrHuawei()
        set(value) {
            value.google?.run { google?.endCap = this }
            huawei?.endCap = value.huawei
        }

    var jointType: Int
        get() = google?.jointType ?: huawei?.jointType ?: throwUnableToResolveGoogleOrHuawei()
        set(value) {
            google?.jointType = value
            huawei?.jointType = value
        }

    var pattern: List<PatternItem>?
        get() = google?.pattern?.map { PatternItem(it, null) }
            ?: huawei?.pattern?.map { PatternItem(null, it) }
        set(value) {
            google?.pattern = value?.map { it.google }
            huawei?.pattern = value?.map { it.huawei }
        }

    var isClickable: Boolean
        get() = google?.isClickable ?: huawei?.isClickable ?: throwUnableToResolveGoogleOrHuawei()
        set(value) {
            google?.isClickable = value
            huawei?.isClickable = value
        }

    var width: Float
        get() = google?.width ?: huawei?.width ?: throwUnableToResolveGoogleOrHuawei()
        set(value) {
            google?.width = value
            huawei?.width = value
        }

    var tag: Any?
        get() = google?.tag ?: huawei?.tag
        set(value) {
            google?.tag = value
            huawei?.tag = value
        }
}
