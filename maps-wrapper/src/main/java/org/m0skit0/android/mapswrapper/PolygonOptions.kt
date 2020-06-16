package org.m0skit0.android.mapswrapper

import org.koin.core.get
import org.m0skit0.android.mapswrapper.di.MapsWrapperKoinComponent

class PolygonOptions : MapsWrapperKoinComponent {

    internal var google: com.google.android.gms.maps.model.PolygonOptions = get()
    internal var huawei: com.huawei.hms.maps.model.PolygonOptions = get()

    fun add(position: LatLng): PolygonOptions = apply {
        google = google.add(position.google)
        huawei = huawei.add(position.huawei)
    }

    fun add(vararg positions: LatLng): PolygonOptions = apply {
        google = google.add(*positions.map { it.google }.toTypedArray())
        huawei = huawei.add(*positions.map { it.huawei }.toTypedArray())
    }

    fun addAll(positions: Iterable<LatLng>): PolygonOptions = apply {
        google = google.addAll(positions.map { it.google })
        huawei = huawei.addAll(positions.map { it.huawei })
    }

    fun zIndex(zIndex: Float): PolygonOptions = apply {
        google = google.zIndex(zIndex)
        huawei = huawei.zIndex(zIndex)
    }

    fun visible(visible: Boolean): PolygonOptions = apply {
        google = google.visible(visible)
        huawei = huawei.visible(visible)
    }

    fun geodesic(geodesic: Boolean): PolygonOptions = apply {
        google = google.geodesic(geodesic)
        huawei = huawei.geodesic(geodesic)
    }

    fun clickable(clickable: Boolean): PolygonOptions = apply {
        google = google.clickable(clickable)
        huawei = huawei.clickable(clickable)
    }

    fun addHole(hole: Iterable<LatLng>): PolygonOptions = apply {
        google = google.addHole(hole.map { it.google })
        huawei = huawei.addHole(hole.map { it.huawei })
    }

    fun strokeWidth(width: Float): PolygonOptions = apply {
        google = google.strokeWidth(width)
        huawei = huawei.strokeWidth(width)
    }

    fun strokeColor(color: Int): PolygonOptions = apply {
        google = google.strokeColor(color)
        huawei = huawei.strokeColor(color)
    }

    fun strokeJointType(jointType: Int): PolygonOptions = apply {
        google = google.strokeJointType(jointType)
        huawei = huawei.strokeJointType(jointType)
    }

    fun fillColor(color: Int): PolygonOptions = apply {
        google = google.fillColor(color)
        huawei = huawei.fillColor(color)
    }
}
