package org.legd.oriontekdemo.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.github.clans.fab.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import org.legd.oriontekdemo.R
import org.legd.oriontekdemo.app.DemoApplication
import org.legd.oriontekdemo.database.model.Address
import org.legd.oriontekdemo.database.model.User
import org.legd.oriontekdemo.ui.adapters.AddressAdapter
import org.legd.oriontekdemo.viewmodel.UserViewModel
import org.legd.oriontekdemo.viewmodel.UserViewModelFactory

class UserDetails : AppCompatActivity() {

    private var selectedUser: User? = null
    private lateinit var addresses: RecyclerView
    private lateinit var addressesAdapter: AddressAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_details)

        val fullName = findViewById<TextView>(R.id.full_name)
        val email = findViewById<TextView>(R.id.email)
        val phoneNumber = findViewById<TextView>(R.id.phone_number)
        this.addresses = findViewById(R.id.user_addresses)

        if(intent.extras != null) {
            selectedUser = intent.getParcelableExtra<User>("user")
            fullName.text = String.format("%s%s%s", selectedUser!!.firstName, " ", selectedUser!!.lastName)
            email.text = selectedUser!!.email
            phoneNumber.text = selectedUser!!.phoneNumber
        }

        val addAddress = findViewById<FloatingActionButton>(R.id.add_address)
        addAddress.setOnClickListener(View.OnClickListener {
            val addAddressIntent = Intent(this, AddAddressScreen::class.java)
            addAddressIntent.putExtra("user_id", selectedUser!!.id)
            startActivity(addAddressIntent)
        })

        val editUser = findViewById<FloatingActionButton>(R.id.edit_user)
        editUser.setOnClickListener(View.OnClickListener {
            val editUserIntent = Intent(this, AddUserScreen::class.java)
            editUserIntent.putExtra("user", selectedUser)
            startActivity(editUserIntent)
        })

        val deleteUser = findViewById<FloatingActionButton>(R.id.delete_user)
        deleteUser.setOnClickListener(View.OnClickListener {
            val usersModel: UserViewModel by viewModels {
                UserViewModelFactory((application as DemoApplication).appRepository)
            }
            usersModel.deleteUser(selectedUser!!.id)
            Snackbar.make(it, R.string.user_deleted, Snackbar.LENGTH_LONG).show()
            this.finish()
        })

        initAddressesList()
        fillAddressesList()
    }

    override fun onResume() {
        super.onResume()
        fillAddressesList()
    }

    private fun initAddressesList() {
        this.linearLayoutManager = LinearLayoutManager(this)
        this.addresses.layoutManager = linearLayoutManager
        this.addressesAdapter = AddressAdapter(applicationContext)
        this.addresses.adapter = this.addressesAdapter
    }

    private fun fillAddressesList() {
        val usersModel: UserViewModel by viewModels {
            UserViewModelFactory((application as DemoApplication).appRepository)
        }
        usersModel.getUserAddresses(selectedUser!!.id).observe(this, Observer<List<Address>> { addresses ->
            this.addressesAdapter.updateList(addresses)
        })
    }
}