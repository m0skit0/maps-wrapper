package org.m0skit0.android.mapswrapper

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class SupportMapFragment : Fragment() {

    private val TAG = javaClass.simpleName

    private val containedSupportMapFragment: Fragment by lazy { instantiateSupportMapFragment() }

    private var callback: OnMapReadyCallback? = null

    fun getMapAsync(callback: OnMapReadyCallback) {
        this.callback = callback
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleGetMapAsync(callback)
            MapType.HUAWEI -> huaweiGetMapAsync(callback)
        }
    }

    suspend fun mapAsync(): CommonMap =
        suspendCoroutine { continuation ->
            object : OnMapReadyCallback {
                override fun onMapReady(map: CommonMap) {
                    continuation.resume(map)
                }
            }.let { callback -> getMapAsync(callback) }
        }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_container, container).apply {
            fragmentManager
                ?.beginTransaction()
                ?.replace(R.id.container, containedSupportMapFragment)
                ?.commitNow()
        }

    private fun instantiateSupportMapFragment(): Fragment = when (MapsConfiguration.type) {
        MapType.GOOGLE -> com.google.android.gms.maps.SupportMapFragment.newInstance()
        MapType.HUAWEI -> com.huawei.hms.maps.SupportMapFragment.newInstance()
    }

    private fun castToGoogleMap(): com.google.android.gms.maps.SupportMapFragment =
        containedSupportMapFragment as com.google.android.gms.maps.SupportMapFragment

    private fun googleGetMapAsync(callback: OnMapReadyCallback) {
        castToGoogleMap().getMapAsync { CommonMap(it).let { commonMap ->  callback.onMapReady(commonMap) } }
    }

    private fun castToHuaweiMap(): com.huawei.hms.maps.SupportMapFragment =
        containedSupportMapFragment as com.huawei.hms.maps.SupportMapFragment

    private fun huaweiGetMapAsync(callback: OnMapReadyCallback) {
        castToHuaweiMap().getMapAsync { CommonMap(it).let { commonMap ->  callback.onMapReady(commonMap) } }
    }

    override fun onInflate(context: Context?, attrs: AttributeSet?, savedInstanceState: Bundle?) {
        super.onInflate(context, attrs, savedInstanceState)
        context?.obtainStyledAttributes(attrs, R.styleable.org_m0skit0_android_mapswrapper_SupportMapFragment)?.also {
            val att = it.getText(R.styleable.org_m0skit0_android_mapswrapper_SupportMapFragment_type)
            Log.d(TAG, "onInflate $att")
        }?.recycle()

    }
}
