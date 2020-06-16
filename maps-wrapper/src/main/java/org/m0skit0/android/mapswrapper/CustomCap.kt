package org.m0skit0.android.mapswrapper

import org.koin.core.parameter.parametersOf
import org.m0skit0.android.mapswrapper.di.koin

class CustomCap(bitmapDescriptor: BitmapDescriptor, length: Float) : Cap(
    bitmapDescriptor.google?.run {
        koin().get<com.google.android.gms.maps.model.CustomCap> { parametersOf(this, length) }
    },
    koin().get { parametersOf(bitmapDescriptor.huawei, length) }
)
