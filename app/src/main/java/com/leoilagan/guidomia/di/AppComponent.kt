package com.leoilagan.guidomia.di

import android.content.Context
import com.leoilagan.guidomia.ui.home.HomeFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


/**
 * To generate dagger app component
 * we define all starting points of graph here
 */
@Singleton
@Component(modules = [AppModule::class,ViewModelModule::class])
interface AppComponent {

    /**
     *  Factory to create instances of the AppComponent -
     *  factory tells how to create objects, now we are using bindsinstance of context
     *  so context will be available in dagger graph
     */
    @Component.Factory
    interface Factory {
        /**
         *  With @BindsInstance, the Context passed in will be available in the graph
         */
        fun create(@BindsInstance context: Context): AppComponent
    }

    /**
     * inject all activities and fragments here
     */
    fun inject(homeFragment: HomeFragment)
}