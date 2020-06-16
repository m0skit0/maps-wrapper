package org.m0skit0.android.mapswrapper

import org.m0skit0.android.mapswrapper.di.koin

class Dot : PatternItem(
    koin().get<com.google.android.gms.maps.model.Dot>(),
    koin().get<com.huawei.hms.maps.model.Dot>()
)
