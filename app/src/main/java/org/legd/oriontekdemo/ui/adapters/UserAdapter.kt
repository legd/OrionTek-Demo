package org.legd.oriontekdemo.ui.adapters

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.legd.oriontekdemo.R
import org.legd.oriontekdemo.database.model.User
import org.legd.oriontekdemo.database.relations.UsersWithAddresses
import org.legd.oriontekdemo.ui.UserDetails

/**
 * Class of the custom adapter for the users list.
 */
class UserAdapter(private val context: Context) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private var userList: MutableList<User> = ArrayList()
//    private var userList: MutableList<UsersWithAddresses> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemHolder = LayoutInflater.from(parent.context).inflate(R.layout.user_holder, parent, false)
        return UserViewHolder(itemHolder)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = this.userList[position]
//        val item = this.userList[position]


        holder.name.text = String.format("%s%s%s", item.firstName, " ", item.lastName)
        holder.name.setOnClickListener(View.OnClickListener {
            val userDetailsIntent = Intent(this.context, UserDetails::class.java)
//            val addressesBundle = Bundle()
//            addressesBundle.putParcelableArray("addresses", item.addresses.toTypedArray())
//            userDetailsIntent.putExtra("user_addresses", addressesBundle)
            userDetailsIntent.putExtra("user", item)
            this.context.startActivity(userDetailsIntent)
        })
    }

    override fun getItemCount(): Int {
        return this.userList.size
    }

//    fun updateList(userList: List<UsersWithAddresses>) {
//        this.userList.clear()
//        this.userList.addAll(userList)
//        notifyDataSetChanged()
//    }

    fun updateList(userList: List<User>) {
        this.userList.clear()
        this.userList.addAll(userList)
        notifyDataSetChanged()
    }

    //===========================================================================
    //                           PRIVATE CLASSES
    //===========================================================================

    /**
     * Class for the implementation of the ViewHolder design pattern used to represent the users
     * list.
     */
    class UserViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val name = item.findViewById<TextView>(R.id.user_name)
    }
}