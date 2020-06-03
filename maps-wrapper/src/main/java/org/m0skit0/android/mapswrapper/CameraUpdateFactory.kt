package org.m0skit0.android.mapswrapper

object CameraUpdateFactory {

    fun zoomIn(): CameraUpdate {
        val google = com.google.android.gms.maps.CameraUpdateFactory.zoomIn()
        val huawei = com.huawei.hms.maps.CameraUpdateFactory.zoomIn()
        return CameraUpdate(google, huawei)
    }

    fun zoomOut(): CameraUpdate {
        val google = com.google.android.gms.maps.CameraUpdateFactory.zoomOut()
        val huawei = com.huawei.hms.maps.CameraUpdateFactory.zoomOut()
        return CameraUpdate(google, huawei)
    }

    fun scrollBy(x: Float, y: Float): CameraUpdate {
        val google = com.google.android.gms.maps.CameraUpdateFactory.scrollBy(x, y)
        val huawei = com.huawei.hms.maps.CameraUpdateFactory.scrollBy(x, y)
        return CameraUpdate(google, huawei)
    }

    fun zoomTo(pos: Float): CameraUpdate {
        val google = com.google.android.gms.maps.CameraUpdateFactory.zoomTo(pos)
        val huawei = com.huawei.hms.maps.CameraUpdateFactory.zoomTo(pos)
        return CameraUpdate(google, huawei)
    }

    fun zoomBy(amount: Float): CameraUpdate {
        val google = com.google.android.gms.maps.CameraUpdateFactory.zoomBy(amount)
        val huawei = com.huawei.hms.maps.CameraUpdateFactory.zoomBy(amount)
        return CameraUpdate(google, huawei)
    }
}
