package org.m0skit0.android.mapswrapper.model

open class PatternItem(
    internal val google: com.google.android.gms.maps.model.PatternItem?,
    internal val huawei: com.huawei.hms.maps.model.PatternItem?
) {
    internal constructor(google: com.google.android.gms.maps.model.PatternItem?) : this(google, null)
    internal constructor(huawei: com.huawei.hms.maps.model.PatternItem?) : this(null, huawei)
}
