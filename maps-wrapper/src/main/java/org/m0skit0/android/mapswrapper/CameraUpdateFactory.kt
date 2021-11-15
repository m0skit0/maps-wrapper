package org.m0skit0.android.mapswrapper

import org.m0skit0.android.mapswrapper.model.CameraPosition
import org.m0skit0.android.mapswrapper.model.LatLng
import org.m0skit0.android.mapswrapper.model.LatLngBounds

object CameraUpdateFactory {

    fun zoomIn(): CameraUpdate {
        val google = executeOrNull { com.google.android.gms.maps.CameraUpdateFactory.zoomIn() }
        val huawei = com.huawei.hms.maps.CameraUpdateFactory.zoomIn()
        return CameraUpdate(google, huawei)
    }

    fun zoomOut(): CameraUpdate {
        val google = executeOrNull { com.google.android.gms.maps.CameraUpdateFactory.zoomOut() }
        val huawei = com.huawei.hms.maps.CameraUpdateFactory.zoomOut()
        return CameraUpdate(google, huawei)
    }

    fun scrollBy(x: Float, y: Float): CameraUpdate {
        val google = executeOrNull { com.google.android.gms.maps.CameraUpdateFactory.scrollBy(x, y) }
        val huawei = com.huawei.hms.maps.CameraUpdateFactory.scrollBy(x, y)
        return CameraUpdate(google, huawei)
    }

    fun zoomTo(pos: Float): CameraUpdate {
        val google = executeOrNull { com.google.android.gms.maps.CameraUpdateFactory.zoomTo(pos) }
        val huawei = com.huawei.hms.maps.CameraUpdateFactory.zoomTo(pos)
        return CameraUpdate(google, huawei)
    }

    fun zoomBy(amount: Float): CameraUpdate {
        val google = executeOrNull { com.google.android.gms.maps.CameraUpdateFactory.zoomBy(amount) }
        val huawei = com.huawei.hms.maps.CameraUpdateFactory.zoomBy(amount)
        return CameraUpdate(google, huawei)
    }

    fun newLatLngZoom(position: LatLng, zoom: Float): CameraUpdate {
        val google = executeOrNull {
            com.google.android.gms.maps.CameraUpdateFactory.newLatLngZoom(position.google, zoom)
        }
        val huawei = com.huawei.hms.maps.CameraUpdateFactory.newLatLngZoom(position.huawei, zoom)
        return CameraUpdate(google, huawei)
    }

    fun newCameraPosition(position: CameraPosition): CameraUpdate {
        val google = position.google?.let {
            executeOrNull {
                com.google.android.gms.maps.CameraUpdateFactory.newCameraPosition(it)
            }
        }
        val huawei = com.huawei.hms.maps.CameraUpdateFactory.newCameraPosition(position.huawei)
        return CameraUpdate(google, huawei)
    }

    fun newLatLngBounds(bounds: LatLngBounds, value: Int): CameraUpdate {
        val google = bounds.google?.let {
            executeOrNull {
                com.google.android.gms.maps.CameraUpdateFactory.newLatLngBounds(it, value)
            }
        }
        val huawei = com.huawei.hms.maps.CameraUpdateFactory.newLatLngBounds(bounds.huawei, value)
        return CameraUpdate(google, huawei)
    }

    fun newLatLng(position: LatLng): CameraUpdate {
        val google = executeOrNull {
            com.google.android.gms.maps.CameraUpdateFactory.newLatLng(position.google)
        }
        val huawei = com.huawei.hms.maps.CameraUpdateFactory.newLatLng(position.huawei)
        return CameraUpdate(google, huawei)
    }
}
