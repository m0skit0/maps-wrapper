package org.m0skit0.android.mapswrapper

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class SupportMapFragment : Fragment() {

    private val containedSupportMapFragment: Fragment by lazy {
        context?.run { mapFragmentFromResolverType(this, mapType) }
            ?: throw IllegalStateException("Context is null")
    }

    private var mapType: MapResolverStrategy = MapResolverStrategy.default

    fun getMapAsync(callback: OnMapReadyCallback) {
        when {
            isGoogleMap() -> googleGetMapAsync(callback)
            isHuaweiMap() -> huaweiGetMapAsync(callback)
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
            object : OnMapReadyCallback {
                override fun onMapReady(map: CommonMap) {
                    continuation.resume(map)
                }
            }.let { callback -> getMapAsync(callback) }
        }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_container, container)

    override fun onInflate(context: Context?, attrs: AttributeSet?, savedInstanceState: Bundle?) {
        super.onInflate(context, attrs, savedInstanceState)
        context?.obtainStyledAttributes(attrs, R.styleable.mapResolution)
            ?.apply {
                getText(R.styleable.mapResolution_type)
                    ?.let { mapType = MapResolverStrategy.fromValue(it.toString())  }
            }
            ?.recycle()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        replaceContainerWithCorrespondingMapFragment()
    }

    private fun replaceContainerWithCorrespondingMapFragment() {
        fragmentManager
            ?.beginTransaction()
            ?.replace(R.id.container, containedSupportMapFragment)
            ?.commitNow()
    }

    private fun isGoogleMap(): Boolean = containedSupportMapFragment is com.google.android.gms.maps.SupportMapFragment

    private fun googleMap(): com.google.android.gms.maps.SupportMapFragment =
        containedSupportMapFragment as com.google.android.gms.maps.SupportMapFragment

    private fun googleGetMapAsync(callback: OnMapReadyCallback) {
        googleMap().getMapAsync { CommonMap(it).let { commonMap ->  callback.onMapReady(commonMap) } }
    }

    private fun isHuaweiMap(): Boolean = containedSupportMapFragment is com.huawei.hms.maps.SupportMapFragment

    private fun huaweiMap(): com.huawei.hms.maps.SupportMapFragment =
        containedSupportMapFragment as com.huawei.hms.maps.SupportMapFragment

    private fun huaweiGetMapAsync(callback: OnMapReadyCallback) {
        huaweiMap().getMapAsync { CommonMap(it).let { commonMap ->  callback.onMapReady(commonMap) } }
    }
}
