package org.m0skit0.android.mapswrapper.model

interface TileProvider {

    companion object {
        val NO_TILE: Tile = com.google.android.gms.maps.model.TileProvider.NO_TILE.run {
            Tile(width, height, data)
        }
    }

    fun getTile(x: Int, y: Int, zoom: Int): Tile?
}
