package org.m0skit0.android.mapswrapper

import android.graphics.Point
import org.m0skit0.android.mapswrapper.model.LatLng

class Projection(
    internal val google: com.google.android.gms.maps.Projection?,
    internal val huawei: com.huawei.hms.maps.Projection?
) {

    fun fromScreenLocation(point: Point): LatLng =
        google?.fromScreenLocation(point)?.let { LatLng(it.latitude, it.longitude) }
            ?: huawei?.fromScreenLocation(point)?.let { LatLng(it.latitude, it.longitude) }
            ?: LatLng(0.0, 0.0)
}
