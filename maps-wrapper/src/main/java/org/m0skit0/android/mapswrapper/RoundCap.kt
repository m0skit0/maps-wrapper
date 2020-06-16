package org.m0skit0.android.mapswrapper

import org.m0skit0.android.mapswrapper.di.koin

class RoundCap : Cap(
    koin().get<com.google.android.gms.maps.model.RoundCap>(),
    koin().get<com.huawei.hms.maps.model.RoundCap>()
)
