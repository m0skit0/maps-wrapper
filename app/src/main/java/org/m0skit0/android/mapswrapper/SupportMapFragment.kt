package org.m0skit0.android.mapswrapper

import android.animation.Animator
import android.app.Activity
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
import androidx.loader.app.LoaderManager
import java.io.FileDescriptor
import java.io.PrintWriter

class SupportMapFragment : Fragment() {

    private lateinit var google: com.google.android.gms.maps.SupportMapFragment
    private lateinit var huawei: com.huawei.hms.maps.SupportMapFragment

    init {
        when (MapsConfiguration.type) {
            MapType.GOOGLE -> google = com.google.android.gms.maps.SupportMapFragment.newInstance()
            MapType.HUAWEI -> huawei = com.huawei.hms.maps.SupportMapFragment.newInstance()
        }
    }

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
        return super.getAllowReturnTransitionOverlap()
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
        super.startIntentSenderForResult(
            intent,
            requestCode,
            fillInIntent,
            flagsMask,
            flagsValues,
            extraFlags,
            options
        )
    }

    override fun setNextTransition(nextTransition: Int, nextTransitionStyle: Int) {
        super.setNextTransition(nextTransition, nextTransitionStyle)
    }

    override fun getAllowEnterTransitionOverlap(): Boolean {
        return super.getAllowEnterTransitionOverlap()
    }

    override fun setExitTransition(transition: Any?) {
        super.setExitTransition(transition)
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
    }

    override fun performResume() {
        super.performResume()
    }

    override fun setOnStartEnterTransitionListener(listener: OnStartEnterTransitionListener?) {
        super.setOnStartEnterTransitionListener(listener)
    }

    override fun performDetach() {
        super.performDetach()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun setArguments(args: Bundle?) {
        super.setArguments(args)
    }

    override fun setSharedElementEnterTransition(transition: Any?) {
        super.setSharedElementEnterTransition(transition)
    }

    override fun performStart() {
        super.performStart()
    }

    override fun setAllowEnterTransitionOverlap(allow: Boolean) {
        super.setAllowEnterTransitionOverlap(allow)
    }

    override fun dump(
        prefix: String,
        fd: FileDescriptor?,
        writer: PrintWriter,
        args: Array<out String>?
    ) {
        super.dump(prefix, fd, writer, args)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
    }

    override fun setEnterSharedElementCallback(callback: SharedElementCallback?) {
        super.setEnterSharedElementCallback(callback)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun getReturnTransition(): Any? {
        return super.getReturnTransition()
    }

    override fun noteStateNotSaved() {
        super.noteStateNotSaved()
    }

    override fun performPictureInPictureModeChanged(isInPictureInPictureMode: Boolean) {
        super.performPictureInPictureModeChanged(isInPictureInPictureMode)
    }

    override fun setNextAnim(animResourceId: Int) {
        super.setNextAnim(animResourceId)
    }

    override fun getNextTransitionStyle(): Int {
        return super.getNextTransitionStyle()
    }

    override fun onCreateAnimator(transit: Int, enter: Boolean, nextAnim: Int): Animator? {
        return super.onCreateAnimator(transit, enter, nextAnim)
    }

    override fun getLayoutInflater(savedFragmentState: Bundle?): LayoutInflater {
        return super.getLayoutInflater(savedFragmentState)
    }

    override fun performLowMemory() {
        super.performLowMemory()
    }

    override fun onMultiWindowModeChanged(isInMultiWindowMode: Boolean) {
        super.onMultiWindowModeChanged(isInMultiWindowMode)
    }

    override fun setEnterTransition(transition: Any?) {
        super.setEnterTransition(transition)
    }

    override fun onInflate(context: Context, attrs: AttributeSet, savedInstanceState: Bundle?) {
        super.onInflate(context, attrs, savedInstanceState)
    }

    override fun onInflate(activity: Activity, attrs: AttributeSet, savedInstanceState: Bundle?) {
        super.onInflate(activity, attrs, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun performPrimaryNavigationFragmentChanged() {
        super.performPrimaryNavigationFragmentChanged()
    }

    override fun performCreate(savedInstanceState: Bundle?) {
        super.performCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun performContextItemSelected(item: MenuItem): Boolean {
        return super.performContextItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onLowMemory() {
        super.onLowMemory()
    }

    override fun restoreChildFragmentState(savedInstanceState: Bundle?) {
        super.restoreChildFragmentState(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun startPostponedEnterTransition() {
        super.startPostponedEnterTransition()
    }

    override fun isHideReplaced(): Boolean {
        return super.isHideReplaced()
    }

    override fun getLoaderManager(): LoaderManager {
        return super.getLoaderManager()
    }

    override fun callStartTransitionListener() {
        super.callStartTransitionListener()
    }

    override fun performDestroyView() {
        super.performDestroyView()
    }

    override fun getAnimator(): Animator {
        return super.getAnimator()
    }

    override fun getEnterTransition(): Any? {
        return super.getEnterTransition()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun setRetainInstance(retain: Boolean) {
        super.setRetainInstance(retain)
    }

    override fun unregisterForContextMenu(view: View) {
        super.unregisterForContextMenu(view)
    }

    override fun registerForContextMenu(view: View) {
        super.registerForContextMenu(view)
    }

    override fun getReenterTransition(): Any? {
        return super.getReenterTransition()
    }

    override fun getStateAfterAnimating(): Int {
        return super.getStateAfterAnimating()
    }

    override fun onDestroyOptionsMenu() {
        super.onDestroyOptionsMenu()
    }

    override fun getLifecycle(): Lifecycle {
        return super.getLifecycle()
    }

    override fun setTargetFragment(fragment: Fragment?, requestCode: Int) {
        super.setTargetFragment(fragment, requestCode)
    }

    override fun onOptionsMenuClosed(menu: Menu) {
        super.onOptionsMenuClosed(menu)
    }

    override fun getSharedElementEnterTransition(): Any? {
        return super.getSharedElementEnterTransition()
    }

    override fun performGetLayoutInflater(savedInstanceState: Bundle?): LayoutInflater {
        return super.performGetLayoutInflater(savedInstanceState)
    }

    override fun performSaveInstanceState(outState: Bundle?) {
        super.performSaveInstanceState(outState)
    }

    override fun getNextAnim(): Int {
        return super.getNextAnim()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun postponeEnterTransition() {
        super.postponeEnterTransition()
    }

    override fun performStop() {
        super.performStop()
    }

    override fun performOptionsItemSelected(item: MenuItem): Boolean {
        return super.performOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun setHideReplaced(replaced: Boolean) {
        super.setHideReplaced(replaced)
    }

    override fun performConfigurationChanged(newConfig: Configuration) {
        super.performConfigurationChanged(newConfig)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun getViewLifecycleOwnerLiveData(): LiveData<LifecycleOwner> {
        return super.getViewLifecycleOwnerLiveData()
    }

    override fun getNextTransition(): Int {
        return super.getNextTransition()
    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun shouldShowRequestPermissionRationale(permission: String): Boolean {
        return super.shouldShowRequestPermissionRationale(permission)
    }

    override fun getEnterTransitionCallback(): SharedElementCallback {
        return super.getEnterTransitionCallback()
    }

    override fun startActivity(intent: Intent?) {
        super.startActivity(intent)
    }

    override fun startActivity(intent: Intent?, options: Bundle?) {
        super.startActivity(intent, options)
    }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        return super.onCreateAnimation(transit, enter, nextAnim)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun getExitTransitionCallback(): SharedElementCallback {
        return super.getExitTransitionCallback()
    }

    override fun getUserVisibleHint(): Boolean {
        return super.getUserVisibleHint()
    }

    override fun performPrepareOptionsMenu(menu: Menu): Boolean {
        return super.performPrepareOptionsMenu(menu)
    }

    override fun toString(): String {
        return super.toString()
    }

    override fun onAttachFragment(childFragment: Fragment) {
        super.onAttachFragment(childFragment)
    }

    override fun performAttach() {
        super.performAttach()
    }

    override fun onGetLayoutInflater(savedInstanceState: Bundle?): LayoutInflater {
        return super.onGetLayoutInflater(savedInstanceState)
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
    }

    override fun getContext(): Context? {
        return super.getContext()
    }

    override fun setMenuVisibility(menuVisible: Boolean) {
        super.setMenuVisibility(menuVisible)
    }

    override fun setStateAfterAnimating(state: Int) {
        super.setStateAfterAnimating(state)
    }

    override fun setInitialSavedState(state: SavedState?) {
        super.setInitialSavedState(state)
    }

    override fun setReturnTransition(transition: Any?) {
        super.setReturnTransition(transition)
    }

    override fun setReenterTransition(transition: Any?) {
        super.setReenterTransition(transition)
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
    }

    override fun getSharedElementReturnTransition(): Any? {
        return super.getSharedElementReturnTransition()
    }

    override fun startActivityForResult(intent: Intent?, requestCode: Int) {
        super.startActivityForResult(intent, requestCode)
    }

    override fun startActivityForResult(intent: Intent?, requestCode: Int, options: Bundle?) {
        super.startActivityForResult(intent, requestCode, options)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun setHasOptionsMenu(hasMenu: Boolean) {
        super.setHasOptionsMenu(hasMenu)
    }

    override fun findFragmentByWho(who: String): Fragment? {
        return super.findFragmentByWho(who)
    }

    override fun performOptionsMenuClosed(menu: Menu) {
        super.performOptionsMenuClosed(menu)
    }

    override fun performActivityCreated(savedInstanceState: Bundle?) {
        super.performActivityCreated(savedInstanceState)
    }

    override fun performMultiWindowModeChanged(isInMultiWindowMode: Boolean) {
        super.performMultiWindowModeChanged(isInMultiWindowMode)
    }

    override fun onStop() {
        super.onStop()
    }

    override fun getAnimatingAway(): View {
        return super.getAnimatingAway()
    }

    override fun setSharedElementReturnTransition(transition: Any?) {
        super.setSharedElementReturnTransition(transition)
    }

    override fun performCreateOptionsMenu(menu: Menu, inflater: MenuInflater): Boolean {
        return super.performCreateOptionsMenu(menu, inflater)
    }

    override fun getViewLifecycleOwner(): LifecycleOwner {
        return super.getViewLifecycleOwner()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    override fun getExitTransition(): Any? {
        return super.getExitTransition()
    }

    override fun initState() {
        super.initState()
    }

    override fun setAnimatingAway(view: View?) {
        super.setAnimatingAway(view)
    }

    override fun setAllowReturnTransitionOverlap(allow: Boolean) {
        super.setAllowReturnTransitionOverlap(allow)
    }

    override fun setAnimator(animator: Animator?) {
        super.setAnimator(animator)
    }

    override fun performDestroy() {
        super.performDestroy()
    }

    override fun isPostponed(): Boolean {
        return super.isPostponed()
    }

    override fun onPictureInPictureModeChanged(isInPictureInPictureMode: Boolean) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode)
    }

    override fun getViewModelStore(): ViewModelStore {
        return super.getViewModelStore()
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
    }

    override fun performCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) {
        super.performCreateView(inflater, container, savedInstanceState)
    }

    override fun onPrimaryNavigationFragmentChanged(isPrimaryNavigationFragment: Boolean) {
        super.onPrimaryNavigationFragmentChanged(isPrimaryNavigationFragment)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
