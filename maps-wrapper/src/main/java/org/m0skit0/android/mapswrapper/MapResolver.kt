package org.m0skit0.android.mapswrapper

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.annotation.AttrRes
import androidx.fragment.app.Fragment
import org.koin.core.parameter.parametersOf
import org.m0skit0.android.mapswrapper.di.koin

internal fun mapFragmentFromResolverType(context: Context, strategy: MapResolverStrategy): Fragment =
    when (strategy) {
        MapResolverStrategy.FORCE_GOOGLE -> googleSupportMapFragment()
        MapResolverStrategy.FORCE_HUAWEI -> huaweiSupportMapFragment()
        MapResolverStrategy.GOOGLE_THEN_HUAWEI ->
            context.googleThenHuawei({ googleSupportMapFragment() }, { huaweiSupportMapFragment() })
        MapResolverStrategy.HUAWEI_THEN_GOOGLE ->
            context.huaweiThenGoogle({ huaweiSupportMapFragment() }, { googleSupportMapFragment() })
    }

private fun googleSupportMapFragment(): com.google.android.gms.maps.SupportMapFragment = koin().get()

private fun Context.isGoogleAvailable(): Boolean = ApiAvailability.isGoogleAvailable(this)

private fun huaweiSupportMapFragment(): com.huawei.hms.maps.SupportMapFragment = koin().get()

private fun Context.isHuaweiAvailable(): Boolean = ApiAvailability.isHuaweiAvailable(this)

internal fun mapViewFromResolverType(context: Context, attr: AttributeSet?, @AttrRes defStyleAttr: Int, strategy: MapResolverStrategy): FrameLayout =
    when (strategy) {
        MapResolverStrategy.FORCE_GOOGLE -> context.googleMapView(attr, defStyleAttr)
        MapResolverStrategy.FORCE_HUAWEI -> context.huaweiMapView(attr, defStyleAttr)
        MapResolverStrategy.GOOGLE_THEN_HUAWEI ->
            context.googleThenHuawei( { googleMapView(attr, defStyleAttr) }, { huaweiMapView(attr, defStyleAttr) })
        MapResolverStrategy.HUAWEI_THEN_GOOGLE ->
            context.huaweiThenGoogle({ huaweiMapView(attr, defStyleAttr) }, { googleMapView(attr, defStyleAttr) })
    }

private fun Context.googleMapView(attr: AttributeSet?, @AttrRes defStyleAttr: Int): com.google.android.gms.maps.MapView =
    koin().get { parametersOf(this, attr, defStyleAttr) }

private fun Context.huaweiMapView(attr: AttributeSet?, @AttrRes defStyleAttr: Int): com.huawei.hms.maps.MapView =
    koin().get { parametersOf(this, attr, defStyleAttr) }

private fun <T> Context.googleThenHuawei(google: Context.() -> T, huawei: Context.() -> T): T =
    when {
        isGoogleAvailable() -> google()
        isHuaweiAvailable() -> huawei()
        else -> throwNoGoogleNoHuaweiServices()
    }

private fun <T> Context.huaweiThenGoogle(huawei: Context.() -> T, google: Context.() -> T): T =
    when {
        isHuaweiAvailable() -> huawei()
        isGoogleAvailable() -> google()
        else -> throwNoGoogleNoHuaweiServices()
    }


