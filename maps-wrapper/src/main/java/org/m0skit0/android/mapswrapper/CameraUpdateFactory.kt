package org.m0skit0.android.mapswrapper

import org.koin.core.get
import org.koin.core.parameter.parametersOf
import org.m0skit0.android.mapswrapper.di.KoinInterface

object CameraUpdateFactory : KoinInterface {

    fun zoomIn(): CameraUpdate {
        val google = executeOrNull { com.google.android.gms.maps.CameraUpdateFactory.zoomIn() }
        val huawei = com.huawei.hms.maps.CameraUpdateFactory.zoomIn()
        return get { parametersOf(google, huawei) }
    }

    fun zoomOut(): CameraUpdate {
        val google = executeOrNull { com.google.android.gms.maps.CameraUpdateFactory.zoomOut() }
        val huawei = com.huawei.hms.maps.CameraUpdateFactory.zoomOut()
        return get { parametersOf(google, huawei) }
    }

    fun scrollBy(x: Float, y: Float): CameraUpdate {
        val google = executeOrNull { com.google.android.gms.maps.CameraUpdateFactory.scrollBy(x, y) }
        val huawei = com.huawei.hms.maps.CameraUpdateFactory.scrollBy(x, y)
        return get { parametersOf(google, huawei) }
    }

    fun zoomTo(pos: Float): CameraUpdate {
        val google = executeOrNull { com.google.android.gms.maps.CameraUpdateFactory.zoomTo(pos) }
        val huawei = com.huawei.hms.maps.CameraUpdateFactory.zoomTo(pos)
        return get { parametersOf(google, huawei) }
    }

    fun zoomBy(amount: Float): CameraUpdate {
        val google = executeOrNull { com.google.android.gms.maps.CameraUpdateFactory.zoomBy(amount) }
        val huawei = com.huawei.hms.maps.CameraUpdateFactory.zoomBy(amount)
        return get { parametersOf(google, huawei) }
    }

    fun newLatLngZoom(position: LatLng, zoom: Float): CameraUpdate {
        val google = executeOrNull {
            com.google.android.gms.maps.CameraUpdateFactory.newLatLngZoom(position.google, zoom)
        }
        val huawei = com.huawei.hms.maps.CameraUpdateFactory.newLatLngZoom(position.huawei, zoom)
        return get { parametersOf(google, huawei) }
    }

    fun newCameraPosition(position: CameraPosition): CameraUpdate {
        val google = executeOrNull {
            com.google.android.gms.maps.CameraUpdateFactory.newCameraPosition(position.google)
        }
        val huawei = com.huawei.hms.maps.CameraUpdateFactory.newCameraPosition(position.huawei)
        return get { parametersOf(google, huawei) }
    }

    fun newLatLngBounds(bounds: LatLngBounds, value: Int): CameraUpdate {
        val google = executeOrNull {
            com.google.android.gms.maps.CameraUpdateFactory.newLatLngBounds(bounds.google, value)
        }
        val huawei = com.huawei.hms.maps.CameraUpdateFactory.newLatLngBounds(bounds.huawei, value)
        return get { parametersOf(google, huawei) }
    }

    fun newLatLng(position: LatLng): CameraUpdate {
        val google = executeOrNull {
            com.google.android.gms.maps.CameraUpdateFactory.newLatLng(position.google)
        }
        val huawei = com.huawei.hms.maps.CameraUpdateFactory.newLatLng(position.huawei)
        return get { parametersOf(google, huawei) }
    }
}
