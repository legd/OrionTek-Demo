package org.legd.oriontekdemo.viewmodel

import androidx.lifecycle.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.legd.oriontekdemo.database.model.Address
import org.legd.oriontekdemo.database.model.User
import org.legd.oriontekdemo.database.relations.UsersWithAddresses
import org.legd.oriontekdemo.repositories.AppRepository
import java.lang.IllegalArgumentException

/**
 * ViewModel class to manage the interactions between the UI and user repository.
 */
class UserViewModel(private val repository: AppRepository) : ViewModel() {

    val userList: LiveData<List<User>> = repository.users.asLiveData()
//    val userList: LiveData<List<UsersWithAddresses>> = repository.usersWithAddresses.asLiveData()

    /**
     * Returns addresses associated with the user id.
     */
    fun getUserAddresses(userId: Long): LiveData<List<Address>> {
        return repository.getUserAddresses(userId).asLiveData()
    }

    /**
     * Insert a single user to the database.
     */
    fun saveUser(user: User) = viewModelScope.launch {
        repository.insertUser(user)
    }

    /**
     * Delete a single user to the database.
     */
    fun deleteUser(user_id: Long) = viewModelScope.launch {
        repository.deletesUser(user_id)
    }

    /**
     * Insert a single address to the database.
     */
    fun saveAddress(address: Address) = viewModelScope.launch {
        repository.insertAddress(address)
    }
}

/**
 * Class for the ViewModelProvider factory pattern.
 */
class UserViewModelFactory(private val repository: AppRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}