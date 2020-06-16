package org.m0skit0.android.mapswrapper.di

import org.koin.core.Koin
import org.koin.core.KoinComponent
import org.koin.core.get
import org.koin.core.parameter.parametersOf

internal interface MapsWrapperKoinComponent : KoinComponent {
    override fun getKoin(): Koin = koin()
}

internal inline fun <reified T, U> MapsWrapperKoinComponent.getGoogle(google: U): T = get { parametersOf(google, null) }
internal inline fun <reified T, U> MapsWrapperKoinComponent.getHuawei(huawei: U): T = get { parametersOf(null, huawei) }
