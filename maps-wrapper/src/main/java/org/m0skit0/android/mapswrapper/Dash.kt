package org.m0skit0.android.mapswrapper

import org.koin.core.parameter.parametersOf
import org.m0skit0.android.mapswrapper.di.koin

class Dash(length: Float) : PatternItem(
    koin().get<com.google.android.gms.maps.model.Dash> { parametersOf(length) },
    koin().get<com.huawei.hms.maps.model.Dash> { parametersOf(length) }
)
