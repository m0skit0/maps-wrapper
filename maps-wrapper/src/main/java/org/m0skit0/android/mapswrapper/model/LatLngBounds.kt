package org.m0skit0.android.mapswrapper.model

class LatLngBounds {

    internal val google: com.google.android.gms.maps.model.LatLngBounds?
    internal val huawei: com.huawei.hms.maps.model.LatLngBounds?

    internal constructor(
        google: com.google.android.gms.maps.model.LatLngBounds?,
        huawei: com.huawei.hms.maps.model.LatLngBounds?
    ) {
        this.google = google
        this.huawei = huawei
    }

    internal constructor(google: com.google.android.gms.maps.model.LatLngBounds?) : this(google, null)
    internal constructor(huawei: com.huawei.hms.maps.model.LatLngBounds?) : this(null, huawei)

    constructor(latLng1: LatLng, latLng2: LatLng) {
        this.google = com.google.android.gms.maps.model.LatLngBounds(latLng1.google, latLng2.google)
        this.huawei = com.huawei.hms.maps.model.LatLngBounds(latLng1.huawei, latLng2.huawei)
    }

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
