package org.m0skit0.android.mapswrapper

import org.koin.core.parameter.parametersOf
import org.m0skit0.android.mapswrapper.di.koin

class Gap(length: Float) : PatternItem(
    koin().get { parametersOf(length) },
    koin().get { parametersOf(length) }
)
