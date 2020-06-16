package org.m0skit0.android.mapswrapper

import org.m0skit0.android.mapswrapper.di.koin

class ButtCap : Cap(
    koin().get<com.google.android.gms.maps.model.ButtCap>(),
    koin().get<com.huawei.hms.maps.model.ButtCap>()
)
