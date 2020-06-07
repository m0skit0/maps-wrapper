package org.m0skit0.android.mapswrapper

import android.view.View
import com.google.android.gms.maps.GoogleMap
import com.huawei.hms.maps.HuaweiMap

class CommonMap(private val map: Any) {

    private val google: GoogleMap
        get() = map as GoogleMap

    private val huawei: HuaweiMap
        get() = map as HuaweiMap

    var mapType: Int
        get() = googleOrHuawei({ mapType }, { mapType })
        set(value) {
            googleOrHuawei(
                { mapType = value },
                { mapType = value }
            )
        }

    var isMyLocationEnabled: Boolean
        get() = googleOrHuawei({ isMyLocationEnabled }, { isMyLocationEnabled })
        set(value) {
            googleOrHuawei(
                { isMyLocationEnabled = value },
                { isMyLocationEnabled = value }
            )
        }

    val cameraPosition: CameraPosition
        get() =
            googleOrHuawei(
                { cameraPosition.let { CameraPosition(it, null) } },
                { cameraPosition.let { CameraPosition(null, it) } }
            )

    val uiSettings: UiSettings
        get() =
            googleOrHuawei(
                { uiSettings.let { UiSettings(it, null) } },
                { uiSettings.let { UiSettings(null, it) } }
            )

    val projection: Projection
        get() =
            googleOrHuawei(
                { projection.let { Projection(it, null) } },
                { projection.let { Projection(null, it) } }
            )

    var isTrafficEnabled: Boolean
        get() = googleOrHuawei({ isTrafficEnabled }, { isTrafficEnabled })
        set(value) {
            googleOrHuawei(
                { isTrafficEnabled = value },
                { isTrafficEnabled = value }
            )
        }

    var isIndoorEnabled: Boolean
        get() = googleOrHuawei({ isIndoorEnabled }, { isIndoorEnabled })
        set(value) {
            googleOrHuawei(
                { isIndoorEnabled = value },
                { isIndoorEnabled = value }
            )
        }

    var isBuildingsEnabled: Boolean
        get() = googleOrHuawei({ isBuildingsEnabled }, { isBuildingsEnabled })
        set(value) {
            googleOrHuawei(
                { isBuildingsEnabled = value },
                { isBuildingsEnabled = value }
            )
        }

    private fun isGoogle(): Boolean = map is GoogleMap

    private fun isHuawei(): Boolean = map is HuaweiMap

    // TODO Migrate all functions to call this function
    private inline fun <T> googleOrHuawei(google: GoogleMap.() -> T, huawei: HuaweiMap.() -> T): T =
        when {
            isGoogle() -> this.google.google()
            isHuawei() -> this.huawei.huawei()
            else -> throwUnableToResolveGoogleOrHuawei()
        }

    fun clear() {
        googleOrHuawei({ clear() }, { clear() })
    }

    fun moveCamera(cameraUpdate: CameraUpdate) {
        googleOrHuawei(
            { moveCamera(cameraUpdate.google) },
            { moveCamera(cameraUpdate.huawei) }
        )
    }

    fun animateCamera(cameraUpdate: CameraUpdate) {
        googleOrHuawei(
            { animateCamera(cameraUpdate.google) },
            { animateCamera(cameraUpdate.huawei) }
        )
    }

    fun animateCamera(cameraUpdate: CameraUpdate, callback: CancelableCallback?) {
        googleOrHuawei(
            {
                animateCamera(
                    cameraUpdate.google,
                    object : GoogleMap.CancelableCallback {
                        override fun onFinish() {
                            callback?.onFinish()
                        }
                        override fun onCancel() {
                            callback?.onCancel()
                        }
                    }
                )
            }, {
                animateCamera(
                    cameraUpdate.huawei,
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
        )
    }

    fun animateCamera(cameraUpdate: CameraUpdate, value: Int, callback: CancelableCallback?) {
        when {
            isGoogle() -> google.animateCamera(
                cameraUpdate.google,
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
            isHuawei() -> huawei.animateCamera(
                cameraUpdate.huawei,
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

    fun stopAnimation() {
        googleOrHuawei(
            { google.stopAnimation() },
            { huawei.stopAnimation() }
        )
    }

    fun addCircle(circleOptions: CircleOptions): Circle =
        when {
            isGoogle() -> google.addCircle(circleOptions.google).let { Circle(it, null) }
            isHuawei() -> huawei.addCircle(circleOptions.huawei).let { Circle(null, it) }
            else -> throwUnableToResolveGoogleOrHuawei()
        }

    fun addMarker(markerOptions: MarkerOptions): Marker =
        when {
            isGoogle() -> google.addMarker(markerOptions.google).let { Marker(it, null) }
            isHuawei() -> huawei.addMarker(markerOptions.huawei).let { Marker(null, it) }
            else -> throwUnableToResolveGoogleOrHuawei()
        }

    fun addPolyline(polylineOptions: PolylineOptions): Polyline =
        when {
            isGoogle() -> google.addPolyline(polylineOptions.google).let { Polyline(it, null) }
            isHuawei() -> huawei.addPolyline(polylineOptions.huawei).let { Polyline(null, it) }
            else -> throwUnableToResolveGoogleOrHuawei()
        }

    fun addPolygon(polygonOptions: PolygonOptions): Polygon =
        googleOrHuawei(
            { google.addPolygon(polygonOptions.google).let { Polygon(it, null) } },
            { huawei.addPolygon(polygonOptions.huawei).let { Polygon(null, it) } }
        )

    fun addGroundOverlay(groundOverlayOptions: GroundOverlayOptions): GroundOverlay =
        googleOrHuawei(
            { addGroundOverlay(groundOverlayOptions.google).let { GroundOverlay(it, null) } },
            { addGroundOverlay(groundOverlayOptions.huawei).let { GroundOverlay(null, it) } }
        )

    fun setPadding(left: Int, right: Int, top: Int, bottom: Int) {
        googleOrHuawei(
            { setPadding(left, right, top, bottom) },
            { setPadding(left, right, top, bottom) }
        )
    }

    fun setInfoWindowAdapter(adapter: InfoWindowAdapter) {
        googleOrHuawei(
            {
                setInfoWindowAdapter(object : GoogleMap.InfoWindowAdapter {
                    override fun getInfoContents(marker: com.google.android.gms.maps.model.Marker?): View? =
                        adapter.getInfoContents(Marker(marker, null))
                    override fun getInfoWindow(marker: com.google.android.gms.maps.model.Marker?): View? =
                        adapter.getInfoWindow(Marker(marker, null))
                })
            },
            {
                setInfoWindowAdapter(object : HuaweiMap.InfoWindowAdapter {
                    override fun getInfoContents(marker: com.huawei.hms.maps.model.Marker?): View? =
                        adapter.getInfoContents(Marker(null, marker))
                    override fun getInfoWindow(marker: com.huawei.hms.maps.model.Marker?): View? =
                        adapter.getInfoWindow(Marker(null, marker))
                })
            }
        )
    }

    fun setContentDescription(description: String) {
        googleOrHuawei({ setContentDescription(description) }, { setContentDescription(description) })
    }

    fun setOnMapClickListener(listener: OnMapClickListener) {
        googleOrHuawei(
            { setOnMapClickListener { listener.onMapClick(LatLng(it.latitude, it.longitude)) } },
            { setOnMapClickListener { listener.onMapClick(LatLng(it.latitude, it.longitude)) } }
        )
    }

    fun setOnMapClickListener(listener: (LatLng) -> Unit) {
        setOnMapClickListener(object : OnMapClickListener {
            override fun onMapClick(position: LatLng) {
                listener(position)
            }
        })
    }

    fun setOnMapLongClickListener(listener: OnMapLongClickListener) {
        googleOrHuawei(
            { setOnMapLongClickListener { listener.onMapLongClick(LatLng(it.latitude, it.longitude)) } },
            { setOnMapLongClickListener { listener.onMapLongClick(LatLng(it.latitude, it.longitude)) } }
        )
    }

    fun setOnMapLongClickListener(listener: (LatLng) -> Unit) {
        setOnMapLongClickListener(object : OnMapLongClickListener {
            override fun onMapLongClick(position: LatLng) {
                listener(position)
            }
        })
    }

    fun setOnCameraMoveStartedListener(listener: OnCameraMoveStartedListener) {
        googleOrHuawei(
            { setOnCameraMoveStartedListener { listener.onCameraMoveStarted(it) } },
            { setOnCameraMoveStartedListener { listener.onCameraMoveStarted(it) } }
        )
    }

    fun setOnCameraMoveListener(listener: OnCameraMoveListener) {
        googleOrHuawei(
            { setOnCameraMoveListener { listener.onCameraMove() } },
            { setOnCameraMoveListener { listener.onCameraMove() } }
        )
    }

    fun setOnCameraMoveCanceledListener(listener: OnCameraMoveCanceledListener) {
        googleOrHuawei(
            { setOnCameraMoveCanceledListener { listener.onCameraMoveCanceled() } },
            { setOnCameraMoveCanceledListener { listener.onCameraMoveCanceled() } }
        )
    }

    fun setOnCameraIdleListener(listener: OnCameraIdleListener) {
        googleOrHuawei(
            { setOnCameraIdleListener { listener.onCameraIdle() } },
            { setOnCameraIdleListener { listener.onCameraIdle() } }
        )
    }

    fun setOnCameraIdleListener(listener: () -> Unit) {
        setOnCameraIdleListener(object : OnCameraIdleListener {
            override fun onCameraIdle() {
                listener()
            }
        })
    }

    fun setOnMarkerDragListener(listener: OnMarkerDragListener) {
        googleOrHuawei(
            {
                setOnMarkerDragListener(object : GoogleMap.OnMarkerDragListener {
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
            },
            {
                setOnMarkerDragListener(object : HuaweiMap.OnMarkerDragListener {
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
        )
    }

    fun setOnCircleClickListener(listener: OnCircleClickListener) {
        when {
            isGoogle() -> google.setOnCircleClickListener {
                listener.onCircleClick(Circle(it, null))
            }
            isHuawei() -> huawei.setOnCircleClickListener {
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
            isGoogle() -> google.setOnMarkerClickListener {
                listener.onMarkerClick(Marker(it, null))
            }
            isHuawei() -> huawei.setOnMarkerClickListener {
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
            isGoogle() -> google.setOnInfoWindowCloseListener {
                listener.onInfoWindowClose(Marker(it, null))
            }
            isHuawei() -> huawei.setOnInfoWindowCloseListener {
                listener.onInfoWindowClose(Marker(null, it))
            }
        }
    }

    fun setOnInfoWindowLongClickListener(listener: OnInfoWindowLongClickListener) {
        when {
            isGoogle() -> google.setOnInfoWindowLongClickListener {
                listener.onInfoWindowLongClick(Marker(it, null))
            }
            isHuawei() -> huawei.setOnInfoWindowLongClickListener {
                listener.onInfoWindowLongClick(Marker(null, it))
            }
        }
    }

    fun setOnInfoWindowClickListener(listener: OnInfoWindowClickListener) {
        when {
            isGoogle() -> google.setOnInfoWindowClickListener {
                listener.onInfoWindowClick(Marker(it, null))
            }
            isHuawei() -> huawei.setOnInfoWindowClickListener {
                listener.onInfoWindowClick(Marker(null, it))
            }
        }
    }

    fun setOnPolygonClickListener(listener: (Polygon) -> Unit) {
        setOnPolygonClickListener(object : OnPolygonClickListener {
            override fun onPolygonClick(polygon: Polygon) {
                listener(polygon)
            }
        })
    }

    fun setOnPolygonClickListener(listener: OnPolygonClickListener) {
        googleOrHuawei(
            { setOnPolygonClickListener { listener.onPolygonClick(Polygon(it, null)) } },
            { setOnPolygonClickListener { listener.onPolygonClick(Polygon(null, it)) } }
        )
    }

    fun setOnPolylineClickListener(listener: OnPolylineClickListener) {
        googleOrHuawei(
            { setOnPolylineClickListener { listener.onPolylineClick(Polyline(it, null)) } },
            { setOnPolylineClickListener { listener.onPolylineClick(Polyline(null, it)) } }
        )
    }

    fun setOnPolylineClickListener(listener: (Polyline) -> Unit) {
        setOnPolylineClickListener(object : OnPolylineClickListener {
            override fun onPolylineClick(polyline: Polyline) {
                listener(polyline)
            }
        })
    }

    fun setOnGroundOverlayClickListener(listener: OnGroundOverlayClickListener) {
        googleOrHuawei(
            { setOnGroundOverlayClickListener { listener.onGroundOverlayClick(GroundOverlay(it, null)) } },
            { setOnGroundOverlayClickListener { listener.onGroundOverlayClick(GroundOverlay(null, it)) } }
        )
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

    interface OnPolygonClickListener {
        fun onPolygonClick(polygon: Polygon)
    }

    interface OnPolylineClickListener {
        fun onPolylineClick(polyline: Polyline)
    }

    interface OnGroundOverlayClickListener {
        fun onGroundOverlayClick(groundOverlay: GroundOverlay)
    }
}
