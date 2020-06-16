package org.m0skit0.android.mapswrapper

import org.koin.core.get
import org.koin.core.parameter.parametersOf
import org.m0skit0.android.mapswrapper.di.KoinInterface

class CameraPosition internal constructor(
    internal val google: com.google.android.gms.maps.model.CameraPosition?,
    internal val huawei: com.huawei.hms.maps.model.CameraPosition?
) : KoinInterface {

    val target: LatLng
        get() = google?.target?.let { get { parametersOf(it.latitude, it.longitude) } }
            ?: huawei?.target?.let { get { parametersOf(it.latitude, it.longitude) } }
            ?: throwUnableToResolveGoogleOrHuawei()

    val zoom: Float
        get() = google?.zoom ?: huawei?.zoom ?: throwUnableToResolveGoogleOrHuawei()

    val tilt: Float
        get() = google?.tilt ?: huawei?.tilt ?: throwUnableToResolveGoogleOrHuawei()

    val bearing: Float
        get() = google?.bearing ?: huawei?.bearing ?: throwUnableToResolveGoogleOrHuawei()

    companion object : KoinInterface {
        fun builder(): Builder = get()
        fun fromLatLngZoom(latLng: LatLng, zoom: Float): CameraPosition =
            get {
                parametersOf(
                    com.google.android.gms.maps.model.CameraPosition.fromLatLngZoom(latLng.google, zoom),
                    com.huawei.hms.maps.model.CameraPosition.fromLatLngZoom(latLng.huawei, zoom)
                )
            }
    }

    class Builder : KoinInterface {

        private var google: com.google.android.gms.maps.model.CameraPosition.Builder? = null
        private var huawei: com.huawei.hms.maps.model.CameraPosition.Builder? = null

        constructor() {
            google = get()
            huawei = get()
        }

        constructor(cameraPosition: CameraPosition) {
            cameraPosition.google?.run {
                google = get { parametersOf(this) }
            }
            cameraPosition.huawei?.run {
                huawei = get { parametersOf(this) }
            }
        }

        fun target(location: LatLng): Builder = apply {
            google = google?.target(location.google)
            huawei = huawei?.target(location.huawei)
        }

        fun zoom(zoom: Float): Builder = apply {
            google = google?.zoom(zoom)
            huawei = huawei?.zoom(zoom)
        }

        fun tilt(tilt: Float): Builder = apply {
            google = google?.tilt(tilt)
            huawei = huawei?.tilt(tilt)
        }

        fun bearing(bearing: Float): Builder = apply {
            google = google?.bearing(bearing)
            huawei = huawei?.bearing(bearing)
        }

        fun build(): CameraPosition = get { parametersOf(google?.build(), huawei?.build()) }
    }
}
