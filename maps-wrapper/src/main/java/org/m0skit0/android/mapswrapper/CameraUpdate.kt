package org.m0skit0.android.mapswrapper

class CameraUpdate internal constructor(
    internal val googleCameraUpdate: com.google.android.gms.maps.CameraUpdate,
    internal val huaweiCameraUpdate: com.huawei.hms.maps.CameraUpdate
) {
}
