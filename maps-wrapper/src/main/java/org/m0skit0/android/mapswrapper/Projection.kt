package org.m0skit0.android.mapswrapper

import android.graphics.Point
import org.m0skit0.android.mapswrapper.model.LatLng
import org.m0skit0.android.mapswrapper.model.LatLngBounds
import org.m0skit0.android.mapswrapper.model.VisibleRegion

class Projection(
    internal val google: com.google.android.gms.maps.Projection?,
    internal val huawei: com.huawei.hms.maps.Projection?
) {

    internal constructor(google: com.google.android.gms.maps.Projection?) : this(google, null)
    internal constructor(huawei: com.huawei.hms.maps.Projection?) : this(null, huawei)

    val visibleRegion: VisibleRegion
        get() = google?.visibleRegion?.let {
            VisibleRegion(
                LatLng(it.nearLeft.latitude, it.nearLeft.longitude),
                LatLng(it.nearRight.latitude, it.nearRight.longitude),
                LatLng(it.farLeft.latitude, it.farLeft.longitude),
                LatLng(it.farRight.latitude, it.farRight.longitude),
                LatLngBounds(it.latLngBounds)
            )
        } ?: huawei?.visibleRegion?.let {
            VisibleRegion(
                LatLng(it.nearLeft.latitude, it.nearLeft.longitude),
                LatLng(it.nearRight.latitude, it.nearRight.longitude),
                LatLng(it.farLeft.latitude, it.farLeft.longitude),
                LatLng(it.farRight.latitude, it.farRight.longitude),
                LatLngBounds(it.latLngBounds)
            )
        } ?: throwUnableToResolveGoogleOrHuawei()

    fun fromScreenLocation(point: Point): LatLng =
        google?.fromScreenLocation(point)?.let { LatLng(it.latitude, it.longitude) }
            ?: huawei?.fromScreenLocation(point)?.let { LatLng(it.latitude, it.longitude) }
            ?: throwUnableToResolveGoogleOrHuawei()

    fun toScreenLocation(location: LatLng): Point =
        google?.toScreenLocation(location.google)
            ?: huawei?.toScreenLocation(location.huawei)
            ?: throwUnableToResolveGoogleOrHuawei()
}
