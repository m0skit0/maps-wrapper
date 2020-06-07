package org.m0skit0.android.mapswrapper

class CustomCap(bitmapDescriptor: BitmapDescriptor, length: Float) : Cap(
    bitmapDescriptor.google?.run { com.google.android.gms.maps.model.CustomCap(this, length) },
    com.huawei.hms.maps.model.CustomCap(bitmapDescriptor.huawei, length)
)
