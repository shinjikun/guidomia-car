package com.leoilagan.guidomia.ui.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.leoilagan.guidomia.utils.ViewModelFactory

import javax.inject.Inject

/**
 * base fragment all common code in fragments goes here
 */
abstract class BaseFragment<VM : ViewModel> : Fragment() {
    lateinit var viewModel: VM

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        bindContentView()
        super.onCreate(savedInstanceState)
    }

    private fun bindContentView() {
        viewModel = ViewModelProvider(this, viewModelFactory).get(getViewModelClass())
    }

    abstract fun getViewModelClass(): Class<VM>

}