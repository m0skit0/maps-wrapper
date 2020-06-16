package org.m0skit0.android.mapswrapper

import org.koin.core.get
import org.koin.core.parameter.parametersOf
import org.m0skit0.android.mapswrapper.di.MapsWrapperKoinComponent

class LatLngBounds : MapsWrapperKoinComponent {

    internal val google: com.google.android.gms.maps.model.LatLngBounds?
    internal val huawei: com.huawei.hms.maps.model.LatLngBounds?

    internal constructor(
        google: com.google.android.gms.maps.model.LatLngBounds?,
        huawei: com.huawei.hms.maps.model.LatLngBounds?
    ) {
        this.google = google
        this.huawei = huawei
    }

    constructor(latLng1: LatLng, latLng2: LatLng) {
        this.google = get { parametersOf(latLng1.google, latLng2.google) }
        this.huawei = get { parametersOf(latLng1.huawei, latLng2.huawei) }
    }

    class Builder : MapsWrapperKoinComponent {

        private var google: com.google.android.gms.maps.model.LatLngBounds.Builder = get()
        private var huawei: com.huawei.hms.maps.model.LatLngBounds.Builder = get()

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
