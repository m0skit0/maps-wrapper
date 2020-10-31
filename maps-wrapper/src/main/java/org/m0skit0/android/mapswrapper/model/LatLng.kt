package org.m0skit0.android.mapswrapper.model

import android.os.Parcelable
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LatLng(val latitude: Double, val longitude: Double) : Parcelable {
    @IgnoredOnParcel
    internal val google by lazy { com.google.android.gms.maps.model.LatLng(latitude, longitude) }
    @IgnoredOnParcel
    internal val huawei by lazy { com.huawei.hms.maps.model.LatLng(latitude, longitude) }
}
