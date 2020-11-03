package org.m0skit0.android.mapswrapper

import org.m0skit0.android.mapswrapper.model.*

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

internal fun com.google.android.gms.maps.model.Marker.asWrapper(): Marker = Marker(this)

internal fun com.huawei.hms.maps.model.Marker.asWrapper(): Marker = Marker(this)

internal fun com.google.android.gms.maps.model.TileOverlay.asWrapper(): TileOverlay = TileOverlay(this)

internal fun com.huawei.hms.maps.model.TileOverlay.asWrapper(): TileOverlay = TileOverlay(this)

internal fun com.google.android.gms.maps.model.GroundOverlay.asWrapper(): GroundOverlay = GroundOverlay(this)

internal fun com.huawei.hms.maps.model.GroundOverlay.asWrapper(): GroundOverlay = GroundOverlay(this)

internal fun com.google.android.gms.maps.model.Polygon.asWrapper(): Polygon = Polygon(this)

internal fun com.huawei.hms.maps.model.Polygon.asWrapper(): Polygon = Polygon(this)

internal fun com.google.android.gms.maps.model.Polyline.asWrapper(): Polyline = Polyline(this)

internal fun com.huawei.hms.maps.model.Polyline.asWrapper(): Polyline = Polyline(this)

internal fun com.google.android.gms.maps.model.Circle.asWrapper(): Circle = Circle(this)

internal fun com.huawei.hms.maps.model.Circle.asWrapper(): Circle = Circle(this)

internal fun com.google.android.gms.maps.Projection.asWrapper(): Projection = Projection(this)

internal fun com.huawei.hms.maps.Projection.asWrapper(): Projection = Projection(this)

internal fun com.google.android.gms.maps.UiSettings.asWrapper(): UiSettings = UiSettings(this)

internal fun com.huawei.hms.maps.UiSettings.asWrapper(): UiSettings = UiSettings(this)

internal fun com.google.android.gms.maps.model.CameraPosition.asWrapper(): CameraPosition = CameraPosition(this)

internal fun com.huawei.hms.maps.model.CameraPosition.asWrapper(): CameraPosition = CameraPosition(this)
