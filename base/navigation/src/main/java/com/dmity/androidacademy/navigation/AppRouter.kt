package com.dmity.androidacademy.navigation

import android.util.Log
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.Screen
import javax.inject.Inject


class AppRouter @Inject constructor() : Router() {


    override fun navigateTo(screen: Screen?) {
        Log.i("navigateTo", screen?.screenKey)
        super.navigateTo(screen)
    }

//    fun navigateToDialog(screenKey: String, tag: String = screenKey, data: Any? = null) {
//        executeCommands(ShowDialog(screenKey, tag, data))
//    }
//
//    fun closeDialog(tag: String?) {
//        executeCommands(CloseDialog(tag))
//    }
//
//    fun navigateWithResult(
//        screenKey: String,
//        transitionData: Any? = null,
//        requestCode: Int,
//        withAnimation: ForwardWithResult.TransAnim? = null
//    ) {
//        executeCommands(ForwardWithResult(screenKey, transitionData, requestCode, withAnimation))
//    }
//
//    fun exitWithResult(screenKey: String?, data: Any?, resultCode: Int) {
//        executeCommands(ExitWithResult(screenKey, data, resultCode))
//    }
//
//    fun exitWithResultAndMessage(screenKey: String?, data: Any?, resultCode: Int, message: String) {
//        executeCommands(
//            ExitWithResult(screenKey, data, resultCode),
//            SystemMessage(message)
//        )
//    }
//
//    fun showDropDown(
//        anchorView: View,
//        items: List<TransferableItem>,
//        menuWorker: MenuWorker
//    ) {
//        executeCommands(Dropdown(anchorView, items, menuWorker))
//    }
//
//
//    fun finishActivity() {
//        executeCommands(FinishActivity())
//    }
//
//
//    fun startNewRootActivity(tag: String, data: Any? = null) {
//        executeCommands(NewRootActivity(tag, data))
//    }
//
//    fun closeDrawer() {
//        executeCommands(CloseDrawer())
//    }
//
//    fun startScreenFromRemoteIntent(intent: Intent?) {
//        executeCommands(StartScreenFromRemoteIntent(intent))
//    }
}
