package org.m0skit0.android.mapswrapper

import android.content.Context
import androidx.fragment.app.Fragment
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import com.huawei.hms.api.HuaweiApiAvailability

internal fun mapFragmentFromResolverType(context: Context?, strategy: MapResolverStrategy): Fragment =
    when (strategy) {
        MapResolverStrategy.FORCE_GOOGLE -> googleSupportMapFragment()
        MapResolverStrategy.FORCE_HUAWEI -> huaweiSupportMapFragment()
        MapResolverStrategy.GOOGLE_THEN_HUAWEI -> googleThenHuawei(context)
        MapResolverStrategy.HUAWEI_THEN_GOOGLE -> huaweiThenGoogle(context)
    }

private fun googleSupportMapFragment(): com.google.android.gms.maps.SupportMapFragment =
    com.google.android.gms.maps.SupportMapFragment.newInstance()

private fun isGoogleAvailable(context: Context?): Boolean {
    context ?: return false
    return GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(context) == ConnectionResult.SUCCESS
}

private fun googleThenHuawei(context: Context?): Fragment =
    when {
        isGoogleAvailable(context) -> googleSupportMapFragment()
        isHuaweiAvailable(context) -> huaweiSupportMapFragment()
        else -> throw IllegalStateException("No Google or Huawei Services found!")
    }

private fun huaweiSupportMapFragment(): com.huawei.hms.maps.SupportMapFragment =
    com.huawei.hms.maps.SupportMapFragment.newInstance()

private fun isHuaweiAvailable(context: Context?): Boolean {
    context ?: return false
    return HuaweiApiAvailability.getInstance().isHuaweiMobileServicesAvailable(context) == com.huawei.hms.api.ConnectionResult.SUCCESS
}

private fun huaweiThenGoogle(context: Context?): Fragment =
    when {
        isHuaweiAvailable(context) -> huaweiSupportMapFragment()
        isGoogleAvailable(context) -> googleSupportMapFragment()
        else -> throw IllegalStateException("No Google or Huawei Services found!")
    }
