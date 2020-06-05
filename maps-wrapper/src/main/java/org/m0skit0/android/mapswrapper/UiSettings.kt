package org.m0skit0.android.mapswrapper

class UiSettings(
    internal var google: com.google.android.gms.maps.UiSettings?,
    internal var huawei: com.huawei.hms.maps.UiSettings?
) {

    var isZoomControlsEnabled: Boolean
        get() = google?.isZoomControlsEnabled ?: huawei?.isZoomControlsEnabled ?: false
        set(value) {
            google = google?.apply {
                isZoomControlsEnabled = value
            }
            huawei = huawei?.apply {
                isZoomControlsEnabled = value
            }
        }

    var isMyLocationButtonEnabled: Boolean
        get() = google?.isMyLocationButtonEnabled ?: huawei?.isMyLocationButtonEnabled ?: false
        set(value) {
            google = google?.apply {
                isMyLocationButtonEnabled = value
            }
            huawei = huawei?.apply {
                isMyLocationButtonEnabled = value
            }
        }
}
