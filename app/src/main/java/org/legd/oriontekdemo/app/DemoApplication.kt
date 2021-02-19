package org.legd.oriontekdemo.app

import android.app.Application
import org.legd.oriontekdemo.database.DemoDataBase
import org.legd.oriontekdemo.repositories.AppRepository

/**
 * Class declaration for universal access of the user repository and the
 * initialization of the database.
 */
class DemoApplication : Application() {

    val demoDataBase by lazy { DemoDataBase.getDatabase(this) }
    val appRepository by lazy { AppRepository(demoDataBase.getUsersWithAddressesDao()) }
}