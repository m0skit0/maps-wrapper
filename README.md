# maps-wrapper

[![](https://jitpack.io/v/m0skit0/maps-wrapper.svg)](https://jitpack.io/#m0skit0/maps-wrapper)

Wrapper that unifies Google and Huawei maps in a single API.

The API is identical to Google and Huawei Maps API except the map that has been renamed from
GoogleMap/HuaweiMap to *CommonMap*. The rest of the classes and methods have exactly the same names,
so generally by replacing the Google or Huawei imports with this library imports it should do
the trick.

You can check the project *testapplication* module for an example app that contains exhaustive
examples for each part of the maps API.

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
    implementation 'com.github.m0skit0:maps-wrapper:1.0-beta-3'
}
```

Note that you no longer need to define Google Maps or Huawei Maps dependencies explicitly, this library
already has dependencies on both of them.
