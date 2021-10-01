package com.leoilagan.guidomia

import android.app.Application
import com.leoilagan.guidomia.di.AppComponent
import com.leoilagan.guidomia.di.DaggerAppComponent

/**
 * This is the Main Application class
 * it stays in scope from application starting to ending
 * right now i am only injecting dagger here.
 *
 * if you need any other things which need to stay through
 * entire application you can add it here
 */
class BaseApplication  : Application(){
    val appComponent: AppComponent by lazy {

        DaggerAppComponent.factory()
            .create(applicationContext)
    }
}