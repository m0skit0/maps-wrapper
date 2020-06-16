package org.m0skit0.android.mapswrapper

import org.koin.core.inject
import org.koin.core.parameter.parametersOf
import org.m0skit0.android.mapswrapper.di.MapsWrapperKoinComponent

data class LatLng(val latitude: Double, val longitude: Double) : MapsWrapperKoinComponent {
    internal val google: com.google.android.gms.maps.model.LatLng by inject { parametersOf(latitude, longitude) }
    internal val huawei: com.huawei.hms.maps.model.LatLng by inject { parametersOf(latitude, longitude) }
}
