package org.m0skit0.android.mapswrapper

import android.content.Context
import com.google.android.gms.common.GoogleApiAvailability
import com.huawei.hms.api.HuaweiApiAvailability
import org.koin.core.get
import org.m0skit0.android.mapswrapper.di.MapsWrapperKoinComponent

internal object ApiAvailability : MapsWrapperKoinComponent {

    private lateinit var context: Context

    internal fun isHuaweiAvailable(context: Context = ApiAvailability.context): Boolean {
        this.context = context.applicationContext
        return get<HuaweiApiAvailability>().isHuaweiMobileServicesAvailable(context) ==
                com.huawei.hms.api.ConnectionResult.SUCCESS
    }

    internal fun isGoogleAvailable(context: Context = ApiAvailability.context): Boolean {
        this.context = context.applicationContext
        return get<GoogleApiAvailability>().isGooglePlayServicesAvailable(context) ==
                com.google.android.gms.common.ConnectionResult.SUCCESS
    }
}
