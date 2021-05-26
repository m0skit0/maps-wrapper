package org.m0skit0.android.mapswrapper.exception

fun interface InternalExceptionHandler {
    fun handle(e: Exception)
}

internal object InternalExceptionHandlerImpl : InternalExceptionHandler {
    override fun handle(e: Exception) {
        e.printStackTrace()
    }
}

object ExceptionHandlerFactory {
    var handler: InternalExceptionHandler = InternalExceptionHandlerImpl
}
