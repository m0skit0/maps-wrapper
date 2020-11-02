package org.m0skit0.android.mapswrapper.model

import java.net.URL

abstract class UrlTileProvider(val width: Int, val height: Int) : TileProvider {

    internal val google = object : com.google.android.gms.maps.model.UrlTileProvider(width, height) {
        override fun getTileUrl(x: Int, y: Int, zoom: Int): URL = this@UrlTileProvider.getTileUrl(x, y, zoom)
    }

    internal val huawei = object : com.huawei.hms.maps.model.UrlTileProvider(width, height) {
        override fun getTileUrl(x: Int, y: Int, zoom: Int): URL = this@UrlTileProvider.getTileUrl(x, y, zoom)
    }

    override fun getTile(x: Int, y: Int, zoom: Int): Tile? = Tile(google.getTile(x, y, zoom), huawei.getTile(x, y, zoom))

    abstract fun getTileUrl(x: Int, y: Int, zoom: Int): URL
}
