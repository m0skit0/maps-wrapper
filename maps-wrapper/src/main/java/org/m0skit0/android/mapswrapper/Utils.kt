package org.m0skit0.android.mapswrapper

internal fun <T> executeOrNull(block: () -> T): T? = try { block() } catch (e: Exception) { null }

internal fun throwUnableToResolveGoogleOrHuawei(): Nothing =
    throw IllegalStateException("Could not resolve if Google or Huawei")

internal fun throwNoGoogleNoHuaweiServices(): Nothing =
    throw IllegalStateException("No Google or Huawei Services found!")
