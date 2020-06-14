package org.m0skit0.android.mapswrapper.di

import org.koin.core.module.Module

internal interface KoinModuleProvider {
    fun module(): Module
}
