package org.m0skit0.android.mapswrapper.di

import android.view.View
import com.google.android.gms.maps.GoogleMap
import com.huawei.hms.maps.HuaweiMap
import org.koin.core.module.Module
import org.koin.core.parameter.parametersOf
import org.koin.dsl.module
import org.m0skit0.android.mapswrapper.CommonMap

internal object CallbackModuleProvider : KoinModuleProvider {
    override fun module(): Module = module {
        factory<GoogleMap.CancelableCallback> { (callback: CommonMap.CancelableCallback?) ->
            object : GoogleMap.CancelableCallback {
                override fun onFinish() {
                    callback?.onFinish()
                }
                override fun onCancel() {
                    callback?.onCancel()
                }
            }
        }
        factory<HuaweiMap.CancelableCallback> { (callback: CommonMap.CancelableCallback?) ->
            object : HuaweiMap.CancelableCallback {
                override fun onFinish() {
                    callback?.onFinish()
                }
                override fun onCancel() {
                    callback?.onCancel()
                }
            }
        }
        factory<GoogleMap.InfoWindowAdapter> { (adapter: CommonMap.InfoWindowAdapter) ->
            object : GoogleMap.InfoWindowAdapter {
                override fun getInfoContents(marker: com.google.android.gms.maps.model.Marker?): View? =
                    adapter.getInfoContents(get { parametersOf(marker, null) })
                override fun getInfoWindow(marker: com.google.android.gms.maps.model.Marker?): View? =
                    adapter.getInfoWindow(get { parametersOf(marker, null) })
            }
        }
        factory<HuaweiMap.InfoWindowAdapter> { (adapter: CommonMap.InfoWindowAdapter) ->
            object : HuaweiMap.InfoWindowAdapter {
                override fun getInfoContents(marker: com.huawei.hms.maps.model.Marker?): View? =
                    adapter.getInfoContents(get { parametersOf(null, marker) })
                override fun getInfoWindow(marker: com.huawei.hms.maps.model.Marker?): View? =
                    adapter.getInfoWindow(get { parametersOf(null, marker) })
            }
        }
        factory<GoogleMap.OnMarkerDragListener> { (listener: CommonMap.OnMarkerDragListener) ->
            object : GoogleMap.OnMarkerDragListener {
                override fun onMarkerDragEnd(marker: com.google.android.gms.maps.model.Marker?) {
                    listener.onMarkerDragEnd(get { parametersOf(marker, null) })
                }
                override fun onMarkerDragStart(marker: com.google.android.gms.maps.model.Marker?) {
                    listener.onMarkerDragStart(get { parametersOf(marker, null) })
                }
                override fun onMarkerDrag(marker: com.google.android.gms.maps.model.Marker?) {
                    listener.onMarkerDragStart(get { parametersOf(marker, null) })
                }
            }
        }
        factory<HuaweiMap.OnMarkerDragListener> { (listener: CommonMap.OnMarkerDragListener) ->
            object : HuaweiMap.OnMarkerDragListener {
                override fun onMarkerDragEnd(marker: com.huawei.hms.maps.model.Marker?) {
                    listener.onMarkerDragEnd(get { parametersOf(null, marker) })
                }
                override fun onMarkerDragStart(marker: com.huawei.hms.maps.model.Marker?) {
                    listener.onMarkerDragStart(get { parametersOf(null, marker) })
                }
                override fun onMarkerDrag(marker: com.huawei.hms.maps.model.Marker?) {
                    listener.onMarkerDragStart(get { parametersOf(null, marker) })
                }
            }
        }
    }
}
