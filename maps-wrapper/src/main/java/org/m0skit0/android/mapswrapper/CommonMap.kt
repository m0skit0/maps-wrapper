package org.m0skit0.android.mapswrapper

import android.view.View
import com.google.android.gms.maps.GoogleMap
import com.huawei.hms.maps.HuaweiMap

class CommonMap(private val map: Any) {

    private val googleMap: GoogleMap
        get() = map as GoogleMap

    private val huaweiMap: HuaweiMap
        get() = map as HuaweiMap

    private fun isGoogle(): Boolean = map is GoogleMap

    private fun isHuawei(): Boolean = map is HuaweiMap

    var mapType: Int
        get() = when {
            isGoogle() -> googleMap.mapType
            isHuawei() -> huaweiMap.mapType
            else -> -1
        }
        set(value) {
            when {
                isGoogle() -> googleMap.mapType = value
                isHuawei() -> huaweiMap.mapType = value
            }
        }

    var isMyLocationEnabled: Boolean
        get() = when {
            isGoogle() -> googleMap.isMyLocationEnabled
            isHuawei() -> huaweiMap.isMyLocationEnabled
            else -> false
        }
        set(value) {
            when {
                isGoogle() -> googleMap.isMyLocationEnabled = value
                isHuawei() -> huaweiMap.isMyLocationEnabled = value
            }
        }

    val cameraPosition: CameraPosition
        get() = when {
            isGoogle() -> googleMap.cameraPosition.let { CameraPosition(it, null) }
            isHuawei() -> huaweiMap.cameraPosition.let { CameraPosition(null, it) }
            else -> throwUnableToResolveGoogleOrHuawei()
        }

    val uiSettings: UiSettings
        get() = when {
            isGoogle() -> googleMap.uiSettings.let { UiSettings(it, null) }
            isHuawei() -> huaweiMap.uiSettings.let { UiSettings(null, it) }
            else -> throwUnableToResolveGoogleOrHuawei()
        }

    val projection: Projection
        get() = when {
            isGoogle() -> googleMap.projection.let { Projection(it, null) }
            isHuawei() -> huaweiMap.projection.let { Projection(null, it) }
            else -> throwUnableToResolveGoogleOrHuawei()
        }

    var isTrafficEnabled: Boolean
        get() = when {
            isGoogle() -> googleMap.isTrafficEnabled
            isHuawei() -> huaweiMap.isTrafficEnabled
            else -> throwUnableToResolveGoogleOrHuawei()
        }
        set(value) {
            when {
                isGoogle() -> googleMap.isTrafficEnabled = value
                isHuawei() -> huaweiMap.isTrafficEnabled = value
                else -> throwUnableToResolveGoogleOrHuawei()
            }
        }

    var isIndoorEnabled: Boolean
        get() = when {
            isGoogle() -> googleMap.isIndoorEnabled
            isHuawei() -> huaweiMap.isIndoorEnabled
            else -> throwUnableToResolveGoogleOrHuawei()
        }
        set(value) {
            when {
                isGoogle() -> googleMap.isIndoorEnabled = value
                isHuawei() -> huaweiMap.isIndoorEnabled = value
                else -> throwUnableToResolveGoogleOrHuawei()
            }
        }

    var isBuildingsEnabled: Boolean
        get() = when {
            isGoogle() -> googleMap.isBuildingsEnabled
            isHuawei() -> huaweiMap.isBuildingsEnabled
            else -> throwUnableToResolveGoogleOrHuawei()
        }
        set(value) {
            when {
                isGoogle() -> googleMap.isBuildingsEnabled = value
                isHuawei() -> huaweiMap.isBuildingsEnabled = value
                else -> throwUnableToResolveGoogleOrHuawei()
            }
        }

    fun clear() {
        when {
            isGoogle() -> googleMap.clear()
            isHuawei() -> huaweiMap.clear()
        }
    }

    fun moveCamera(cameraUpdate: CameraUpdate) {
        when {
            isGoogle() -> googleMap.moveCamera(cameraUpdate.googleCameraUpdate)
            isHuawei() -> huaweiMap.moveCamera(cameraUpdate.huaweiCameraUpdate)
        }
    }

    fun animateCamera(cameraUpdate: CameraUpdate) {
        when {
            isGoogle() -> googleMap.animateCamera(cameraUpdate.googleCameraUpdate)
            isHuawei() -> huaweiMap.animateCamera(cameraUpdate.huaweiCameraUpdate)
        }
    }

    fun animateCamera(cameraUpdate: CameraUpdate, callback: CancelableCallback?) {
        when {
            isGoogle() -> googleMap.animateCamera(
                cameraUpdate.googleCameraUpdate,
                object : GoogleMap.CancelableCallback {
                    override fun onFinish() {
                        callback?.onFinish()
                    }
                    override fun onCancel() {
                        callback?.onCancel()
                    }
                }
            )
            isHuawei() -> huaweiMap.animateCamera(
                cameraUpdate.huaweiCameraUpdate,
                object : HuaweiMap.CancelableCallback {
                    override fun onFinish() {
                        callback?.onFinish()
                    }
                    override fun onCancel() {
                        callback?.onCancel()
                    }
                }
            )
        }
    }

    fun animateCamera(cameraUpdate: CameraUpdate, value: Int, callback: CancelableCallback?) {
        when {
            isGoogle() -> googleMap.animateCamera(
                cameraUpdate.googleCameraUpdate,
                value,
                object : GoogleMap.CancelableCallback {
                    override fun onFinish() {
                        callback?.onFinish()
                    }
                    override fun onCancel() {
                        callback?.onCancel()
                    }
                }
            )
            isHuawei() -> huaweiMap.animateCamera(
                cameraUpdate.huaweiCameraUpdate,
                value,
                object : HuaweiMap.CancelableCallback {
                    override fun onFinish() {
                        callback?.onFinish()
                    }
                    override fun onCancel() {
                        callback?.onCancel()
                    }
                }
            )
        }
    }

    fun addCircle(circleOptions: CircleOptions): Circle =
        when {
            isGoogle() -> googleMap.addCircle(circleOptions.google).let { Circle(it, null) }
            isHuawei() -> huaweiMap.addCircle(circleOptions.huawei).let { Circle(null, it) }
            else -> throwUnableToResolveGoogleOrHuawei()
        }

    fun addMarker(markerOptions: MarkerOptions): Marker =
        when {
            isGoogle() -> googleMap.addMarker(markerOptions.google).let { Marker(it, null) }
            isHuawei() -> huaweiMap.addMarker(markerOptions.huawei).let { Marker(null, it) }
            else -> throwUnableToResolveGoogleOrHuawei()
        }

    fun addPolyline(polylineOptions: PolylineOptions): Polyline =
        when {
            isGoogle() -> googleMap.addPolyline(polylineOptions.google).let { Polyline(it, null) }
            isHuawei() -> huaweiMap.addPolyline(polylineOptions.huawei).let { Polyline(null, it) }
            else -> throwUnableToResolveGoogleOrHuawei()
        }

    fun stopAnimation() {
        when {
            isGoogle() -> googleMap.stopAnimation()
            isHuawei() -> huaweiMap.stopAnimation()
        }
    }

    fun setInfoWindowAdapter(adapter: InfoWindowAdapter) {
        when {
            isGoogle() -> googleMap.setInfoWindowAdapter(object : GoogleMap.InfoWindowAdapter {
                override fun getInfoContents(marker: com.google.android.gms.maps.model.Marker?): View? =
                    adapter.getInfoContents(Marker(marker, null))
                override fun getInfoWindow(marker: com.google.android.gms.maps.model.Marker?): View? =
                    adapter.getInfoWindow(Marker(marker, null))
            })
            isHuawei() -> huaweiMap.setInfoWindowAdapter(object : HuaweiMap.InfoWindowAdapter {
                override fun getInfoContents(marker: com.huawei.hms.maps.model.Marker?): View? =
                    adapter.getInfoContents(Marker(null, marker))
                override fun getInfoWindow(marker: com.huawei.hms.maps.model.Marker?): View? =
                    adapter.getInfoWindow(Marker(null, marker))
            })
        }
    }

    fun setContentDescription(description: String) {
        when {
            isGoogle() -> googleMap.setContentDescription(description)
            isHuawei() -> huaweiMap.setContentDescription(description)
        }
    }

    fun setOnMapClickListener(listener: OnMapClickListener) {
        when {
            isGoogle() -> googleMap.setOnMapClickListener {
                listener.onMapClick(LatLng(it.latitude, it.longitude))
            }
            isHuawei() -> huaweiMap.setOnMapClickListener {
                listener.onMapClick(LatLng(it.latitude, it.longitude))
            }
        }
    }

    fun setOnMapClickListener(listener: (LatLng) -> Unit) {
        setOnMapClickListener(object : OnMapClickListener {
            override fun onMapClick(position: LatLng) {
                listener(position)
            }
        })
    }

    fun setOnMapLongClickListener(listener: OnMapLongClickListener) {
        when {
            isGoogle() -> googleMap.setOnMapLongClickListener {
                listener.onMapLongClick(LatLng(it.latitude, it.longitude))
            }
            isHuawei() -> huaweiMap.setOnMapLongClickListener {
                listener.onMapLongClick(LatLng(it.latitude, it.longitude))
            }
        }
    }

    fun setOnMapLongClickListener(listener: (LatLng) -> Unit) {
        setOnMapLongClickListener(object : OnMapLongClickListener {
            override fun onMapLongClick(position: LatLng) {
                listener(position)
            }
        })
    }

    fun setOnCameraMoveStartedListener(listener: OnCameraMoveStartedListener) {
        when {
            isGoogle() -> googleMap.setOnCameraMoveStartedListener {
                listener.onCameraMoveStarted(it)
            }
            isHuawei() -> huaweiMap.setOnCameraMoveStartedListener {
                listener.onCameraMoveStarted(it)
            }
        }
    }

    fun setOnCameraMoveListener(listener: OnCameraMoveListener) {
        when {
            isGoogle() -> googleMap.setOnCameraMoveListener {
                listener.onCameraMove()
            }
            isHuawei() -> huaweiMap.setOnCameraMoveListener {
                listener.onCameraMove()
            }
        }
    }

    fun setOnCameraMoveCanceledListener(listener: OnCameraMoveCanceledListener) {
        when {
            isGoogle() -> googleMap.setOnCameraMoveCanceledListener {
                listener.onCameraMoveCanceled()
            }
            isHuawei() -> huaweiMap.setOnCameraMoveCanceledListener {
                listener.onCameraMoveCanceled()
            }
        }
    }

    fun setOnCameraIdleListener(listener: OnCameraIdleListener) {
        when {
            isGoogle() -> googleMap.setOnCameraIdleListener {
                listener.onCameraIdle()
            }
            isHuawei() -> huaweiMap.setOnCameraIdleListener {
                listener.onCameraIdle()
            }
        }
    }

    fun setOnMarkerDragListener(listener: OnMarkerDragListener) {
        when {
            isGoogle() -> googleMap.setOnMarkerDragListener(object : GoogleMap.OnMarkerDragListener {
                override fun onMarkerDragEnd(marker: com.google.android.gms.maps.model.Marker?) {
                    listener.onMarkerDragEnd(Marker(marker, null))
                }
                override fun onMarkerDragStart(marker: com.google.android.gms.maps.model.Marker?) {
                    listener.onMarkerDragStart(Marker(marker, null))
                }
                override fun onMarkerDrag(marker: com.google.android.gms.maps.model.Marker?) {
                    listener.onMarkerDragStart(Marker(marker, null))
                }
            })
            isHuawei() -> huaweiMap.setOnMarkerDragListener(object : HuaweiMap.OnMarkerDragListener {
                override fun onMarkerDragEnd(marker: com.huawei.hms.maps.model.Marker?) {
                    listener.onMarkerDragEnd(Marker(null, marker))
                }
                override fun onMarkerDragStart(marker: com.huawei.hms.maps.model.Marker?) {
                    listener.onMarkerDragStart(Marker(null, marker))
                }
                override fun onMarkerDrag(marker: com.huawei.hms.maps.model.Marker?) {
                    listener.onMarkerDragStart(Marker(null, marker))
                }
            })
        }
    }

    fun setOnCircleClickListener(listener: OnCircleClickListener) {
        when {
            isGoogle() -> googleMap.setOnCircleClickListener {
                listener.onCircleClick(Circle(it, null))
            }
            isHuawei() -> huaweiMap.setOnCircleClickListener {
                listener.onCircleClick(Circle(null, it))
            }
        }
    }

    fun setOnCircleClickListener(listener: (Circle) -> Unit) {
        setOnCircleClickListener(object : OnCircleClickListener {
            override fun onCircleClick(circle: Circle) {
                listener(circle)
            }
        })
    }

    fun setOnMarkerClickListener(listener: OnMarkerClickListener) {
        when {
            isGoogle() -> googleMap.setOnMarkerClickListener {
                listener.onMarkerClick(Marker(it, null))
            }
            isHuawei() -> huaweiMap.setOnMarkerClickListener {
                listener.onMarkerClick(Marker(null, it))
            }
        }
    }

    fun setOnMarkerClickListener(listener: (Marker) -> Boolean) {
        setOnMarkerClickListener(object : OnMarkerClickListener {
            override fun onMarkerClick(marker: Marker): Boolean = listener(marker)
        })
    }

    fun setOnInfoWindowCloseListener(listener: OnInfoWindowCloseListener) {
        when {
            isGoogle() -> googleMap.setOnInfoWindowCloseListener {
                listener.onInfoWindowClose(Marker(it, null))
            }
            isHuawei() -> huaweiMap.setOnInfoWindowCloseListener {
                listener.onInfoWindowClose(Marker(null, it))
            }
        }
    }

    fun setOnInfoWindowLongClickListener(listener: OnInfoWindowLongClickListener) {
        when {
            isGoogle() -> googleMap.setOnInfoWindowLongClickListener {
                listener.onInfoWindowLongClick(Marker(it, null))
            }
            isHuawei() -> huaweiMap.setOnInfoWindowLongClickListener {
                listener.onInfoWindowLongClick(Marker(null, it))
            }
        }
    }

    fun setOnInfoWindowClickListener(listener: OnInfoWindowClickListener) {
        when {
            isGoogle() -> googleMap.setOnInfoWindowClickListener {
                listener.onInfoWindowClick(Marker(it, null))
            }
            isHuawei() -> huaweiMap.setOnInfoWindowClickListener {
                listener.onInfoWindowClick(Marker(null, it))
            }
        }
    }

    companion object {
        const val MAP_TYPE_NONE = GoogleMap.MAP_TYPE_NONE
        const val MAP_TYPE_NORMAL = GoogleMap.MAP_TYPE_NORMAL
        const val MAP_TYPE_SATELLITE = GoogleMap.MAP_TYPE_SATELLITE
        const val MAP_TYPE_TERRAIN = GoogleMap.MAP_TYPE_TERRAIN
        const val MAP_TYPE_HYBRID = GoogleMap.MAP_TYPE_HYBRID
    }

    interface OnMapClickListener {
        fun onMapClick(position: LatLng)
    }

    interface OnMapLongClickListener {
        fun onMapLongClick(position: LatLng)
    }

    interface OnCameraMoveStartedListener {

        fun onCameraMoveStarted(reason: Int)

        companion object {
            const val REASON_GESTURE = GoogleMap.OnCameraMoveStartedListener.REASON_GESTURE
            const val REASON_API_ANIMATION = GoogleMap.OnCameraMoveStartedListener.REASON_API_ANIMATION
            const val REASON_DEVELOPER_ANIMATION = GoogleMap.OnCameraMoveStartedListener.REASON_DEVELOPER_ANIMATION
        }
    }

    interface OnCameraMoveListener {
        fun onCameraMove()
    }

    interface OnCameraMoveCanceledListener {
        fun onCameraMoveCanceled()
    }

    interface OnCameraIdleListener {
        fun onCameraIdle()
    }

    interface CancelableCallback {
        fun onFinish()
        fun onCancel()
    }

    interface OnMarkerDragListener {
        fun onMarkerDragStart(marker: Marker)
        fun onMarkerDrag(marker: Marker)
        fun onMarkerDragEnd(marker: Marker)
    }

    interface OnCircleClickListener {
        fun onCircleClick(circle: Circle)
    }

    interface OnMarkerClickListener {
        fun onMarkerClick(marker: Marker): Boolean
    }

    interface OnInfoWindowCloseListener {
        fun onInfoWindowClose(marker: Marker)
    }

    interface OnInfoWindowLongClickListener {
        fun onInfoWindowLongClick(marker: Marker)
    }

    interface OnInfoWindowClickListener {
        fun onInfoWindowClick(marker: Marker)
    }

    interface InfoWindowAdapter {
        fun getInfoWindow(marker: Marker): View?
        fun getInfoContents(marker: Marker): View?
    }
}
