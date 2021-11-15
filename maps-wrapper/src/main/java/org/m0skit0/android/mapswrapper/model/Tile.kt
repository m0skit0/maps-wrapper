package org.m0skit0.android.mapswrapper.model


class Tile {

    constructor(width: Int, height: Int, data: ByteArray?) {
        google = com.google.android.gms.maps.model.Tile(width, height, data)
        huawei = com.huawei.hms.maps.model.Tile(width, height, data)
    }

    internal constructor(google: com.google.android.gms.maps.model.Tile?, huawei: com.huawei.hms.maps.model.Tile?) {
        this.google = google
        this.huawei = huawei
    }

    internal var google: com.google.android.gms.maps.model.Tile? = null
    internal var huawei: com.huawei.hms.maps.model.Tile? = null
}
