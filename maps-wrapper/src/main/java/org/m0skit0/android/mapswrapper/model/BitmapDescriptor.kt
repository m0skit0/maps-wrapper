package org.m0skit0.android.mapswrapper.model

class BitmapDescriptor(
    internal val google: com.google.android.gms.maps.model.BitmapDescriptor?,
    internal val huawei: com.huawei.hms.maps.model.BitmapDescriptor?
) {
    internal constructor(google: com.google.android.gms.maps.model.BitmapDescriptor?): this(google, null)
    internal constructor(huawei: com.huawei.hms.maps.model.BitmapDescriptor?): this(null, huawei)
}
