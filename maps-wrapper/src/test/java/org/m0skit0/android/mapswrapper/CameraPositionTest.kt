package org.m0skit0.android.mapswrapper

import io.kotlintest.shouldBe
import io.mockk.MockKAnnotations
import org.junit.Before
import org.junit.Test
import org.koin.test.AutoCloseKoinTest

class CameraPositionTest : AutoCloseKoinTest() {

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `when created with google and get target should return google target`() {
        val target = com.google.android.gms.maps.model.LatLng(1.0, 2.0)
        val cameraPosition = com.google.android.gms.maps.model.CameraPosition
            .builder()
            .target(target)
            .build()
        CameraPosition(cameraPosition, null).target.run {
            latitude shouldBe cameraPosition.target.latitude
            longitude shouldBe cameraPosition.target.longitude
        }
    }

    @Test
    fun `when created with huawei and get target should return huawei target`() {
        val target = com.huawei.hms.maps.model.LatLng(1.0, 2.0)
        val cameraPosition = com.huawei.hms.maps.model.CameraPosition
            .builder()
            .target(target)
            .build()
        CameraPosition(null, cameraPosition).target.run {
            latitude shouldBe cameraPosition.target.latitude
            longitude shouldBe cameraPosition.target.longitude
        }
    }

    @Test
    fun `when created with google and get zoom should return google zoom`() {
        val target = com.google.android.gms.maps.model.LatLng(1.0, 2.0)
        val cameraPosition = com.google.android.gms.maps.model.CameraPosition
            .builder()
            .target(target)
            .zoom(2f)
            .build()
        CameraPosition(cameraPosition, null).zoom shouldBe 2f
    }

    @Test
    fun `when created with huawei and get zoom should return huawei zoom`() {
        val target = com.huawei.hms.maps.model.LatLng(1.0, 2.0)
        val cameraPosition = com.huawei.hms.maps.model.CameraPosition
            .builder()
            .target(target)
            .zoom(2f)
            .build()
        CameraPosition(null, cameraPosition).zoom shouldBe 2f
    }

    @Test
    fun `when created with google and get tilt should return google tilt`() {
        val target = com.google.android.gms.maps.model.LatLng(1.0, 2.0)
        val cameraPosition = com.google.android.gms.maps.model.CameraPosition
            .builder()
            .target(target)
            .tilt(2f)
            .build()
        CameraPosition(cameraPosition, null).tilt shouldBe 2f
    }

    @Test
    fun `when created with huawei and get tilt should return huawei tilt`() {
        val target = com.huawei.hms.maps.model.LatLng(1.0, 2.0)
        val cameraPosition = com.huawei.hms.maps.model.CameraPosition
            .builder()
            .target(target)
            .tilt(2f)
            .build()
        CameraPosition(null, cameraPosition).tilt shouldBe 2f
    }

    @Test
    fun `when created with google and get bearing should return google bearing`() {
        val target = com.google.android.gms.maps.model.LatLng(1.0, 2.0)
        val cameraPosition = com.google.android.gms.maps.model.CameraPosition
            .builder()
            .target(target)
            .bearing(2f)
            .build()
        CameraPosition(cameraPosition, null).bearing shouldBe 2f
    }

    @Test
    fun `when created with huawei and get bearing should return huawei bearing`() {
        val target = com.huawei.hms.maps.model.LatLng(1.0, 2.0)
        val cameraPosition = com.huawei.hms.maps.model.CameraPosition
            .builder()
            .target(target)
            .bearing(2f)
            .build()
        CameraPosition(null, cameraPosition).bearing shouldBe 2f
    }
}
