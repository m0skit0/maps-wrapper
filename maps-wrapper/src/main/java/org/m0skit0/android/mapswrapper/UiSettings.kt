package org.m0skit0.android.mapswrapper

class UiSettings(
    internal var google: com.google.android.gms.maps.UiSettings?,
    internal var huawei: com.huawei.hms.maps.UiSettings?
) {

    var isZoomControlsEnabled: Boolean
        get() = google?.isZoomControlsEnabled ?: huawei?.isZoomControlsEnabled ?: throwUnableToResolveGoogleOrHuawei()
        set(value) {
            google?.isZoomControlsEnabled = value
            huawei?.isZoomControlsEnabled = value
        }

    var isMyLocationButtonEnabled: Boolean
        get() = google?.isMyLocationButtonEnabled ?: huawei?.isMyLocationButtonEnabled ?: throwUnableToResolveGoogleOrHuawei()
        set(value) {
            google?.isMyLocationButtonEnabled = value
            huawei?.isMyLocationButtonEnabled = value
        }

    var isMapToolbarEnabled: Boolean
        get() = google?.isMapToolbarEnabled ?: huawei?.isMapToolbarEnabled ?: throwUnableToResolveGoogleOrHuawei()
        set(value) {
            google?.isMapToolbarEnabled = value
            huawei?.isMapToolbarEnabled = value
        }

    var isScrollGesturesEnabled: Boolean
        get() = google?.isScrollGesturesEnabled ?: huawei?.isScrollGesturesEnabled ?: throwUnableToResolveGoogleOrHuawei()
        set(value) {
            google?.isScrollGesturesEnabled = value
            huawei?.isScrollGesturesEnabled = value
        }

    var isZoomGesturesEnabled: Boolean
        get() = google?.isZoomGesturesEnabled ?: huawei?.isZoomGesturesEnabled ?: throwUnableToResolveGoogleOrHuawei()
        set(value) {
            google?.isZoomGesturesEnabled = value
            huawei?.isZoomGesturesEnabled = value
        }

    var isTiltGesturesEnabled: Boolean
        get() = google?.isTiltGesturesEnabled ?: huawei?.isTiltGesturesEnabled ?: throwUnableToResolveGoogleOrHuawei()
        set(value) {
            google?.isTiltGesturesEnabled = value
            huawei?.isTiltGesturesEnabled = value
        }

    var isRotateGesturesEnabled: Boolean
        get() = google?.isRotateGesturesEnabled ?: huawei?.isRotateGesturesEnabled ?: throwUnableToResolveGoogleOrHuawei()
        set(value) {
            google?.isRotateGesturesEnabled = value
            huawei?.isRotateGesturesEnabled = value
        }
}
