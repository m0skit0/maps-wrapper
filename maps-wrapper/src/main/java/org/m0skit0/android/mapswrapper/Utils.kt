package org.m0skit0.android.mapswrapper

import org.m0skit0.android.mapswrapper.exception.ExceptionHandlerFactory

internal fun <T> executeOrNull(block: () -> T): T? =
    try {
        block()
    } catch (e: Exception) {
        ExceptionHandlerFactory.handler.handle(e)
        null
    }

internal fun throwUnableToResolveGoogleOrHuawei(): Nothing =
    throw IllegalStateException("Could not resolve if Google or Huawei")

internal fun throwNoGoogleNoHuaweiServices(): Nothing =
    throw IllegalStateException("No Google or Huawei Services found!")

internal fun throwNotSupported(): Nothing = throw UnsupportedOperationException("Not supported")
