package org.m0skit0.android.mapswrapper

import android.graphics.Bitmap
import io.kotlintest.shouldBe
import io.mockk.*
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Test
import org.koin.test.AutoCloseKoinTest

class BitmapDescriptorFactoryTest : AutoCloseKoinTest() {

    @MockK
    private lateinit var mockGoogleBitmapDescriptor: com.google.android.gms.maps.model.BitmapDescriptor

    @MockK
    private lateinit var mockHuaweiBitmapDescriptor: com.huawei.hms.maps.model.BitmapDescriptor

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        mockkStatic("com.google.android.gms.maps.model.BitmapDescriptorFactory")
        mockkStatic("com.huawei.hms.maps.model.BitmapDescriptorFactory")
    }

    @Test
    fun `when call fromResource should call both Google and Huawei fromResource`() {
        every { com.google.android.gms.maps.model.BitmapDescriptorFactory.fromResource(any()) } returns mockGoogleBitmapDescriptor
        every { com.huawei.hms.maps.model.BitmapDescriptorFactory.fromResource(any()) } returns mockHuaweiBitmapDescriptor
        BitmapDescriptorFactory.fromResource(0).run {
            google shouldBe mockGoogleBitmapDescriptor
            huawei shouldBe mockHuaweiBitmapDescriptor
        }
        verify {
            com.google.android.gms.maps.model.BitmapDescriptorFactory.fromResource(0)
            com.huawei.hms.maps.model.BitmapDescriptorFactory.fromResource(0)
        }
    }

    @Test
    fun `when call fromBitmap should call both Google and Huawei fromBitmap`() {
        every { com.google.android.gms.maps.model.BitmapDescriptorFactory.fromBitmap(any()) } returns mockGoogleBitmapDescriptor
        every { com.huawei.hms.maps.model.BitmapDescriptorFactory.fromBitmap(any()) } returns mockHuaweiBitmapDescriptor
        val bitmap: Bitmap = mockk()
        BitmapDescriptorFactory.fromBitmap(bitmap).run {
            google shouldBe mockGoogleBitmapDescriptor
            huawei shouldBe mockHuaweiBitmapDescriptor
        }
        verify {
            com.google.android.gms.maps.model.BitmapDescriptorFactory.fromBitmap(bitmap)
            com.huawei.hms.maps.model.BitmapDescriptorFactory.fromBitmap(bitmap)
        }
    }

    @Test
    fun `when call defaultMarker should call both Google and Huawei defaultMarker`() {
        every { com.google.android.gms.maps.model.BitmapDescriptorFactory.defaultMarker() } returns mockGoogleBitmapDescriptor
        every { com.huawei.hms.maps.model.BitmapDescriptorFactory.defaultMarker() } returns mockHuaweiBitmapDescriptor
        BitmapDescriptorFactory.defaultMarker().run {
            google shouldBe mockGoogleBitmapDescriptor
            huawei shouldBe mockHuaweiBitmapDescriptor
        }
        verify {
            com.google.android.gms.maps.model.BitmapDescriptorFactory.defaultMarker()
            com.huawei.hms.maps.model.BitmapDescriptorFactory.defaultMarker()
        }
    }

    @Test
    fun `when call defaultMarker(Float) should call both Google and Huawei defaultMarker(Float)`() {
        every { com.google.android.gms.maps.model.BitmapDescriptorFactory.defaultMarker(any()) } returns mockGoogleBitmapDescriptor
        every { com.huawei.hms.maps.model.BitmapDescriptorFactory.defaultMarker(any()) } returns mockHuaweiBitmapDescriptor
        BitmapDescriptorFactory.defaultMarker(0f).run {
            google shouldBe mockGoogleBitmapDescriptor
            huawei shouldBe mockHuaweiBitmapDescriptor
        }
        verify {
            com.google.android.gms.maps.model.BitmapDescriptorFactory.defaultMarker(0f)
            com.huawei.hms.maps.model.BitmapDescriptorFactory.defaultMarker(0f)
        }
    }
}
