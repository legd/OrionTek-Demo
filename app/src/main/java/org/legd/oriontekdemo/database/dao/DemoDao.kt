package org.legd.oriontekdemo.database.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import org.legd.oriontekdemo.database.model.Address
import org.legd.oriontekdemo.database.model.User
import org.legd.oriontekdemo.database.relations.UsersWithAddresses

/**
 * Dao interface for the Demo app database models.
 */
@Dao
interface DemoDao {

    /**
     * Insert a single user.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    /**
     * Update a user.
     */
    @Update
    suspend fun updateUser(user: User)

    /**
     * Deletes the given user
     */
    @Query("DELETE FROM users WHERE id == :id")
    suspend fun deleteUser(id: Long)

    /**
     * Insert a single address.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAddress(address: Address)

    /**
     * Deletes all the user addresses
     */
    @Query("DELETE FROM addresses WHERE user_id == :id")
    suspend fun deleteAddresses(id: Long)

    /**
     * Return all the users with addresses.
     */
    @Transaction
    @Query("SELECT * FROM users")
    fun getAllUsersWithAddress(): Flow<List<UsersWithAddresses>>

    /**
     * Return all the users.
     */
    @Query("SELECT * FROM users")
    fun getAllUsers(): Flow<List<User>>

    /**
     * Return all the users with addresses.
     */
    @Query("SELECT * FROM addresses WHERE  user_id == :userId")
    fun getAllUserAddresses(userId: Long): Flow<List<Address>>
}