package org.m0skit0.android.mapswrapper.di

import com.huawei.hms.maps.model.*
import org.koin.core.module.Module
import org.koin.dsl.module
import org.m0skit0.android.mapswrapper.LatLng

object HuaweiModelsModuleProvider : KoinModuleProvider {
    override fun module(): Module = module {
        factory { ButtCap() }
        factory { CircleOptions() }
        factory { (bitmapDescriptor: BitmapDescriptor, length: Float) -> CustomCap(bitmapDescriptor, length) }
        factory { (length: Float) -> Dash(length) }
        factory { Dot() }
        factory { (length: Float) -> Gap(length) }
        factory { GroundOverlayOptions() }
        factory { (latitude: Double, longitude: Double) -> com.huawei.hms.maps.model.LatLng(latitude, longitude) }
        factory { (latLng1: LatLng, latLng2: LatLng) -> LatLngBounds(latLng1.huawei, latLng2.huawei) }
        factory { LatLngBounds.Builder() }
        factory { MarkerOptions() }
        factory { PolygonOptions() }
        factory { RoundCap() }
        factory { SquareCap() }
        factory { CameraPosition.Builder() }
        factory { (cameraPosition: CameraPosition) -> CameraPosition.Builder(cameraPosition) }
    }
}
