package com.dmity.androidacademy.core

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dmity.androidacademy.core.extensions.visible
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.view_progress_stub.*
import javax.inject.Inject

abstract class BaseActivity: AppCompatActivity(), SubscriptionsHolder {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override val disposables: CompositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initLayout()
        initUi()
        initUi(savedInstanceState)
        initUx()
    }

    override fun onDestroy() {
        resetCompositeDisposable()
        super.onDestroy()
    }

    protected open fun initUx() {}
    protected open fun initUi() {}
    protected open fun initUi(savedInstanceState: Bundle?) {}
    protected open fun showProgress(show: Boolean) = progress?.visible(show)
    protected open fun showError(errorMessage: String = "", show: Boolean) {}

    private fun initLayout(){
        var layoutId = 0
        val annotation = javaClass.getAnnotation(Layout::class.java)
        if (annotation != null) {
            layoutId = annotation.id
        }

        if (layoutId != 0) {
            setContentView(layoutId)
        }
    }

    protected fun replaceFragment(container: Int, fragment: Fragment, addToBackStack: Boolean) {
        val transaction = supportFragmentManager
                .beginTransaction()
                .replace(container, fragment)

        if(addToBackStack) {
            transaction.addToBackStack(null)
        }

        transaction.commit()

    }

}