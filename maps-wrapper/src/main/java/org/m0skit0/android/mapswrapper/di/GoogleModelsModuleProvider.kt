package org.m0skit0.android.mapswrapper.di

import com.google.android.gms.maps.model.*
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.m0skit0.android.mapswrapper.LatLng

internal object GoogleModelsModuleProvider : KoinModuleProvider {

    val WITH_CAMERA_POSITION = named("WITH_CAMERA_POSITION")

    override fun module(): Module = module {
        factory { ButtCap() }
        factory { CircleOptions() }
        factory { (bitmapDescriptor: BitmapDescriptor, length: Float) -> CustomCap(bitmapDescriptor, length) }
        factory { (length: Float) -> Dash(length) }
        factory { Dot() }
        factory { (length: Float) -> Gap(length) }
        factory { GroundOverlayOptions() }
        factory { (latitude: Double, longitude: Double) -> com.google.android.gms.maps.model.LatLng(latitude, longitude) }
        factory { (latLng1: LatLng, latLng2: LatLng) -> LatLngBounds(latLng1.google, latLng2.google) }
        factory { LatLngBounds.Builder() }
        factory { MarkerOptions() }
        factory { PolygonOptions() }
        factory { RoundCap() }
        factory { SquareCap() }
        factory { CameraPosition.Builder() }
        factory(WITH_CAMERA_POSITION) { (cameraPosition: CameraPosition) -> CameraPosition.Builder(cameraPosition) }
    }
}
