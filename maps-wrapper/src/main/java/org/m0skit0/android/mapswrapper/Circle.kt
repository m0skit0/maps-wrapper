package org.m0skit0.android.mapswrapper

class Circle internal constructor(
    internal val google: com.google.android.gms.maps.model.Circle?,
    internal val huawei: com.huawei.hms.maps.model.Circle?
) {

    fun setTag(tag: Any) {
        google?.tag = tag
        huawei?.setTag(tag)
    }

    fun getTag(): Any? = google?.tag ?: huawei?.tag
}
