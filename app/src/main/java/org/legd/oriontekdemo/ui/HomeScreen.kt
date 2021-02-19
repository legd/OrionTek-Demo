package org.legd.oriontekdemo.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.clans.fab.FloatingActionButton
import org.legd.oriontekdemo.R
import org.legd.oriontekdemo.app.DemoApplication
import org.legd.oriontekdemo.database.model.User
import org.legd.oriontekdemo.database.relations.UsersWithAddresses
import org.legd.oriontekdemo.ui.adapters.UserAdapter
import org.legd.oriontekdemo.viewmodel.UserViewModel
import org.legd.oriontekdemo.viewmodel.UserViewModelFactory

class HomeScreen : AppCompatActivity() {

    private lateinit var userList: RecyclerView
    private lateinit var userAdapter: UserAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_screen)

        val addUser = findViewById<FloatingActionButton>(R.id.add_user)
        addUser.setOnClickListener(View.OnClickListener {
            val userDetailsIntent = Intent(applicationContext, AddUserScreen::class.java)
            startActivity(userDetailsIntent)
        })

        this.userList = findViewById(R.id.user_list)

        initUserList()
        fillUserList()
    }

    private fun initUserList() {
        this.linearLayoutManager = LinearLayoutManager(this)
        this.userList.layoutManager = linearLayoutManager
        this.userAdapter = UserAdapter(applicationContext)
        this.userList.adapter = this.userAdapter
    }

    private fun fillUserList() {
        val usersModel: UserViewModel by viewModels {
            UserViewModelFactory((application as DemoApplication).appRepository)
        }
        usersModel.userList.observe(this, Observer<List<User>> { users ->
            this.userAdapter.updateList(users)
        })

//        usersModel.userList.observe(this, Observer<List<UsersWithAddresses>> { users ->
//            this.userAdapter.updateList(users)
//        })
    }
}