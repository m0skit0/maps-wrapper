package org.m0skit0.android.mapswrapper

import android.location.Location
import android.view.View
import androidx.annotation.RequiresPermission
import com.google.android.gms.maps.GoogleMap
import com.huawei.hms.maps.HuaweiMap
import org.m0skit0.android.mapswrapper.model.*

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

    val cameraPosition: CameraPosition
        get() =
            googleOrHuawei(
                { cameraPosition.asWrapper() },
                { cameraPosition.asWrapper() }
            )

    val uiSettings: UiSettings
        get() =
            googleOrHuawei(
                { uiSettings.asWrapper() },
                { uiSettings.asWrapper() }
            )

    val projection: Projection
        get() =
            googleOrHuawei(
                { projection.asWrapper() },
                { projection.asWrapper() }
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

    @Deprecated("This method is deprecated. Use FusedLocationProviderApi instead")
    val myLocation: Location
        get() = googleOrHuawei({ myLocation }, { throwNotSupported() })

    private fun isGoogle(): Boolean = map is GoogleMap

    private fun isHuawei(): Boolean = map is HuaweiMap

    private inline fun <T> googleOrHuawei(google: GoogleMap.() -> T, huawei: HuaweiMap.() -> T): T =
        when {
            isGoogle() -> this.google.google()
            isHuawei() -> this.huawei.huawei()
            else -> throwUnableToResolveGoogleOrHuawei()
        }


    var isMyLocationEnabled: Boolean = false
        @RequiresPermission(anyOf = ["android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"])
        set(value) {
            field = value
            googleOrHuawei(
                { isMyLocationEnabled = value },
                { isMyLocationEnabled = value }
            )
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
        googleOrHuawei(
            { addCircle(circleOptions.google).asWrapper() },
            { addCircle(circleOptions.huawei).asWrapper() }
        )

    fun addMarker(markerOptions: MarkerOptions): Marker =
        googleOrHuawei(
            { addMarker(markerOptions.google).asWrapper() },
            { addMarker(markerOptions.huawei).asWrapper() }
        )

    fun addPolyline(polylineOptions: PolylineOptions?): Polyline =
        googleOrHuawei(
            { addPolyline(polylineOptions?.google).asWrapper() },
            { addPolyline(polylineOptions?.huawei).asWrapper() }
        )

    fun addPolygon(polygonOptions: PolygonOptions): Polygon =
        googleOrHuawei(
            { google.addPolygon(polygonOptions.google).asWrapper() },
            { huawei.addPolygon(polygonOptions.huawei).asWrapper() }
        )

    fun addGroundOverlay(groundOverlayOptions: GroundOverlayOptions): GroundOverlay =
        googleOrHuawei(
            { addGroundOverlay(groundOverlayOptions.google).asWrapper() },
            { addGroundOverlay(groundOverlayOptions.huawei).asWrapper() }
        )

    fun addTileOverlay(options: TileOverlayOptions): TileOverlay =
        googleOrHuawei(
            { addTileOverlay(options.google).asWrapper() },
            { addTileOverlay(options.huawei).asWrapper() }
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
                        marker?.run { adapter.getInfoContents(asWrapper()) }

                    override fun getInfoWindow(marker: com.google.android.gms.maps.model.Marker?): View? =
                        marker?.run { adapter.getInfoWindow(asWrapper()) }
                })
            },
            {
                setInfoWindowAdapter(object : HuaweiMap.InfoWindowAdapter {
                    override fun getInfoContents(marker: com.huawei.hms.maps.model.Marker?): View? =
                        marker?.run { adapter.getInfoContents(asWrapper()) }

                    override fun getInfoWindow(marker: com.huawei.hms.maps.model.Marker?): View? =
                        marker?.run { adapter.getInfoWindow(asWrapper()) }
                })
            }
        )
    }

    fun setContentDescription(description: String) {
        googleOrHuawei(
            { setContentDescription(description) },
            { setContentDescription(description) })
    }

    fun setOnMapClickListener(listener: OnMapClickListener) {
        googleOrHuawei(
            { setOnMapClickListener { listener.onMapClick(it.asWrapper()) } },
            { setOnMapClickListener { listener.onMapClick(it.asWrapper()) } }
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
            {
                setOnMapLongClickListener {
                    listener.onMapLongClick(it.asWrapper())
                }
            },
            {
                setOnMapLongClickListener {
                    listener.onMapLongClick(it.asWrapper())
                }
            }
        )
    }

    fun setOnMapLongClickListener(listener: (LatLng) -> Unit) {
        setOnMapLongClickListener(object : OnMapLongClickListener {
            override fun onMapLongClick(position: LatLng) {
                listener(position)
            }
        })
    }

    fun setOnMapLoadedCallback(listener: OnMapLoadedCallback) {
        googleOrHuawei(
            { setOnMapLoadedCallback { listener.onMapLoaded() } },
            { setOnMapLoadedCallback { listener.onMapLoaded() } }
        )
    }

    fun setOnMapLongClickListener(listener: () -> Unit) {
        setOnMapLoadedCallback(object : OnMapLoadedCallback {
            override fun onMapLoaded() {
                listener()
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
        googleOrHuawei(
            {
                setOnCircleClickListener {
                    listener.onCircleClick(Circle(it))
                }
            },
            {
                setOnCircleClickListener {
                    listener.onCircleClick(Circle(it))
                }
            }
        )
    }

    fun setOnCircleClickListener(listener: (Circle) -> Unit) {
        setOnCircleClickListener(object : OnCircleClickListener {
            override fun onCircleClick(circle: Circle) {
                listener(circle)
            }
        })
    }

    fun setOnMarkerClickListener(listener: OnMarkerClickListener) {
        googleOrHuawei(
            {
                setOnMarkerClickListener {
                    listener.onMarkerClick(Marker(it))
                }
            },
            {
                setOnMarkerClickListener {
                    listener.onMarkerClick(Marker(it))
                }
            }
        )
    }

    fun setOnMarkerClickListener(listener: (Marker?) -> Boolean) {
        setOnMarkerClickListener(object : OnMarkerClickListener {
            override fun onMarkerClick(marker: Marker?): Boolean = listener(marker)
        })
    }

    fun setOnInfoWindowCloseListener(listener: OnInfoWindowCloseListener) {
        googleOrHuawei(
            {
                setOnInfoWindowCloseListener {
                    listener.onInfoWindowClose(Marker(it))
                }
            },
            {
                setOnInfoWindowCloseListener {
                    listener.onInfoWindowClose(Marker(it))
                }
            }
        )
    }

    fun setOnInfoWindowLongClickListener(listener: OnInfoWindowLongClickListener) {
        googleOrHuawei(
            {
                setOnInfoWindowLongClickListener {
                    listener.onInfoWindowLongClick(Marker(it))
                }
            },
            {
                setOnInfoWindowLongClickListener {
                    listener.onInfoWindowLongClick(Marker(it))
                }
            }
        )
    }

    fun setOnInfoWindowClickListener(listener: OnInfoWindowClickListener) {
        googleOrHuawei(
            {
                setOnInfoWindowClickListener {
                    listener.onInfoWindowClick(Marker(it))
                }
            },
            {
                setOnInfoWindowClickListener {
                    listener.onInfoWindowClick(Marker(it))
                }
            }
        )
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
            { setOnPolygonClickListener { listener.onPolygonClick(Polygon(it)) } },
            { setOnPolygonClickListener { listener.onPolygonClick(Polygon(it)) } }
        )
    }

    fun setOnPolylineClickListener(listener: OnPolylineClickListener) {
        googleOrHuawei(
            { setOnPolylineClickListener { listener.onPolylineClick(Polyline(it)) } },
            { setOnPolylineClickListener { listener.onPolylineClick(Polyline(it)) } }
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
            {
                setOnGroundOverlayClickListener {
                    listener.onGroundOverlayClick(GroundOverlay(it))
                }
            },
            {
                setOnGroundOverlayClickListener {
                    listener.onGroundOverlayClick(GroundOverlay(it))
                }
            }
        )
    }

    fun setOnMyLocationButtonClickListener(listener: OnMyLocationButtonClickListener) {
        googleOrHuawei(
            { setOnMapLongClickListener { listener.onMyLocationButtonClick() } },
            { setOnMapLongClickListener { listener.onMyLocationButtonClick() } }
        )
    }

    fun setOnMyLocationButtonClickListener(listener: () -> Unit) {
        setOnMyLocationButtonClickListener(object : OnMyLocationButtonClickListener {
            override fun onMyLocationButtonClick() {
                listener()
            }
        })
    }

    fun setOnMyLocationClickListener(listener: OnMyLocationClickListener) {
        googleOrHuawei(
            { setOnMyLocationClickListener { location -> listener.onMyLocationClick(location) } },
            { setOnMyLocationClickListener { location -> listener.onMyLocationClick(location) } }
        )
    }

    fun setOnMyLocationClickListener(listener: (Location) -> Unit) {
        setOnMyLocationClickListener(object : OnMyLocationClickListener {
            override fun onMyLocationClick(location: Location) {
                listener(location)
            }
        })
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
        fun onMarkerClick(marker: Marker?): Boolean
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

    interface OnMapLoadedCallback {
        fun onMapLoaded()
    }

    interface OnMyLocationButtonClickListener {
        fun onMyLocationButtonClick()
    }

    interface OnMyLocationClickListener {
        fun onMyLocationClick(location: Location)
    }
}
