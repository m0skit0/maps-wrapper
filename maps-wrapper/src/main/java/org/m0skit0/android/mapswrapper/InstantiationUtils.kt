package org.m0skit0.android.mapswrapper

internal fun <T> executeOrNull(block: () -> T): T? = try { block() } catch (e: Exception) { null }
