package org.m0skit0.android.mapswrapper.di

import org.koin.core.module.Module
import org.koin.dsl.module
import org.m0skit0.android.mapswrapper.LatLng

internal object ModelsModuleProvider : KoinModuleProvider {
    override fun module(): Module = module {
        factory { com.google.android.gms.maps.model.ButtCap() }
        factory { com.huawei.hms.maps.model.ButtCap() }
        factory { com.google.android.gms.maps.model.CircleOptions() }
        factory { com.huawei.hms.maps.model.CircleOptions() }
        factory { com.huawei.hms.maps.model.CircleOptions() }
        factory  { (bitmapDescriptor: com.google.android.gms.maps.model.BitmapDescriptor, length: Float) ->
            com.google.android.gms.maps.model.CustomCap(bitmapDescriptor, length)
        }
        factory { (bitmapDescriptor: com.huawei.hms.maps.model.BitmapDescriptor, length: Float) ->
            com.huawei.hms.maps.model.CustomCap(bitmapDescriptor, length)
        }
        factory { (length: Float) -> com.google.android.gms.maps.model.Dash(length) }
        factory { (length: Float) -> com.huawei.hms.maps.model.Dash(length) }
        factory { com.google.android.gms.maps.model.Dot() }
        factory { com.huawei.hms.maps.model.Dot() }
        factory { (length: Float) -> com.google.android.gms.maps.model.Gap(length) }
        factory { (length: Float) -> com.huawei.hms.maps.model.Gap(length) }
        factory { com.google.android.gms.maps.model.GroundOverlayOptions() }
        factory { com.google.android.gms.maps.model.GroundOverlayOptions() }
        factory { (latitude: Double, longitude: Double) ->
            com.google.android.gms.maps.model.LatLng(latitude, longitude)
        }
        factory { (latitude: Double, longitude: Double) ->
            com.huawei.hms.maps.model.LatLng(latitude, longitude)
        }
        factory { (latLng1: LatLng, latLng2: LatLng) ->
            com.google.android.gms.maps.model.LatLngBounds(latLng1.google, latLng2.google)
        }
        factory { (latLng1: LatLng, latLng2: LatLng) ->
            com.huawei.hms.maps.model.LatLngBounds(latLng1.huawei, latLng2.huawei)
        }
        factory { com.google.android.gms.maps.model.MarkerOptions() }
        factory { com.huawei.hms.maps.model.MarkerOptions() }
        factory { com.google.android.gms.maps.model.PolygonOptions() }
        factory { com.huawei.hms.maps.model.PolygonOptions() }
        factory { com.google.android.gms.maps.model.RoundCap() }
        factory { com.huawei.hms.maps.model.RoundCap() }
        factory { com.google.android.gms.maps.model.SquareCap() }
        factory { com.huawei.hms.maps.model.SquareCap() }
    }
}
