package org.m0skit0.android.mapswrapper

import android.content.Context
import androidx.fragment.app.Fragment
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.huawei.hms.api.HuaweiApiAvailability

internal fun mapFragmentFromResolverType(context: Context, strategy: MapResolverStrategy): Fragment =
    when (strategy) {
        MapResolverStrategy.FORCE_GOOGLE -> googleSupportMapFragment()
        MapResolverStrategy.FORCE_HUAWEI -> huaweiSupportMapFragment()
        MapResolverStrategy.GOOGLE_THEN_HUAWEI -> context.googleThenHuawei()
        MapResolverStrategy.HUAWEI_THEN_GOOGLE -> context.huaweiThenGoogle()
    }

private fun googleSupportMapFragment(): com.google.android.gms.maps.SupportMapFragment =
    com.google.android.gms.maps.SupportMapFragment.newInstance()

private fun Context.isGoogleAvailable(): Boolean {
    return GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this) == ConnectionResult.SUCCESS
}

private fun Context.googleThenHuawei(): Fragment =
    when {
        isGoogleAvailable() -> googleSupportMapFragment()
        isHuaweiAvailable() -> huaweiSupportMapFragment()
        else -> throw IllegalStateException("No Google or Huawei Services found!")
    }

private fun huaweiSupportMapFragment(): com.huawei.hms.maps.SupportMapFragment =
    com.huawei.hms.maps.SupportMapFragment.newInstance()

private fun Context.isHuaweiAvailable(): Boolean {
    return HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(this) == com.huawei.hms.api.ConnectionResult.SUCCESS
}

private fun Context.huaweiThenGoogle(): Fragment =
    when {
        isHuaweiAvailable() -> huaweiSupportMapFragment()
        isGoogleAvailable() -> googleSupportMapFragment()
        else -> throw IllegalStateException("No Google or Huawei Services found!")
    }
