package org.m0skit0.android.mapswrapper

import android.graphics.Point
import org.koin.core.get
import org.koin.core.parameter.parametersOf
import org.m0skit0.android.mapswrapper.di.MapsWrapperKoinComponent

class Projection(
    internal val google: com.google.android.gms.maps.Projection?,
    internal val huawei: com.huawei.hms.maps.Projection?
) : MapsWrapperKoinComponent {

    fun fromScreenLocation(point: Point): LatLng =
        google?.fromScreenLocation(point)?.let { get { parametersOf(it.latitude, it.longitude) } }
            ?: huawei?.fromScreenLocation(point)?.let { get { parametersOf(it.latitude, it.longitude) } }
            ?: LatLng(0.0, 0.0)
}
