package org.legd.oriontekdemo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import org.legd.oriontekdemo.R
import org.legd.oriontekdemo.app.DemoApplication
import org.legd.oriontekdemo.database.model.User
import org.legd.oriontekdemo.viewmodel.UserViewModel
import org.legd.oriontekdemo.viewmodel.UserViewModelFactory

class AddUserScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_user_screen)

        var userId = 0L
        val firstName = findViewById<EditText>(R.id.first_name)
        val lastName = findViewById<EditText>(R.id.last_name)
        val email = findViewById<EditText>(R.id.email)
        val phoneNumber = findViewById<EditText>(R.id.phone_number)
        val save = findViewById<Button>(R.id.save_user)

        if(intent.extras != null) {
            val user = intent.getParcelableExtra<User>("user")
            userId = user.id
            firstName.setText(user.firstName)
            lastName.setText(user.lastName)
            email.setText(user.email)
            phoneNumber.setText(user.phoneNumber)
        }

        save.setOnClickListener(View.OnClickListener {

            if (firstName.text.toString().isNotEmpty() && lastName.text.toString().isNotEmpty() &&
                    email.text.toString().isNotEmpty() && phoneNumber.text.toString().isNotEmpty()) {

                val newUser = User(userId, firstName.text.toString(), lastName.text.toString(),
                email.text.toString(), phoneNumber.text.toString())

                val usersModel: UserViewModel by viewModels {
                    UserViewModelFactory((application as DemoApplication).appRepository)
                }
                usersModel.saveUser(newUser)
                Snackbar.make(it, R.string.user_added, Snackbar.LENGTH_LONG).show()
                this.finish()
            } else {
                Snackbar.make(it, R.string.input_error, Snackbar.LENGTH_LONG).show()
            }
        })

    }
}