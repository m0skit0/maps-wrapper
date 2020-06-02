package org.m0skit0.android.mapswrapper

import com.google.android.gms.maps.GoogleMap
import com.huawei.hms.maps.HuaweiMap

class CommonMap(private val map: Any) {

    private var type: MapType = when (map) {
        is GoogleMap -> MapType.GOOGLE
        is HuaweiMap -> MapType.HUAWEI
        else -> throw IllegalArgumentException("Constructor map argument is neither a Google Map nor a Huawei Map")
    }

    private val googleMap: GoogleMap
        get() = map as GoogleMap

    private val huaweiMap: HuaweiMap
        get() = map as HuaweiMap


}
