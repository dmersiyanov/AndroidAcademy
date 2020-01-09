package com.dmity.androidacademy.navigation

import android.content.Context
import android.content.Intent
import com.dmity.androidacademy.features.newsList.MainActivity
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {

    class NewsListScreen : SupportAppScreen() {
        override fun getActivityIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
        }
    }

//    class ForwardScreen(private val containerName: String, private val number: Int) :
//        SupportAppScreen() {
//        override fun getFragment(): Fragment {
//            return ForwardFragment.getNewInstance(containerName, number)
//        }
//
//    }

//    class GithubScreen : SupportAppScreen() {
//        override fun getActivityIntent(context: Context): Intent {
//            return Intent(
//                Intent.ACTION_VIEW,
//                Uri.parse("https://github.com/terrakok/Cicerone")
//            )
//        }
//    }
}