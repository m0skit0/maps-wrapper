package org.m0skit0.android.mapswrapper

import android.content.Context
import com.google.android.gms.common.GoogleApiAvailability
import com.huawei.hms.api.HuaweiApiAvailability
import io.kotlintest.matchers.boolean.shouldBeFalse
import io.kotlintest.matchers.boolean.shouldBeTrue
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.m0skit0.android.mapswrapper.di.koin

class ApiAvailabilityTest : AutoCloseKoinTest() {

    @MockK
    private lateinit var mockContext: Context

    @MockK
    private lateinit var mockHuaweiApiAvailability: HuaweiApiAvailability

    @MockK
    private lateinit var mockGoogleApiAvailability: GoogleApiAvailability

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        koin().loadModules(listOf(
            module {
                factory(override = true) { mockHuaweiApiAvailability }
                factory(override = true) { mockGoogleApiAvailability }
            }
        ))
        every { mockContext.applicationContext } returns mockk()
    }

    @Test
    fun `when call isHuaweiAvailable should call isHuaweiMobileServicesAvailable`() {
        every { mockHuaweiApiAvailability.isHuaweiMobileServicesAvailable(any()) } returns 0
        ApiAvailability.isHuaweiAvailable(mockContext)
        verify {
            mockHuaweiApiAvailability.isHuaweiMobileServicesAvailable(any())
        }
    }

    @Test
    fun `when call isGoogleAvailable should call isGooglePlayServicesAvailable`() {
        every { mockGoogleApiAvailability.isGooglePlayServicesAvailable(any()) } returns 0
        ApiAvailability.isGoogleAvailable(mockContext)
        verify {
            mockGoogleApiAvailability.isGooglePlayServicesAvailable(any())
        }
    }

    @Test
    fun `when call isHuaweiAvailable should return true iff isHuaweiMobileServicesAvailable is SUCCESS`() {
        every { mockHuaweiApiAvailability.isHuaweiMobileServicesAvailable(any()) } returns com.huawei.hms.api.ConnectionResult.SUCCESS
        ApiAvailability.isHuaweiAvailable(mockContext).shouldBeTrue()
        every { mockHuaweiApiAvailability.isHuaweiMobileServicesAvailable(any()) } returns com.huawei.hms.api.ConnectionResult.CANCELED
        ApiAvailability.isHuaweiAvailable(mockContext).shouldBeFalse()
    }

    @Test
    fun `when call isGoogleAvailable should return false iff isGooglePlayServicesAvailable is not SUCCESS`() {
        every { mockGoogleApiAvailability.isGooglePlayServicesAvailable(any()) } returns com.google.android.gms.common.ConnectionResult.SUCCESS
        ApiAvailability.isGoogleAvailable(mockContext).shouldBeTrue()
        every { mockGoogleApiAvailability.isGooglePlayServicesAvailable(any()) } returns com.google.android.gms.common.ConnectionResult.CANCELED
        ApiAvailability.isGoogleAvailable(mockContext).shouldBeFalse()
    }
}
