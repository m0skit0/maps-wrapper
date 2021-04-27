# maps-wrapper

[![](https://jitpack.io/v/m0skit0/maps-wrapper.svg)](https://jitpack.io/#m0skit0/maps-wrapper)

Wrapper that unifies Google and Huawei maps in a single API.

## Usage

The API is identical to Google and Huawei Maps API except the map that has been renamed from
`GoogleMap`/`HuaweiMap` to `CommonMap`. The rest of the classes and methods have exactly the same names,
so generally by replacing the Google or Huawei imports with this library imports it should do
the trick.

Here's an activity layout with a `SupportMapFragment`:

```xml
<?xml version="1.0" encoding="utf-8"?>
<fragment xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/map"
    android:name="org.m0skit0.android.mapswrapper.SupportMapFragment"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    app:wrappedMapType="auto"
    tools:context=".demos.BasicMapDemoActivity">
</fragment>
```

This is the activity class:

```kotlin
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.demos.maps.R
import org.m0skit0.android.mapswrapper.*

/**
 * This shows how to create a simple activity with a map and a marker on the map.
 */
class BasicMapDemoActivity : AppCompatActivity(), OnMapReadyCallback {

    val SYDNEY = LatLng(-33.862, 151.21)
    val ZOOM_LEVEL = 13f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic_map_demo)
        val mapFragment : SupportMapFragment? =
                supportFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment
        mapFragment?.getMapAsync(this)
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just move the camera to Sydney and add a marker in Sydney.
     */
    override fun onMapReady(map: CommonMap) {
        with(map) {
            moveCamera(CameraUpdateFactory.newLatLngZoom(SYDNEY, ZOOM_LEVEL))
            addMarker(MarkerOptions().position(SYDNEY))
        }
    }
}
```

Note the `app:wrappedMapType="auto"` attribute in the fragment layout.
This defines the map resolution strategy. Currently 5 strategies are available:

- `auto`: same as `google_then_huawei`.
- `google_then_huawei`: tries to use Google Maps first. If not available
falls back to Huawei. If not available either throws an error.
- `huawei_then_google`: tries to use Huawei Maps first. If not available
falls back to Google. If not available either throws an error.
- `force_google`: tries to use Google Maps. If not available throws error.
- `force_huawei`: tries to use Huawei Maps. If not available throws error.

If no map resolution strategy is defined it defaults to the strategy defined in
`MapResolverStrategy.default`. By default this is `auto` but can be
overridden programmatically.

You can check the *testapplication* module for an example app that contains more exhaustive
examples for each part of the maps API.

## Extended API

Some classes have enhanced API support:

- `SupportMapFragment` supports a suspend `mapAsync()` method that returns a `CommonMap`.
- `MapView` supports a suspend `mapAsync()` method that returns a `CommonMap`.

## Add as dependency

Add Jitpack to your project repositories in your root build.gradle.

```gradle
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```
Then add the dependency on your app build.gradle

```gradle
dependencies {
    implementation 'com.github.m0skit0:maps-wrapper:1.0-beta12'
}
```

Note that you no longer need to define Google Maps or Huawei Maps dependencies explicitly, this library
already has dependencies on both of them.
