package org.m0skit0.android.mapswrapper.model

open class Cap(
    internal val google: com.google.android.gms.maps.model.Cap?,
    internal val huawei: com.huawei.hms.maps.model.Cap?
) {
    internal constructor(google: com.google.android.gms.maps.model.Cap?) : this(google, null)
    internal constructor(huawei: com.huawei.hms.maps.model.Cap?) : this(null, huawei)
}
