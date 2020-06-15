package org.m0skit0.android.mapswrapper.di

import com.google.android.gms.common.GoogleApiAvailability
import com.huawei.hms.api.HuaweiApiAvailability
import org.koin.core.module.Module
import org.koin.dsl.module

internal object ApiAvailabilityModuleProvider : KoinModuleProvider {
    override fun module(): Module = module {
        single<HuaweiApiAvailability> { HuaweiApiAvailability.getInstance() }
        single<GoogleApiAvailability> { GoogleApiAvailability.getInstance() }
    }
}
