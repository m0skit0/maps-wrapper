package org.m0skit0.android.mapswrapper.model

import org.m0skit0.android.mapswrapper.asWrapper

class GroundOverlayOptions {

    internal var google = com.google.android.gms.maps.model.GroundOverlayOptions()
    internal var huawei = com.huawei.hms.maps.model.GroundOverlayOptions()

    val isClickable: Boolean
        get() = google.isClickable

    val isVisible: Boolean
        get() = google.isVisible

    val transparency: Float
        get() = google.transparency

    val bearing: Float
        get() = google.bearing

    val zIndex: Float
        get() = google.zIndex

    val location: LatLng
        get() = google.location.asWrapper()

    val width: Float
        get() = google.width

    val height: Float
        get() = google.height

    val anchorU: Float
        get() = google.anchorU

    val anchorV: Float
        get() = google.anchorV

    val bounds: LatLngBounds
        get() = google.bounds.asWrapper()

    val image: BitmapDescriptor
        get() = google.image.asWrapper()

    fun image(bitmapDescriptor: BitmapDescriptor): GroundOverlayOptions = apply {
        bitmapDescriptor.google?.run { google = google.image(this) }
        bitmapDescriptor.huawei?.run { huawei = huawei.image(this) }
    }

    fun position(position: LatLng, width: Float): GroundOverlayOptions = apply {
        google = google.position(position.google, width)
        huawei = huawei.position(position.huawei, width)
    }

    fun position(position: LatLng, width: Float, length: Float): GroundOverlayOptions = apply {
        google = google.position(position.google, width, length)
        huawei = huawei.position(position.huawei, width, length)
    }

    fun clickable(clickable: Boolean): GroundOverlayOptions = apply {
        google = google.clickable(clickable)
        huawei = huawei.clickable(clickable)
    }

    fun anchor(u: Float, v: Float): GroundOverlayOptions = apply {
        google = google.anchor(u, v)
        huawei = huawei.anchor(u, v)
    }

    fun visible(visible: Boolean): GroundOverlayOptions = apply {
        google = google.visible(visible)
        huawei = huawei.visible(visible)
    }

    fun transparency(transparency: Float): GroundOverlayOptions = apply {
        google = google.transparency(transparency)
        huawei = huawei.transparency(transparency)
    }

    fun bearing(bearing: Float): GroundOverlayOptions = apply {
        google = google.bearing(bearing)
        huawei = huawei.bearing(bearing)
    }

    fun zIndex(zIndex: Float): GroundOverlayOptions = apply {
        google = google.zIndex(zIndex)
        huawei = huawei.zIndex(zIndex)
    }

    fun  positionFromBounds(bounds: LatLngBounds): GroundOverlayOptions = apply {
        google = google.positionFromBounds(bounds.google)
        huawei = huawei.positionFromBounds(bounds.huawei)
    }
}
