package org.m0skit0.android.mapswrapper

import io.kotlintest.shouldBe
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test
import org.koin.test.AutoCloseKoinTest

class CameraPositionTest : AutoCloseKoinTest() {

    @MockK
    private lateinit var mockGoogleCameraPosition: com.google.android.gms.maps.model.CameraPosition

    @MockK
    private lateinit var mockHuaweiCameraPosition: com.huawei.hms.maps.model.CameraPosition

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `when created with google and get target should return google`() {
        val target = com.google.android.gms.maps.model.LatLng(1.0, 2.0)
        val cameraPosition = com.google.android.gms.maps.model.CameraPosition.Builder().target(target).build()
        CameraPosition(cameraPosition, null).target.run {
            latitude shouldBe cameraPosition.target.latitude
            longitude shouldBe cameraPosition.target.longitude
        }
    }

    @Test
    fun `when created with huawei and get target should return huawei`() {
        val target = com.huawei.hms.maps.model.LatLng(1.0, 2.0)
        val cameraPosition = com.huawei.hms.maps.model.CameraPosition.Builder().target(target).build()
        CameraPosition(null, cameraPosition).target.run {
            latitude shouldBe cameraPosition.target.latitude
            longitude shouldBe cameraPosition.target.longitude
        }
    }
}
