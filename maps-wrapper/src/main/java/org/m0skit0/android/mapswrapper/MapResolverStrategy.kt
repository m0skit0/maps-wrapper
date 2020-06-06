package org.m0skit0.android.mapswrapper

internal enum class MapResolverStrategy(val value: List<String>) {
    FORCE_GOOGLE(listOf("google")),
    FORCE_HUAWEI(listOf("huawei")),
    GOOGLE_THEN_HUAWEI(listOf("auto", "google_then_huawei")),
    HUAWEI_THEN_GOOGLE(listOf("huawei_then_google"));

    companion object {
        fun fromValue(value: String): MapResolverStrategy =
            values().firstOrNull { it.value.contains(value) } ?: GOOGLE_THEN_HUAWEI
    }
}
