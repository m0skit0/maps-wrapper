package org.m0skit0.android.mapswrapper

class LatLngBounds internal constructor(
    internal val google: com.google.android.gms.maps.model.LatLngBounds?,
    internal val huawei: com.huawei.hms.maps.model.LatLngBounds?
) {

    class Builder {

        private var google = com.google.android.gms.maps.model.LatLngBounds.Builder()
        private var huawei = com.huawei.hms.maps.model.LatLngBounds.Builder()

        fun include(position: LatLng): Builder = apply {
            google = google.include(position.google)
            huawei = huawei.include(position.huawei)
        }

        fun build(): LatLngBounds = LatLngBounds(
            google.build(),
            huawei.build()
        )
    }
}
