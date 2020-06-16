package org.m0skit0.android.mapswrapper

import org.m0skit0.android.mapswrapper.di.koin

class SquareCap : Cap(
    koin().get<com.google.android.gms.maps.model.SquareCap>(),
    koin().get<com.huawei.hms.maps.model.SquareCap>()
)
