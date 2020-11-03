package org.m0skit0.android.mapswrapper.model

internal fun com.google.android.gms.maps.model.LatLng.asWrapper(): LatLng = LatLng(latitude, longitude)

internal fun com.huawei.hms.maps.model.LatLng.asWrapper(): LatLng = LatLng(latitude, longitude)

internal fun com.google.android.gms.maps.model.LatLngBounds.asWrapper() = LatLngBounds(this)

internal fun com.huawei.hms.maps.model.LatLngBounds.asWrapper() = LatLngBounds(this)

internal fun com.google.android.gms.maps.model.VisibleRegion.asWrapper() = VisibleRegion(
    nearLeft.asWrapper(),
    nearRight.asWrapper(),
    farLeft.asWrapper(),
    farRight.asWrapper(),
    latLngBounds.asWrapper()
)

internal fun com.huawei.hms.maps.model.VisibleRegion.asWrapper() = VisibleRegion(
    nearLeft.asWrapper(),
    nearRight.asWrapper(),
    farLeft.asWrapper(),
    farRight.asWrapper(),
    latLngBounds.asWrapper()
)

internal fun com.google.android.gms.maps.model.Cap.asWrapper(): Cap = Cap(this)

internal fun com.huawei.hms.maps.model.Cap.asWrapper(): Cap = Cap(this)

internal fun com.google.android.gms.maps.model.PatternItem.asWrapper(): PatternItem = PatternItem(this)

internal fun com.huawei.hms.maps.model.PatternItem.asWrapper(): PatternItem = PatternItem(this)
