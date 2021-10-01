package com.leoilagan.guidomia.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.leoilagan.guidomia.ui.home.HomeViewModel
import com.leoilagan.guidomia.utils.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * to bind viewmodels to dagger graph
 */
@Module
abstract  class ViewModelModule {

    /**
     * factory tells dagger how to provide
     */
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory


    /**
     * binding home viewmodel into dagger
     */
    @Binds
    @IntoMap
    @ViewModelScope(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel


}