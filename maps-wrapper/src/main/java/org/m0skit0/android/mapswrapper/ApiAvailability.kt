package org.m0skit0.android.mapswrapper

import android.content.Context
import com.google.android.gms.common.GoogleApiAvailability
import com.huawei.hms.api.HuaweiApiAvailability

internal object ApiAvailability {

    private lateinit var context: Context

    internal fun isHuaweiAvailable(context: Context = ApiAvailability.context): Boolean {
        this.context = context.applicationContext
        return HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(context) ==
                com.huawei.hms.api.ConnectionResult.SUCCESS
    }

    internal fun isGoogleAvailable(context: Context = ApiAvailability.context): Boolean {
        this.context = context.applicationContext
        return GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context) ==
                com.google.android.gms.common.ConnectionResult.SUCCESS
    }
}
