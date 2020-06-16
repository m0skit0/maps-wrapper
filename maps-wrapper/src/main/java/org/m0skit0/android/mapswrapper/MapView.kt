package org.m0skit0.android.mapswrapper

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import org.koin.core.get
import org.koin.core.parameter.parametersOf
import org.m0skit0.android.mapswrapper.di.MapsWrapperKoinComponent
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class MapView : FrameLayout, MapsWrapperKoinComponent {

    private lateinit var mapView: FrameLayout

    constructor(context: Context): super(context) {
        resolveMapView(context, null, 0, 0)
        addView(mapView)
    }

    constructor(context: Context, attr: AttributeSet?): super(context, attr) {
        resolveMapView(context, attr, 0, 0)
        addView(mapView)
    }

    constructor(context: Context, attr: AttributeSet?, @AttrRes defStyleAttr: Int): super(context, attr, defStyleAttr) {
        resolveMapView(context, attr, defStyleAttr, 0)
        addView(mapView)
    }

    constructor(context: Context, attr: AttributeSet?, @AttrRes defStyleAttr: Int, @StyleRes defStyleRes: Int): super(context, attr, defStyleAttr, defStyleRes) {
        resolveMapView(context, attr, defStyleAttr, defStyleRes)
        addView(mapView)
    }

    private fun resolveMapView(context: Context, attr: AttributeSet?, @AttrRes defStyleAttr: Int, @StyleRes defStyleRes: Int) {
        context.theme.obtainStyledAttributes(
            attr,
            R.styleable.mapResolution,
            defStyleAttr,
            defStyleRes
        ).apply {
                val strategy = getText(R.styleable.mapResolution_wrappedMapType)
                    ?.let { MapResolverStrategy.fromValue(it.toString())  }
                    ?: MapResolverStrategy.default
                mapView = mapViewFromResolverType(context, attr, defStyleAttr, strategy)
        }.recycle()
    }

    private fun isGoogle(): Boolean = mapView is com.google.android.gms.maps.MapView

    private fun googleMap(): com.google.android.gms.maps.MapView = mapView as com.google.android.gms.maps.MapView

    private fun isHuawei(): Boolean = mapView is com.huawei.hms.maps.MapView

    private fun huaweiMap(): com.huawei.hms.maps.MapView = mapView as com.huawei.hms.maps.MapView

    fun getMapAsync(callback: OnMapReadyCallback) {
        when {
            isGoogle() -> googleGetMapAsync(callback)
            isHuawei() -> huaweiGetMapAsync(callback)
        }
    }

    fun getMapAsync(callback: (CommonMap) -> Unit) {
        val onMapReadyCallback = object : OnMapReadyCallback {
            override fun onMapReady(map: CommonMap) {
                callback(map)
            }
        }
        getMapAsync(onMapReadyCallback)
    }

    suspend fun mapAsync(): CommonMap =
        suspendCoroutine { continuation ->
            getMapAsync {
                continuation.resume(it)
            }
        }

    private fun googleGetMapAsync(callback: OnMapReadyCallback) {
        googleMap().getMapAsync { callback.onMapReady(get { parametersOf(it) }) }
    }

    private fun huaweiGetMapAsync(callback: OnMapReadyCallback) {
        huaweiMap().getMapAsync { callback.onMapReady(get { parametersOf(it) }) }
    }

    fun onCreate(bundle: Bundle?) {
        googleOrHuawei({ onCreate(bundle) }, { onCreate(bundle) })
    }

    fun onResume() {
        googleOrHuawei({ onResume() }, { onResume() })
    }

    fun onPause() {
        googleOrHuawei({ onPause() }, { onPause() })
    }

    fun onStart() {
        googleOrHuawei({ onStart() }, { onStart() })
    }

    fun onStop() {
        googleOrHuawei({ onStop() }, { onStop() })
    }

    fun onDestroy() {
        googleOrHuawei({ onDestroy() }, { onDestroy() })
    }

    fun onLowMemory() {
        googleOrHuawei({ onLowMemory() }, { onLowMemory() })
    }

    fun onSaveInstanceState(bundle: Bundle?) {
        googleOrHuawei({ onSaveInstanceState(bundle) }, { onSaveInstanceState(bundle) })
    }

    private inline fun <T> googleOrHuawei(google: com.google.android.gms.maps.MapView.() -> T, huawei: com.huawei.hms.maps.MapView.() -> T): T =
        when {
            isGoogle() -> this.googleMap().google()
            isHuawei() -> this.huaweiMap().huawei()
            else -> throwUnableToResolveGoogleOrHuawei()
        }
}
