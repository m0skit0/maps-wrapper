package org.m0skit0.android.mapswrapper

import android.animation.Animator
import android.annotation.SuppressLint
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

class SupportMapFragment : Fragment() {

    private val google: com.google.android.gms.maps.SupportMapFragment by lazy { com.google.android.gms.maps.SupportMapFragment.newInstance() }
    private val huawei: com.huawei.hms.maps.SupportMapFragment by lazy { com.huawei.hms.maps.SupportMapFragment.newInstance() }

    override fun onCreateContextMenu(
        menu: ContextMenu,
        v: View,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.onCreateContextMenu(menu, v, menuInfo)
            MapType.HUAWEI -> huawei.onCreateContextMenu(menu, v, menuInfo)
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.onContextItemSelected(item)
            MapType.HUAWEI -> huawei.onContextItemSelected(item)
        }
    }

    override fun getView(): View? {
        return when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.view
            MapType.HUAWEI -> huawei.view
        }
    }

    override fun setExitSharedElementCallback(callback: SharedElementCallback?) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.setExitSharedElementCallback(callback)
            MapType.HUAWEI -> huawei.setExitSharedElementCallback(callback)
        }
    }

    override fun getAllowReturnTransitionOverlap(): Boolean {
        return when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.allowReturnTransitionOverlap
            MapType.HUAWEI -> huawei.allowReturnTransitionOverlap
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
            MapType.GOOGLE -> google.startIntentSenderForResult(intent, requestCode, fillInIntent, flagsMask, flagsValues, extraFlags, options)
            MapType.HUAWEI -> huawei.startIntentSenderForResult(intent, requestCode, fillInIntent, flagsMask, flagsValues, extraFlags, options)
        }
    }

    override fun getAllowEnterTransitionOverlap(): Boolean {
        return when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.allowEnterTransitionOverlap
            MapType.HUAWEI -> huawei.allowEnterTransitionOverlap
        }
    }

    override fun setExitTransition(transition: Any?) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.exitTransition = transition
            MapType.HUAWEI -> huawei.exitTransition = transition
        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onConfigurationChanged(newConfig: Configuration) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.onConfigurationChanged(newConfig)
            MapType.HUAWEI -> huawei.onConfigurationChanged(newConfig)
        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onAttach(context: Context) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.onAttach(context)
            MapType.HUAWEI -> huawei.onAttach(context)
        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onPause() {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.onPause()
            MapType.HUAWEI -> huawei.onPause()
        }
    }

    override fun setArguments(args: Bundle?) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.arguments = args
            MapType.HUAWEI -> huawei.arguments = args
        }
    }

    override fun setSharedElementEnterTransition(transition: Any?) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.sharedElementEnterTransition = transition
            MapType.HUAWEI -> huawei.sharedElementEnterTransition = transition
        }
    }

    override fun setAllowEnterTransitionOverlap(allow: Boolean) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.allowEnterTransitionOverlap = allow
            MapType.HUAWEI -> huawei.allowEnterTransitionOverlap = allow
        }
    }

    override fun dump(
        prefix: String,
        fd: FileDescriptor?,
        writer: PrintWriter,
        args: Array<out String>?
    ) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.dump(prefix, fd, writer, args)
            MapType.HUAWEI -> huawei.dump(prefix, fd, writer, args)
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.onPrepareOptionsMenu(menu)
            MapType.HUAWEI -> huawei.onPrepareOptionsMenu(menu)
        }
    }

    override fun setEnterSharedElementCallback(callback: SharedElementCallback?) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.setEnterSharedElementCallback(callback)
            MapType.HUAWEI -> huawei.setEnterSharedElementCallback(callback)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.onActivityResult(requestCode, resultCode, data)
            MapType.HUAWEI -> huawei.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun getReturnTransition(): Any? {
        return when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.returnTransition
            MapType.HUAWEI -> huawei.returnTransition
        }
    }

    override fun onCreateAnimator(transit: Int, enter: Boolean, nextAnim: Int): Animator? {
        return when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.onCreateAnimator(transit, enter, nextAnim)
            MapType.HUAWEI -> huawei.onCreateAnimator(transit, enter, nextAnim)
        }
    }

    override fun onMultiWindowModeChanged(isInMultiWindowMode: Boolean) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.onMultiWindowModeChanged(isInMultiWindowMode)
            MapType.HUAWEI -> huawei.onMultiWindowModeChanged(isInMultiWindowMode)
        }
    }

    override fun setEnterTransition(transition: Any?) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.enterTransition = transition
            MapType.HUAWEI -> huawei.enterTransition = transition
        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onInflate(context: Context, attrs: AttributeSet, savedInstanceState: Bundle?) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.onInflate(context, attrs, savedInstanceState)
            MapType.HUAWEI -> huawei.onInflate(context, attrs, savedInstanceState)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.onViewCreated(view, savedInstanceState)
            MapType.HUAWEI -> huawei.onViewCreated(view, savedInstanceState)
        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.onActivityCreated(savedInstanceState)
            MapType.HUAWEI -> huawei.onActivityCreated(savedInstanceState)
        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onCreate(savedInstanceState: Bundle?) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.onCreate(savedInstanceState)
            MapType.HUAWEI -> huawei.onCreate(savedInstanceState)
        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onLowMemory() {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.onLowMemory()
            MapType.HUAWEI -> huawei.onLowMemory()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.onCreateOptionsMenu(menu, inflater)
            MapType.HUAWEI -> huawei.onCreateOptionsMenu(menu, inflater)
        }
    }

    override fun startPostponedEnterTransition() {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.startPostponedEnterTransition()
            MapType.HUAWEI -> huawei.startPostponedEnterTransition()
        }
    }

    override fun getEnterTransition(): Any? {
        return when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.enterTransition
            MapType.HUAWEI -> huawei.enterTransition
        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onStart() {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.onStart()
            MapType.HUAWEI -> huawei.onStart()
        }
    }

    override fun setRetainInstance(retain: Boolean) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.retainInstance = retain
            MapType.HUAWEI -> huawei.retainInstance = retain
        }
    }

    override fun unregisterForContextMenu(view: View) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.unregisterForContextMenu(view)
            MapType.HUAWEI -> huawei.unregisterForContextMenu(view)
        }
    }

    override fun registerForContextMenu(view: View) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.registerForContextMenu(view)
            MapType.HUAWEI -> huawei.registerForContextMenu(view)
        }
    }

    override fun getReenterTransition(): Any? {
        return when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.reenterTransition
            MapType.HUAWEI -> huawei.reenterTransition
        }
    }

    override fun onDestroyOptionsMenu() {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.onDestroyOptionsMenu()
            MapType.HUAWEI -> huawei.onDestroyOptionsMenu()
        }
    }

    override fun getLifecycle(): Lifecycle {
        return when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.lifecycle
            MapType.HUAWEI -> huawei.lifecycle
        }
    }

    override fun setTargetFragment(fragment: Fragment?, requestCode: Int) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.setTargetFragment(fragment, requestCode)
            MapType.HUAWEI -> huawei.setTargetFragment(fragment, requestCode)
        }
    }

    override fun onOptionsMenuClosed(menu: Menu) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.onOptionsMenuClosed(menu)
            MapType.HUAWEI -> huawei.onOptionsMenuClosed(menu)
        }
    }

    override fun getSharedElementEnterTransition(): Any? {
        return when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.sharedElementEnterTransition
            MapType.HUAWEI -> huawei.sharedElementEnterTransition
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.onRequestPermissionsResult(requestCode, permissions, grantResults)
            MapType.HUAWEI -> huawei.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    override fun postponeEnterTransition() {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.postponeEnterTransition()
            MapType.HUAWEI -> huawei.postponeEnterTransition()
        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onResume() {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.onResume()
            MapType.HUAWEI -> huawei.onResume()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.onSaveInstanceState(outState)
            MapType.HUAWEI -> huawei.onSaveInstanceState(outState)
        }
    }

    override fun getViewLifecycleOwnerLiveData(): LiveData<LifecycleOwner> {
        return when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.viewLifecycleOwnerLiveData
            MapType.HUAWEI -> huawei.viewLifecycleOwnerLiveData
        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onDetach() {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.onDetach()
            MapType.HUAWEI -> huawei.onDetach()
        }
    }

    override fun shouldShowRequestPermissionRationale(permission: String): Boolean {
        return when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.shouldShowRequestPermissionRationale(permission)
            MapType.HUAWEI -> huawei.shouldShowRequestPermissionRationale(permission)
        }
    }

    override fun startActivity(intent: Intent?) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.startActivity(intent)
            MapType.HUAWEI -> huawei.startActivity(intent)
        }
    }

    override fun startActivity(intent: Intent?, options: Bundle?) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.startActivity(intent, options)
            MapType.HUAWEI -> huawei.startActivity(intent, options)
        }
    }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        return when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.onCreateAnimation(transit, enter, nextAnim)
            MapType.HUAWEI -> huawei.onCreateAnimation(transit, enter, nextAnim)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.onCreateView(inflater, container, savedInstanceState)
            MapType.HUAWEI -> huawei.onCreateView(inflater, container, savedInstanceState)
        }
    }

    override fun toString(): String {
        return when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.toString()
            MapType.HUAWEI -> huawei.toString()
        }
    }

    override fun onAttachFragment(childFragment: Fragment) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.onAttachFragment(childFragment)
            MapType.HUAWEI -> huawei.onAttachFragment(childFragment)
        }
    }

    override fun onGetLayoutInflater(savedInstanceState: Bundle?): LayoutInflater {
        return when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.onGetLayoutInflater(savedInstanceState)
            MapType.HUAWEI -> huawei.onGetLayoutInflater(savedInstanceState)
        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.onHiddenChanged(hidden)
            MapType.HUAWEI -> huawei.onHiddenChanged(hidden)
        }
    }

    override fun getContext(): Context? {
        return when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.context
            MapType.HUAWEI -> huawei.context
        }
    }

    override fun setMenuVisibility(menuVisible: Boolean) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.setMenuVisibility(menuVisible)
            MapType.HUAWEI -> huawei.setMenuVisibility(menuVisible)
        }
    }

    override fun setInitialSavedState(state: SavedState?) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.setInitialSavedState(state)
            MapType.HUAWEI -> huawei.setInitialSavedState(state)
        }
    }

    override fun setReturnTransition(transition: Any?) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.returnTransition = transition
            MapType.HUAWEI -> huawei.returnTransition = transition
        }
    }

    override fun setReenterTransition(transition: Any?) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.reenterTransition = transition
            MapType.HUAWEI -> huawei.reenterTransition = transition
        }
    }

    override fun getSharedElementReturnTransition(): Any? {
        return when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.sharedElementReturnTransition
            MapType.HUAWEI -> huawei.sharedElementReturnTransition
        }
    }

    override fun startActivityForResult(intent: Intent?, requestCode: Int) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.startActivityForResult(intent, requestCode)
            MapType.HUAWEI -> huawei.startActivityForResult(intent, requestCode)
        }
    }

    override fun startActivityForResult(intent: Intent?, requestCode: Int, options: Bundle?) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.startActivityForResult(intent, requestCode, options)
            MapType.HUAWEI -> huawei.startActivityForResult(intent, requestCode, options)
        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onDestroyView() {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.onDestroyView()
            MapType.HUAWEI -> huawei.onDestroyView()
        }
    }

    override fun setHasOptionsMenu(hasMenu: Boolean) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.setHasOptionsMenu(hasMenu)
            MapType.HUAWEI -> huawei.setHasOptionsMenu(hasMenu)
        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onStop() {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.onStop()
            MapType.HUAWEI -> huawei.onStop()
        }
    }

    override fun setSharedElementReturnTransition(transition: Any?) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.sharedElementReturnTransition = transition
            MapType.HUAWEI -> huawei.sharedElementReturnTransition = transition
        }
    }

    override fun getViewLifecycleOwner(): LifecycleOwner {
        return when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.viewLifecycleOwner
            MapType.HUAWEI -> huawei.viewLifecycleOwner
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.onOptionsItemSelected(item)
            MapType.HUAWEI -> huawei.onOptionsItemSelected(item)
        }
    }

    override fun getExitTransition(): Any? {
        return when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.exitTransition
            MapType.HUAWEI -> huawei.exitTransition
        }
    }

    override fun setAllowReturnTransitionOverlap(allow: Boolean) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.allowReturnTransitionOverlap = allow
            MapType.HUAWEI -> huawei.allowReturnTransitionOverlap = allow
        }
    }

    override fun onPictureInPictureModeChanged(isInPictureInPictureMode: Boolean) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.onPictureInPictureModeChanged(isInPictureInPictureMode)
            MapType.HUAWEI -> huawei.onPictureInPictureModeChanged(isInPictureInPictureMode)
        }
    }

    override fun getViewModelStore(): ViewModelStore {
        return when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.viewModelStore
            MapType.HUAWEI -> huawei.viewModelStore
        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.onViewStateRestored(savedInstanceState)
            MapType.HUAWEI -> huawei.onViewStateRestored(savedInstanceState)
        }
    }

    override fun onPrimaryNavigationFragmentChanged(isPrimaryNavigationFragment: Boolean) {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.onPrimaryNavigationFragmentChanged(isPrimaryNavigationFragment)
            MapType.HUAWEI -> huawei.onPrimaryNavigationFragmentChanged(isPrimaryNavigationFragment)
        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onDestroy() {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> google.onDestroy()
            MapType.HUAWEI -> huawei.onDestroy()
        }
    }
}
