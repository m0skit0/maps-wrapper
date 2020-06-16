package org.m0skit0.android.mapswrapper

import org.koin.core.get
import org.m0skit0.android.mapswrapper.di.MapsWrapperKoinComponent

class CircleOptions : MapsWrapperKoinComponent {

    internal var google: com.google.android.gms.maps.model.CircleOptions = get()
    internal var huawei: com.huawei.hms.maps.model.CircleOptions = get()

    fun center(center: LatLng): CircleOptions = apply {
        google = google.center(center.google)
        huawei = huawei.center(center.huawei)
    }

    fun radius(radius: Double): CircleOptions = apply {
        google = google.radius(radius)
        huawei = huawei.radius(radius)
    }

    fun strokeWidth(width: Float): CircleOptions = apply {
        google = google.strokeWidth(width)
        huawei = huawei.strokeWidth(width)
    }

    fun strokeColor(color: Int): CircleOptions = apply {
        google = google.strokeColor(color)
        huawei = huawei.strokeColor(color)
    }

    fun fillColor(color: Int): CircleOptions = apply {
        google = google.fillColor(color)
        huawei = huawei.fillColor(color)
    }

    fun zIndex(zIndex: Float): CircleOptions = apply {
        google = google.zIndex(zIndex)
        huawei = huawei.zIndex(zIndex)
    }

    fun visible(visible: Boolean): CircleOptions = apply {
        google = google.visible(visible)
        huawei = huawei.visible(visible)
    }

    fun clickable(clickable: Boolean): CircleOptions = apply {
        google = google.clickable(clickable)
        huawei = huawei.clickable(clickable)
    }

    fun strokePattern(patternItems: List<PatternItem>?): CircleOptions = apply {
        google = google.strokePattern(patternItems?.map { it.google })
        huawei = huawei.strokePattern(patternItems?.map { it.huawei })
    }
}
