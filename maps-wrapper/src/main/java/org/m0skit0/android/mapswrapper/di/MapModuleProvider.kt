package org.m0skit0.android.mapswrapper.di

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.AttrRes
import org.koin.core.module.Module
import org.koin.dsl.module
import org.m0skit0.android.mapswrapper.CommonMap

internal object MapModuleProvider : KoinModuleProvider {
    override fun module(): Module = module {
        factory { com.google.android.gms.maps.SupportMapFragment.newInstance() }
        factory { com.huawei.hms.maps.SupportMapFragment.newInstance() }
        factory { (context: Context, attr: AttributeSet?, @AttrRes defStyleAttr: Int) ->
            com.google.android.gms.maps.MapView(context, attr, defStyleAttr)
        }
        factory { (context: Context, attr: AttributeSet?, @AttrRes defStyleAttr: Int) ->
            com.huawei.hms.maps.MapView(context, attr, defStyleAttr)
        }
        factory { (map: Any) -> CommonMap(map) }
    }
}
