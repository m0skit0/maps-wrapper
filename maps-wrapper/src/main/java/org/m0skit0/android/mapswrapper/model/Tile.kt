package org.m0skit0.android.mapswrapper.model

data class Tile(val width: Int, val height: Int, val data: ByteArray) {
    internal val google by lazy { com.google.android.gms.maps.model.Tile(width, height, data) }
    internal val huawei: Nothing by lazy { throw UnsupportedOperationException("No implementation of Tile API for Huawei Maps") }
}
