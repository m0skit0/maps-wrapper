package org.m0skit0.android.mapswrapper.model

data class VisibleRegion(
    val nearLeft: LatLng,
    val nearRight: LatLng,
    val farLeft: LatLng,
    val farRight: LatLng,
    val latLngBounds: LatLngBounds
) {

    internal val google by lazy {
        com.google.android.gms.maps.model.VisibleRegion(
            nearLeft.google,
            nearRight.google,
            farLeft.google,
            farRight.google,
            latLngBounds.google
        )
    }

    internal val huawei by lazy {
        com.huawei.hms.maps.model.VisibleRegion(
            nearLeft.huawei,
            nearRight.huawei,
            farLeft.huawei,
            farRight.huawei,
            latLngBounds.huawei
        )
    }
}
