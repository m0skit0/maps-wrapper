package org.m0skit0.android.mapswrapper.di

import org.koin.core.Koin
import org.koin.core.KoinComponent

interface KoinInterface : KoinComponent {
    override fun getKoin(): Koin = koin()
}
