package org.m0skit0.android.mapswrapper.di

import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.m0skit0.android.mapswrapper.*

internal object WrapperModelsModuleProvider : KoinModuleProvider {

    val WITH_CAMERA_POSITION = named("WITH_CAMERA_POSITION")

    override fun module(): Module = module {
        factory { (google: com.google.android.gms.maps.model.CameraPosition?, huawei: com.huawei.hms.maps.model.CameraPosition?) ->
            CameraPosition(google, huawei)
        }
        factory { (google: com.google.android.gms.maps.UiSettings?, huawei: com.huawei.hms.maps.UiSettings?) ->
            UiSettings(google, huawei)
        }
        factory { (google: com.google.android.gms.maps.Projection?, huawei: com.huawei.hms.maps.Projection?) ->
            Projection(google, huawei)
        }
        factory { (google: com.google.android.gms.maps.model.Circle?, huawei: com.huawei.hms.maps.model.Circle?) ->
            Circle(google, huawei)
        }
        factory { (google: com.google.android.gms.maps.model.Marker?, huawei: com.huawei.hms.maps.model.Marker?) ->
            Marker(google, huawei)
        }
        factory { (google: com.google.android.gms.maps.model.Polyline?, huawei: com.huawei.hms.maps.model.Polyline?) ->
            Polyline(google, huawei)
        }
        factory { (google: com.google.android.gms.maps.model.Polygon?, huawei: com.huawei.hms.maps.model.Polygon?) ->
            Polygon(google, huawei)
        }
        factory { (google: com.google.android.gms.maps.model.GroundOverlay?, huawei: com.huawei.hms.maps.model.GroundOverlay?) ->
            GroundOverlay(google, huawei)
        }
        factory { (latitude: Double, longitude: Double) -> LatLng(latitude, longitude) }
        factory { (google: com.google.android.gms.maps.model.PatternItem?, huawei: com.huawei.hms.maps.model.PatternItem?) ->
            PatternItem(google, huawei)
        }
        factory { (google: com.google.android.gms.maps.model.BitmapDescriptor?, huawei: com.huawei.hms.maps.model.BitmapDescriptor?) ->
            BitmapDescriptor(google, huawei)
        }
        factory { (google: com.google.android.gms.maps.CameraUpdate?, huawei: com.huawei.hms.maps.CameraUpdate?) ->
            CameraUpdate(google, huawei)
        }
    }
}
