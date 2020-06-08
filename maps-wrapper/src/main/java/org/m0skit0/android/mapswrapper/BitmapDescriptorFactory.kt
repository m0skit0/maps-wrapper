package org.m0skit0.android.mapswrapper

import android.graphics.Bitmap

object BitmapDescriptorFactory {

    const val HUE_RED = com.google.android.gms.maps.model.BitmapDescriptorFactory.HUE_RED
    const val HUE_ORANGE = com.google.android.gms.maps.model.BitmapDescriptorFactory.HUE_ORANGE
    const val HUE_YELLOW = com.google.android.gms.maps.model.BitmapDescriptorFactory.HUE_YELLOW
    const val HUE_GREEN = com.google.android.gms.maps.model.BitmapDescriptorFactory.HUE_GREEN
    const val HUE_CYAN = com.google.android.gms.maps.model.BitmapDescriptorFactory.HUE_CYAN
    const val HUE_AZURE = com.google.android.gms.maps.model.BitmapDescriptorFactory.HUE_AZURE
    const val HUE_BLUE = com.google.android.gms.maps.model.BitmapDescriptorFactory.HUE_BLUE
    const val HUE_VIOLET = com.google.android.gms.maps.model.BitmapDescriptorFactory.HUE_VIOLET
    const val HUE_MAGENTA = com.google.android.gms.maps.model.BitmapDescriptorFactory.HUE_MAGENTA
    const val HUE_ROSE = com.google.android.gms.maps.model.BitmapDescriptorFactory.HUE_ROSE

    fun fromResource(id: Int): BitmapDescriptor {
        val google = executeOrNull { com.google.android.gms.maps.model.BitmapDescriptorFactory.fromResource(id) }
        val huawei = executeOrNull { com.huawei.hms.maps.model.BitmapDescriptorFactory.fromResource(id) }
        return BitmapDescriptor(google, huawei)
    }

    fun defaultMarker(): BitmapDescriptor {
        val google = executeOrNull { com.google.android.gms.maps.model.BitmapDescriptorFactory.defaultMarker() }
        val huawei = com.huawei.hms.maps.model.BitmapDescriptorFactory.defaultMarker()
        return BitmapDescriptor(google, huawei)
    }

    fun defaultMarker(hue: Float): BitmapDescriptor {
        val google = executeOrNull { com.google.android.gms.maps.model.BitmapDescriptorFactory.defaultMarker(hue) }
        val huawei = com.huawei.hms.maps.model.BitmapDescriptorFactory.defaultMarker(hue)
        return BitmapDescriptor(google, huawei)
    }

    fun fromBitmap(bitmap: Bitmap): BitmapDescriptor {
        val google = executeOrNull { com.google.android.gms.maps.model.BitmapDescriptorFactory.fromBitmap(bitmap) }
        val huawei = com.huawei.hms.maps.model.BitmapDescriptorFactory.fromBitmap(bitmap)
        return BitmapDescriptor(google, huawei)
    }
}
