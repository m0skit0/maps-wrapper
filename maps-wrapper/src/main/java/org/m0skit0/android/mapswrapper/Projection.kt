package org.m0skit0.android.mapswrapper

import android.graphics.Point
import org.m0skit0.android.mapswrapper.model.LatLng
import org.m0skit0.android.mapswrapper.model.VisibleRegion

class Projection(
    internal val google: com.google.android.gms.maps.Projection?,
    internal val huawei: com.huawei.hms.maps.Projection?
) {

    internal constructor(google: com.google.android.gms.maps.Projection?) : this(google, null)
    internal constructor(huawei: com.huawei.hms.maps.Projection?) : this(null, huawei)

    val visibleRegion: VisibleRegion
        get() = google?.visibleRegion?.asWrapper()
            ?: huawei?.visibleRegion?.asWrapper()
            ?: throwUnableToResolveGoogleOrHuawei()

    fun fromScreenLocation(point: Point): LatLng =
        google?.fromScreenLocation(point)?.asWrapper()
            ?: huawei?.fromScreenLocation(point)?.asWrapper()
            ?: throwUnableToResolveGoogleOrHuawei()

    fun toScreenLocation(location: LatLng): Point =
        google?.toScreenLocation(location.google)
            ?: huawei?.toScreenLocation(location.huawei)
            ?: throwUnableToResolveGoogleOrHuawei()
}
