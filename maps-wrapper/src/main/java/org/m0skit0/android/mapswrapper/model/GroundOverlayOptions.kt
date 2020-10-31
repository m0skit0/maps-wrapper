package org.m0skit0.android.mapswrapper.model

class GroundOverlayOptions {

    internal var google = com.google.android.gms.maps.model.GroundOverlayOptions()
    internal var huawei = com.huawei.hms.maps.model.GroundOverlayOptions()

    fun image(bitmapDescriptor: BitmapDescriptor): GroundOverlayOptions = apply {
        bitmapDescriptor.google?.run { google = google.image(this) }
        bitmapDescriptor.huawei?.run { huawei = huawei.image(this) }
    }

    fun position(position: LatLng, length: Float): GroundOverlayOptions = apply {
        google = google.position(position.google, length)
        huawei = huawei.position(position.huawei, length)
    }

    fun clickable(clickable: Boolean): GroundOverlayOptions = apply {
        google = google.clickable(clickable)
        huawei = huawei.clickable(clickable)
    }
}
