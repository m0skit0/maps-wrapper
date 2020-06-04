package org.m0skit0.android.mapswrapper

import com.google.android.gms.maps.model.JointType

interface JointType {
    companion object {
        const val DEFAULT = JointType.DEFAULT
        const val BEVEL = JointType.BEVEL
        const val ROUND = JointType.ROUND
    }
}
