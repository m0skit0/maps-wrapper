package org.m0skit0.android.mapswrapper.model

import org.m0skit0.android.mapswrapper.throwUnableToResolveGoogleOrHuawei

class CameraPosition internal constructor(
    internal val google: com.google.android.gms.maps.model.CameraPosition?,
    internal val huawei: com.huawei.hms.maps.model.CameraPosition?
) {

    internal constructor(google: com.google.android.gms.maps.model.CameraPosition?) : this(google, null)
    internal constructor(huawei: com.huawei.hms.maps.model.CameraPosition?) : this(null, huawei)

    val target: LatLng
        get() = google?.target?.asWrapper()
            ?: huawei?.target?.asWrapper()
            ?: throwUnableToResolveGoogleOrHuawei()

    val zoom: Float
        get() = google?.zoom ?: huawei?.zoom ?: throwUnableToResolveGoogleOrHuawei()

    val tilt: Float
        get() = google?.tilt ?: huawei?.tilt ?: throwUnableToResolveGoogleOrHuawei()

    val bearing: Float
        get() = google?.bearing ?: huawei?.bearing ?: throwUnableToResolveGoogleOrHuawei()

    companion object {
        fun builder(): Builder = Builder()
        fun fromLatLngZoom(latLng: LatLng, zoom: Float): CameraPosition =
            CameraPosition(
                com.google.android.gms.maps.model.CameraPosition.fromLatLngZoom(latLng.google, zoom),
                com.huawei.hms.maps.model.CameraPosition.fromLatLngZoom(latLng.huawei, zoom)
            )
    }

    class Builder {

        private var google: com.google.android.gms.maps.model.CameraPosition.Builder? = null
        private var huawei: com.huawei.hms.maps.model.CameraPosition.Builder? = null

        constructor() {
            google = com.google.android.gms.maps.model.CameraPosition.Builder()
            huawei = com.huawei.hms.maps.model.CameraPosition.Builder()
        }

        constructor(cameraPosition: CameraPosition) {
            cameraPosition.google?.run {
                google = com.google.android.gms.maps.model.CameraPosition.Builder(this)
            }
            cameraPosition.huawei?.run {
                huawei = com.huawei.hms.maps.model.CameraPosition.Builder(this)
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

        fun build(): CameraPosition =
            CameraPosition(google?.build(), huawei?.build())
    }
}
