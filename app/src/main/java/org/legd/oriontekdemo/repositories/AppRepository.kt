package org.legd.oriontekdemo.repositories

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import org.legd.oriontekdemo.database.dao.DemoDao
import org.legd.oriontekdemo.database.model.Address
import org.legd.oriontekdemo.database.model.User
import org.legd.oriontekdemo.database.relations.UsersWithAddresses

/**
 * Class in charge to manage the app data source.
 */
class AppRepository(private val demoDao : DemoDao) {

    val users: Flow<List<User>> = demoDao.getAllUsers()
//    val usersWithAddresses: Flow<List<UsersWithAddresses>> = demoDao.getAllUsersWithAddress()

    /**
     * Returns addresses associated with the user id.
     */
    fun getUserAddresses(userId: Long):Flow<List<Address>> {
        return demoDao.getAllUserAddresses(userId)
    }

    /**
     * Insert the given user in the database.
     */
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertUser(user: User) {
        demoDao.insertUser(user)
    }

    /**
     * Insert the given user in the database.
     */
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deletesUser(userId: Long) {
        demoDao.deleteUser(userId)
        demoDao.deleteAddresses(userId)
    }

    /**
     * Insert the given address in the database.
     */
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertAddress(address: Address) {
        demoDao.insertAddress(address)
    }
}