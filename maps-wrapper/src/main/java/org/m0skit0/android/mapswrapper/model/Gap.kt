package org.m0skit0.android.mapswrapper.model

class Gap(length: Float) : PatternItem(
    com.google.android.gms.maps.model.Gap(length),
    com.huawei.hms.maps.model.Gap(length)
)
