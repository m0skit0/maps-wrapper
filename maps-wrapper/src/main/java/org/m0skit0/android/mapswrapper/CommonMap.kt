package org.m0skit0.android.mapswrapper

import com.google.android.gms.maps.GoogleMap
import com.huawei.hms.maps.HuaweiMap

class CommonMap(private val map: Any) {

    private val googleMap: GoogleMap
        get() = map as GoogleMap

    private val huaweiMap: HuaweiMap
        get() = map as HuaweiMap

    private val notGoogleNotHuaweiMap: IllegalStateException
        get() = IllegalStateException("Map is neither Google nor Huawei")

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
            else -> throw notGoogleNotHuaweiMap
        }

    val uiSettings: UiSettings
        get() = when {
            isGoogle() -> googleMap.uiSettings.let { UiSettings(it, null) }
            isHuawei() -> huaweiMap.uiSettings.let { UiSettings(null, it) }
            else -> throw notGoogleNotHuaweiMap
        }

    val projection: Projection
        get() = when {
            isGoogle() -> googleMap.projection.let { Projection(it, null) }
            isHuawei() -> huaweiMap.projection.let { Projection(null, it) }
            else -> throw notGoogleNotHuaweiMap
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
            else -> throw notGoogleNotHuaweiMap
        }

    fun addMarker(markerOptions: MarkerOptions): Marker =
        when {
            isGoogle() -> googleMap.addMarker(markerOptions.google).let { Marker(it, null) }
            isHuawei() -> huaweiMap.addMarker(markerOptions.huawei).let { Marker(null, it) }
            else -> throw notGoogleNotHuaweiMap
        }

    fun addPolyline(polylineOptions: PolylineOptions): Polyline =
        when {
            isGoogle() -> googleMap.addPolyline(polylineOptions.google).let { Polyline(it, null) }
            isHuawei() -> huaweiMap.addPolyline(polylineOptions.huawei).let { Polyline(null, it) }
            else -> throw notGoogleNotHuaweiMap
        }

    fun stopAnimation() {
        when {
            isGoogle() -> googleMap.stopAnimation()
            isHuawei() -> huaweiMap.stopAnimation()
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
}
