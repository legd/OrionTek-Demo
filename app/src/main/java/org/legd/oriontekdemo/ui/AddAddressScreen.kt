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
import org.legd.oriontekdemo.database.model.Address
import org.legd.oriontekdemo.viewmodel.UserViewModel
import org.legd.oriontekdemo.viewmodel.UserViewModelFactory

class AddAddressScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_address)

        val line1 = findViewById<EditText>(R.id.line1)
        val line2 = findViewById<EditText>(R.id.line2)
        val city = findViewById<EditText>(R.id.city)
        val zipCode = findViewById<EditText>(R.id.zip_code)
        val phoneNumber = findViewById<EditText>(R.id.address_phone_number)
        val save = findViewById<Button>(R.id.save_address)

        save.setOnClickListener(View.OnClickListener {

            if (line1.text.toString().isNotEmpty() && line2.text.toString().isNotEmpty() &&
                city.text.toString().isNotEmpty() && zipCode.text.toString().isNotEmpty() &&
                phoneNumber.text.toString().isNotEmpty()) {

                val userId: Long = intent.getLongExtra("user_id", 0)
                val newAddress = Address(0, userId, line1.text.toString(),
                    line2.text.toString(), city.text.toString(), zipCode.text.toString(),
                    phoneNumber.text.toString())

                val usersModel: UserViewModel by viewModels {
                    UserViewModelFactory((application as DemoApplication).appRepository)
                }
                usersModel.saveAddress(newAddress)
                Snackbar.make(it, R.string.address_added, Snackbar.LENGTH_LONG).show()
                this.finish()
            } else {
                Snackbar.make(it, R.string.input_error, Snackbar.LENGTH_LONG).show()
            }
        })
    }
}