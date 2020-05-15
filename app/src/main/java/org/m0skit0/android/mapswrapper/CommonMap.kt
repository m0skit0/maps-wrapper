package org.m0skit0.android.mapswrapper

import com.google.android.gms.maps.GoogleMap
import com.huawei.hms.maps.HuaweiMap

class CommonMap(map: Any) {

    private lateinit var google: GoogleMap
    private lateinit var huawei: HuaweiMap
    private lateinit var type: MapType

    init {
        when (map) {
            is GoogleMap -> {
                google = map
                type = MapType.GOOGLE
            }
            is HuaweiMap -> {
                huawei = map
                type = MapType.HUAWEI
            }
        }
    }
}
