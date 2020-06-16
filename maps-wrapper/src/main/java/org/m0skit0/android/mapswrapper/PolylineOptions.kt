package org.m0skit0.android.mapswrapper

import org.koin.core.get
import org.m0skit0.android.mapswrapper.di.MapsWrapperKoinComponent

class PolylineOptions : MapsWrapperKoinComponent {

    internal var google: com.google.android.gms.maps.model.PolylineOptions = get()
    internal var huawei: com.huawei.hms.maps.model.PolylineOptions = get()

    fun add(position: LatLng?): PolylineOptions = apply {
        google = google.add(position?.google)
        huawei = huawei.add(position?.huawei)
    }

    fun add(vararg positions: LatLng): PolylineOptions = apply {
        google = google.add(*positions.map { it.google }.toTypedArray())
        huawei = huawei.add(*positions.map { it.huawei }.toTypedArray())
    }

    fun addAll(positions: Iterable<LatLng>): PolylineOptions = apply {
        google = google.addAll(positions.map { it.google })
        huawei = huawei.addAll(positions.map { it.huawei })
    }

    fun width(width: Float): PolylineOptions = apply {
        google = google.width(width)
        huawei = huawei.width(width)
    }

    fun color(color: Int): PolylineOptions = apply {
        google = google.color(color)
        huawei = huawei.color(color)
    }

    fun jointType(jointType: Int): PolylineOptions = apply {
        google = google.jointType(jointType)
        huawei = huawei.jointType(jointType)
    }

    fun zIndex(zIndex: Float): PolylineOptions = apply {
        google = google.zIndex(zIndex)
        huawei = huawei.zIndex(zIndex)
    }

    fun visible(visible: Boolean): PolylineOptions = apply {
        google = google.visible(visible)
        huawei = huawei.visible(visible)
    }

    fun geodesic(geodesic: Boolean): PolylineOptions = apply {
        google = google.geodesic(geodesic)
        huawei = huawei.geodesic(geodesic)
    }

    fun clickable(clickable: Boolean): PolylineOptions = apply {
        google = google.clickable(clickable)
        huawei = huawei.clickable(clickable)
    }
}
