package org.m0skit0.android.mapswrapper

import android.animation.Animator
import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.content.res.Configuration
import android.os.Bundle
import android.util.AttributeSet
import android.view.*
import android.view.animation.Animation
import androidx.core.app.SharedElementCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelStore
import java.io.FileDescriptor
import java.io.PrintWriter
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class SupportMapFragment : Fragment() {

    private val googleFragment: com.google.android.gms.maps.SupportMapFragment by lazy { com.google.android.gms.maps.SupportMapFragment.newInstance() }
    private val huaweiFragment: com.huawei.hms.maps.SupportMapFragment by lazy { com.huawei.hms.maps.SupportMapFragment.newInstance() }

    private var callback: OnMapReadyCallback? = null

    fun getMapAsync(callback: OnMapReadyCallback) {
        this.callback = callback
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.getMapAsync { CommonMap(it).let { commonMap ->  callback.onMapReady(commonMap) } }
            MapType.HUAWEI -> huaweiFragment.getMapAsync { CommonMap(it).let { commonMap ->  callback.onMapReady(commonMap) } }
        }
    }

    suspend fun mapAsync(): CommonMap =
        suspendCoroutine { continuation ->
            object : OnMapReadyCallback {
                override fun onMapReady(map: CommonMap) {
                    continuation.resume(map)
                }
            }.let { callback -> getMapAsync(callback) }
        }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.onCreateContextMenu(menu, v, menuInfo)
            MapType.HUAWEI -> huaweiFragment.onCreateContextMenu(menu, v, menuInfo)
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.onContextItemSelected(item)
            MapType.HUAWEI -> huaweiFragment.onContextItemSelected(item)
        }
    }

    override fun getView(): View? {
        return when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.view
            MapType.HUAWEI -> huaweiFragment.view
        }
    }

    override fun setExitSharedElementCallback(callback: SharedElementCallback?) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.setExitSharedElementCallback(callback)
            MapType.HUAWEI -> huaweiFragment.setExitSharedElementCallback(callback)
        }
    }

    override fun getAllowReturnTransitionOverlap(): Boolean {
        return when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.allowReturnTransitionOverlap
            MapType.HUAWEI -> huaweiFragment.allowReturnTransitionOverlap
        }
    }

    override fun startIntentSenderForResult(
        intent: IntentSender?,
        requestCode: Int,
        fillInIntent: Intent?,
        flagsMask: Int,
        flagsValues: Int,
        extraFlags: Int,
        options: Bundle?
    ) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.startIntentSenderForResult(intent, requestCode, fillInIntent, flagsMask, flagsValues, extraFlags, options)
            MapType.HUAWEI -> huaweiFragment.startIntentSenderForResult(intent, requestCode, fillInIntent, flagsMask, flagsValues, extraFlags, options)
        }
    }

    override fun getAllowEnterTransitionOverlap(): Boolean {
        return when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.allowEnterTransitionOverlap
            MapType.HUAWEI -> huaweiFragment.allowEnterTransitionOverlap
        }
    }

    override fun setExitTransition(transition: Any?) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.exitTransition = transition
            MapType.HUAWEI -> huaweiFragment.exitTransition = transition
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.onConfigurationChanged(newConfig)
            MapType.HUAWEI -> huaweiFragment.onConfigurationChanged(newConfig)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.onAttach(context)
            MapType.HUAWEI -> huaweiFragment.onAttach(context)
        }
    }

    override fun onPause() {
        super.onPause()
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.onPause()
            MapType.HUAWEI -> huaweiFragment.onPause()
        }
    }

    override fun setArguments(args: Bundle?) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.arguments = args
            MapType.HUAWEI -> huaweiFragment.arguments = args
        }
    }

    override fun setSharedElementEnterTransition(transition: Any?) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.sharedElementEnterTransition = transition
            MapType.HUAWEI -> huaweiFragment.sharedElementEnterTransition = transition
        }
    }

    override fun setAllowEnterTransitionOverlap(allow: Boolean) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.allowEnterTransitionOverlap = allow
            MapType.HUAWEI -> huaweiFragment.allowEnterTransitionOverlap = allow
        }
    }

    override fun dump(
        prefix: String,
        fd: FileDescriptor?,
        writer: PrintWriter,
        args: Array<out String>?
    ) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.dump(prefix, fd, writer, args)
            MapType.HUAWEI -> huaweiFragment.dump(prefix, fd, writer, args)
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.onPrepareOptionsMenu(menu)
            MapType.HUAWEI -> huaweiFragment.onPrepareOptionsMenu(menu)
        }
    }

    override fun setEnterSharedElementCallback(callback: SharedElementCallback?) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.setEnterSharedElementCallback(callback)
            MapType.HUAWEI -> huaweiFragment.setEnterSharedElementCallback(callback)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.onActivityResult(requestCode, resultCode, data)
            MapType.HUAWEI -> huaweiFragment.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun getReturnTransition(): Any? {
        return when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.returnTransition
            MapType.HUAWEI -> huaweiFragment.returnTransition
        }
    }

    override fun onCreateAnimator(transit: Int, enter: Boolean, nextAnim: Int): Animator? {
        return when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.onCreateAnimator(transit, enter, nextAnim)
            MapType.HUAWEI -> huaweiFragment.onCreateAnimator(transit, enter, nextAnim)
        }
    }

    override fun onMultiWindowModeChanged(isInMultiWindowMode: Boolean) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.onMultiWindowModeChanged(isInMultiWindowMode)
            MapType.HUAWEI -> huaweiFragment.onMultiWindowModeChanged(isInMultiWindowMode)
        }
    }

    override fun setEnterTransition(transition: Any?) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.enterTransition = transition
            MapType.HUAWEI -> huaweiFragment.enterTransition = transition
        }
    }

    override fun onInflate(context: Context, attrs: AttributeSet, savedInstanceState: Bundle?) {
        super.onInflate(context, attrs, savedInstanceState)
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.onInflate(context, attrs, savedInstanceState)
            MapType.HUAWEI -> huaweiFragment.onInflate(context, attrs, savedInstanceState)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.onViewCreated(view, savedInstanceState)
            MapType.HUAWEI -> huaweiFragment.onViewCreated(view, savedInstanceState)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.onActivityCreated(savedInstanceState)
            MapType.HUAWEI -> huaweiFragment.onActivityCreated(savedInstanceState)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.onCreate(savedInstanceState)
            MapType.HUAWEI -> huaweiFragment.onCreate(savedInstanceState)
        }
    }

    override fun onLowMemory() {
        super.onLowMemory()
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.onLowMemory()
            MapType.HUAWEI -> huaweiFragment.onLowMemory()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.onCreateOptionsMenu(menu, inflater)
            MapType.HUAWEI -> huaweiFragment.onCreateOptionsMenu(menu, inflater)
        }
    }

    override fun startPostponedEnterTransition() {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.startPostponedEnterTransition()
            MapType.HUAWEI -> huaweiFragment.startPostponedEnterTransition()
        }
    }

    override fun getEnterTransition(): Any? {
        return when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.enterTransition
            MapType.HUAWEI -> huaweiFragment.enterTransition
        }
    }

    override fun onStart() {
        super.onStart()
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.onStart()
            MapType.HUAWEI -> huaweiFragment.onStart()
        }
    }

    override fun setRetainInstance(retain: Boolean) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.retainInstance = retain
            MapType.HUAWEI -> huaweiFragment.retainInstance = retain
        }
    }

    override fun unregisterForContextMenu(view: View) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.unregisterForContextMenu(view)
            MapType.HUAWEI -> huaweiFragment.unregisterForContextMenu(view)
        }
    }

    override fun registerForContextMenu(view: View) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.registerForContextMenu(view)
            MapType.HUAWEI -> huaweiFragment.registerForContextMenu(view)
        }
    }

    override fun getReenterTransition(): Any? {
        return when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.reenterTransition
            MapType.HUAWEI -> huaweiFragment.reenterTransition
        }
    }

    override fun onDestroyOptionsMenu() {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.onDestroyOptionsMenu()
            MapType.HUAWEI -> huaweiFragment.onDestroyOptionsMenu()
        }
    }

    override fun getLifecycle(): Lifecycle {
        return when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.lifecycle
            MapType.HUAWEI -> huaweiFragment.lifecycle
        }
    }

    override fun setTargetFragment(fragment: Fragment?, requestCode: Int) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.setTargetFragment(fragment, requestCode)
            MapType.HUAWEI -> huaweiFragment.setTargetFragment(fragment, requestCode)
        }
    }

    override fun onOptionsMenuClosed(menu: Menu) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.onOptionsMenuClosed(menu)
            MapType.HUAWEI -> huaweiFragment.onOptionsMenuClosed(menu)
        }
    }

    override fun getSharedElementEnterTransition(): Any? {
        return when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.sharedElementEnterTransition
            MapType.HUAWEI -> huaweiFragment.sharedElementEnterTransition
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.onRequestPermissionsResult(requestCode, permissions, grantResults)
            MapType.HUAWEI -> huaweiFragment.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    override fun postponeEnterTransition() {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.postponeEnterTransition()
            MapType.HUAWEI -> huaweiFragment.postponeEnterTransition()
        }
    }

    override fun onResume() {
        super.onResume()
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.onResume()
            MapType.HUAWEI -> huaweiFragment.onResume()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.onSaveInstanceState(outState)
            MapType.HUAWEI -> huaweiFragment.onSaveInstanceState(outState)
        }
    }

    override fun getViewLifecycleOwnerLiveData(): LiveData<LifecycleOwner> {
        return when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.viewLifecycleOwnerLiveData
            MapType.HUAWEI -> huaweiFragment.viewLifecycleOwnerLiveData
        }
    }

    override fun onDetach() {
        super.onDetach()
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.onDetach()
            MapType.HUAWEI -> huaweiFragment.onDetach()
        }
    }

    override fun shouldShowRequestPermissionRationale(permission: String): Boolean {
        return when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.shouldShowRequestPermissionRationale(permission)
            MapType.HUAWEI -> huaweiFragment.shouldShowRequestPermissionRationale(permission)
        }
    }

    override fun startActivity(intent: Intent?) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.startActivity(intent)
            MapType.HUAWEI -> huaweiFragment.startActivity(intent)
        }
    }

    override fun startActivity(intent: Intent?, options: Bundle?) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.startActivity(intent, options)
            MapType.HUAWEI -> huaweiFragment.startActivity(intent, options)
        }
    }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        return when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.onCreateAnimation(transit, enter, nextAnim)
            MapType.HUAWEI -> huaweiFragment.onCreateAnimation(transit, enter, nextAnim)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.onCreateView(inflater, container, savedInstanceState)
            MapType.HUAWEI -> huaweiFragment.onCreateView(inflater, container, savedInstanceState)
        }
    }

    override fun toString(): String {
        return when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.toString()
            MapType.HUAWEI -> huaweiFragment.toString()
        }
    }

    override fun onAttachFragment(childFragment: Fragment) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.onAttachFragment(childFragment)
            MapType.HUAWEI -> huaweiFragment.onAttachFragment(childFragment)
        }
    }

    override fun onGetLayoutInflater(savedInstanceState: Bundle?): LayoutInflater {
        return when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.onGetLayoutInflater(savedInstanceState)
            MapType.HUAWEI -> huaweiFragment.onGetLayoutInflater(savedInstanceState)
        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.onHiddenChanged(hidden)
            MapType.HUAWEI -> huaweiFragment.onHiddenChanged(hidden)
        }
    }

    override fun getContext(): Context? {
        return when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.context
            MapType.HUAWEI -> huaweiFragment.context
        }
    }

    override fun setMenuVisibility(menuVisible: Boolean) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.setMenuVisibility(menuVisible)
            MapType.HUAWEI -> huaweiFragment.setMenuVisibility(menuVisible)
        }
    }

    override fun setInitialSavedState(state: SavedState?) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.setInitialSavedState(state)
            MapType.HUAWEI -> huaweiFragment.setInitialSavedState(state)
        }
    }

    override fun setReturnTransition(transition: Any?) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.returnTransition = transition
            MapType.HUAWEI -> huaweiFragment.returnTransition = transition
        }
    }

    override fun setReenterTransition(transition: Any?) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.reenterTransition = transition
            MapType.HUAWEI -> huaweiFragment.reenterTransition = transition
        }
    }

    override fun getSharedElementReturnTransition(): Any? {
        return when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.sharedElementReturnTransition
            MapType.HUAWEI -> huaweiFragment.sharedElementReturnTransition
        }
    }

    override fun startActivityForResult(intent: Intent?, requestCode: Int) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.startActivityForResult(intent, requestCode)
            MapType.HUAWEI -> huaweiFragment.startActivityForResult(intent, requestCode)
        }
    }

    override fun startActivityForResult(intent: Intent?, requestCode: Int, options: Bundle?) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.startActivityForResult(intent, requestCode, options)
            MapType.HUAWEI -> huaweiFragment.startActivityForResult(intent, requestCode, options)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.onDestroyView()
            MapType.HUAWEI -> huaweiFragment.onDestroyView()
        }
    }

    override fun setHasOptionsMenu(hasMenu: Boolean) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.setHasOptionsMenu(hasMenu)
            MapType.HUAWEI -> huaweiFragment.setHasOptionsMenu(hasMenu)
        }
    }

    override fun onStop() {
        super.onStop()
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.onStop()
            MapType.HUAWEI -> huaweiFragment.onStop()
        }
    }

    override fun setSharedElementReturnTransition(transition: Any?) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.sharedElementReturnTransition = transition
            MapType.HUAWEI -> huaweiFragment.sharedElementReturnTransition = transition
        }
    }

    override fun getViewLifecycleOwner(): LifecycleOwner {
        return when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.viewLifecycleOwner
            MapType.HUAWEI -> huaweiFragment.viewLifecycleOwner
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.onOptionsItemSelected(item)
            MapType.HUAWEI -> huaweiFragment.onOptionsItemSelected(item)
        }
    }

    override fun getExitTransition(): Any? {
        return when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.exitTransition
            MapType.HUAWEI -> huaweiFragment.exitTransition
        }
    }

    override fun setAllowReturnTransitionOverlap(allow: Boolean) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.allowReturnTransitionOverlap = allow
            MapType.HUAWEI -> huaweiFragment.allowReturnTransitionOverlap = allow
        }
    }

    override fun onPictureInPictureModeChanged(isInPictureInPictureMode: Boolean) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.onPictureInPictureModeChanged(isInPictureInPictureMode)
            MapType.HUAWEI -> huaweiFragment.onPictureInPictureModeChanged(isInPictureInPictureMode)
        }
    }

    override fun getViewModelStore(): ViewModelStore {
        return when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.viewModelStore
            MapType.HUAWEI -> huaweiFragment.viewModelStore
        }
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.onViewStateRestored(savedInstanceState)
            MapType.HUAWEI -> huaweiFragment.onViewStateRestored(savedInstanceState)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> googleFragment.onDestroy()
            MapType.HUAWEI -> huaweiFragment.onDestroy()
        }
    }
}
