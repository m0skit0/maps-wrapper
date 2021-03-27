package org.m0skit0.android.mapswrapper.model

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
data class LatLng(val latitude: Double, val longitude: Double) : Parcelable {
    @IgnoredOnParcel
    internal val google = com.google.android.gms.maps.model.LatLng(latitude, longitude)
    @IgnoredOnParcel
    internal val huawei = com.huawei.hms.maps.model.LatLng(latitude, longitude)
}
