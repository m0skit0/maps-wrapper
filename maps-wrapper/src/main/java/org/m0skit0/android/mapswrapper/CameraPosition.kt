package org.m0skit0.android.mapswrapper

class CameraPosition internal constructor(
    internal val google: com.google.android.gms.maps.model.CameraPosition?,
    internal val huawei: com.huawei.hms.maps.model.CameraPosition?
) {

    companion object {
        fun builder(): Builder = Builder()
        fun fromLatLngZoom(latLng: LatLng, zoom: Float): CameraPosition =
            CameraPosition(
                com.google.android.gms.maps.model.CameraPosition.fromLatLngZoom(latLng.google, zoom),
                com.huawei.hms.maps.model.CameraPosition.fromLatLngZoom(latLng.huawei, zoom)
            )
    }

    class Builder {

        private var google = com.google.android.gms.maps.model.CameraPosition.Builder()
        private var huawei = com.huawei.hms.maps.model.CameraPosition.Builder()

        fun target(location: LatLng): Builder = apply {
            google = google.target(location.google)
            huawei = huawei.target(location.huawei)
        }

        fun zoom(zoom: Float): Builder = apply {
            google = google.zoom(zoom)
            huawei = huawei.zoom(zoom)
        }

        fun tilt(tilt: Float): Builder = apply {
            google = google.tilt(tilt)
            huawei = huawei.tilt(tilt)
        }

        fun bearing(bearing: Float): Builder = apply {
            google = google.bearing(bearing)
            huawei = huawei.bearing(bearing)
        }

        fun build(): CameraPosition =
            CameraPosition(google.build(), huawei.build())
    }
}
