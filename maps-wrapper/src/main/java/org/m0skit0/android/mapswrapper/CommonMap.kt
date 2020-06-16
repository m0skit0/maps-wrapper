package org.m0skit0.android.mapswrapper

import android.view.View
import com.google.android.gms.maps.GoogleMap
import com.huawei.hms.maps.HuaweiMap
import org.koin.core.get
import org.koin.core.parameter.parametersOf
import org.m0skit0.android.mapswrapper.di.KoinInterface
import org.m0skit0.android.mapswrapper.di.getGoogle
import org.m0skit0.android.mapswrapper.di.getHuawei

class CommonMap(private val map: Any) : KoinInterface {

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
                { getGoogle(cameraPosition) },
                { getHuawei(cameraPosition) }
            )

    val uiSettings: UiSettings
        get() =
            googleOrHuawei(
                { getGoogle(uiSettings) },
                { getHuawei(uiSettings) }
            )

    val projection: Projection
        get() =
            googleOrHuawei(
                { getGoogle(projection) },
                { getHuawei(projection) }
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
                    get { parametersOf(callback) }
                )
            }, {
                animateCamera(
                    cameraUpdate.huawei,
                    get { parametersOf(callback) }
                )
            }
        )
    }

    fun animateCamera(cameraUpdate: CameraUpdate, value: Int, callback: CancelableCallback?) {
        when {
            isGoogle() -> google.animateCamera(
                cameraUpdate.google,
                value,
                get { parametersOf(callback) }
            )
            isHuawei() -> huawei.animateCamera(
                cameraUpdate.huawei,
                value,
                get { parametersOf(callback) }
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
            isGoogle() -> google.addCircle(circleOptions.google).let { getGoogle(it) }
            isHuawei() -> huawei.addCircle(circleOptions.huawei).let { getHuawei(it) }
            else -> throwUnableToResolveGoogleOrHuawei()
        }

    fun addMarker(markerOptions: MarkerOptions): Marker =
        when {
            isGoogle() -> google.addMarker(markerOptions.google).let { getGoogle(it) }
            isHuawei() -> huawei.addMarker(markerOptions.huawei).let { getHuawei(it) }
            else -> throwUnableToResolveGoogleOrHuawei()
        }

    fun addPolyline(polylineOptions: PolylineOptions): Polyline =
        when {
            isGoogle() -> google.addPolyline(polylineOptions.google).let { getGoogle(it) }
            isHuawei() -> huawei.addPolyline(polylineOptions.huawei).let { getHuawei(it) }
            else -> throwUnableToResolveGoogleOrHuawei()
        }

    fun addPolygon(polygonOptions: PolygonOptions): Polygon =
        googleOrHuawei(
            { google.addPolygon(polygonOptions.google).let { getGoogle(it) } },
            { huawei.addPolygon(polygonOptions.huawei).let { getHuawei(it) } }
        )

    fun addGroundOverlay(groundOverlayOptions: GroundOverlayOptions): GroundOverlay =
        googleOrHuawei(
            { addGroundOverlay(groundOverlayOptions.google).let { getGoogle(it) } },
            { addGroundOverlay(groundOverlayOptions.huawei).let { getHuawei(it) } }
        )

    fun setPadding(left: Int, right: Int, top: Int, bottom: Int) {
        googleOrHuawei(
            { setPadding(left, right, top, bottom) },
            { setPadding(left, right, top, bottom) }
        )
    }

    fun setInfoWindowAdapter(adapter: InfoWindowAdapter) {
        googleOrHuawei(
            { setInfoWindowAdapter(get { parametersOf(adapter) }) },
            { setInfoWindowAdapter(get { parametersOf(adapter) }) }
        )
    }

    fun setContentDescription(description: String) {
        googleOrHuawei({ setContentDescription(description) }, { setContentDescription(description) })
    }

    fun setOnMapClickListener(listener: OnMapClickListener) {
        googleOrHuawei(
            { setOnMapClickListener { listener.onMapClick(get { parametersOf(it.latitude, it.longitude) }) } },
            { setOnMapClickListener { listener.onMapClick(get { parametersOf(it.latitude, it.longitude) }) } }
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
            { setOnMapLongClickListener { listener.onMapLongClick(get { parametersOf(it.latitude, it.longitude) }) } },
            { setOnMapLongClickListener { listener.onMapLongClick(get { parametersOf(it.latitude, it.longitude) }) } }
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
            { setOnMarkerDragListener(get { parametersOf(listener) }) },
            { setOnMarkerDragListener(get { parametersOf(listener) }) }
        )
    }

    fun setOnCircleClickListener(listener: OnCircleClickListener) {
        googleOrHuawei(
            {
                setOnCircleClickListener {
                    listener.onCircleClick(getGoogle(it))
                }
            },
            {
                setOnCircleClickListener {
                    listener.onCircleClick(getHuawei(it))
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
                    listener.onMarkerClick(getGoogle(it))
                }
            },
            {
                setOnMarkerClickListener {
                    listener.onMarkerClick(getHuawei(it))
                }
            }
        )
    }

    fun setOnMarkerClickListener(listener: (Marker) -> Boolean) {
        setOnMarkerClickListener(object : OnMarkerClickListener {
            override fun onMarkerClick(marker: Marker): Boolean = listener(marker)
        })
    }

    fun setOnInfoWindowCloseListener(listener: OnInfoWindowCloseListener) {
        googleOrHuawei(
            {
                setOnInfoWindowCloseListener {
                    listener.onInfoWindowClose(getGoogle(it))
                }
            },
            {
                setOnInfoWindowCloseListener {
                    listener.onInfoWindowClose(getHuawei(it))
                }
            }
        )
    }

    fun setOnInfoWindowLongClickListener(listener: OnInfoWindowLongClickListener) {
        googleOrHuawei(
            {
                setOnInfoWindowLongClickListener {
                    listener.onInfoWindowLongClick(getGoogle(it))
                }
            },
            {
                setOnInfoWindowLongClickListener {
                    listener.onInfoWindowLongClick(getHuawei(it))
                }
            }
        )
    }

    fun setOnInfoWindowClickListener(listener: OnInfoWindowClickListener) {
        googleOrHuawei(
            {
                setOnInfoWindowClickListener {
                    listener.onInfoWindowClick(getGoogle(it))
                }
            },
            {
                setOnInfoWindowClickListener {
                    listener.onInfoWindowClick(getHuawei(it))
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
            { setOnPolygonClickListener { listener.onPolygonClick(getGoogle(it)) } },
            { setOnPolygonClickListener { listener.onPolygonClick(getHuawei(it)) } }
        )
    }

    fun setOnPolylineClickListener(listener: OnPolylineClickListener) {
        googleOrHuawei(
            { setOnPolylineClickListener { listener.onPolylineClick(getGoogle(it)) } },
            { setOnPolylineClickListener { listener.onPolylineClick(getHuawei(it)) } }
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
            { setOnGroundOverlayClickListener { listener.onGroundOverlayClick(getGoogle(it)) } },
            { setOnGroundOverlayClickListener { listener.onGroundOverlayClick(getHuawei(it)) } }
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
