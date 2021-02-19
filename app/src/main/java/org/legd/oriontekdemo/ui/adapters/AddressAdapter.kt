package org.legd.oriontekdemo.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.legd.oriontekdemo.R
import org.legd.oriontekdemo.database.model.Address

/**
 * Class of the custom adapter for the address list.
 */
class AddressAdapter(private val context: Context) : RecyclerView.Adapter<AddressAdapter.AddressViewHolder>() {

    private var addressList: MutableList<Address> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder {
        val itemHolder = LayoutInflater.from(parent.context).inflate(R.layout.address_holder, parent, false)
        return AddressViewHolder(itemHolder)
    }

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        val address = this.addressList[position]

        holder.line1.text = address.line1
        holder.line2.text = address.line2
        holder.city.text = address.city
        holder.zip_code.text = address.zipCode
        holder.phone_number.text = address.phoneNumber
    }

    override fun getItemCount(): Int {
        return this.addressList.size
    }

    fun updateList(addressList: List<Address>) {
        this.addressList.clear()
        this.addressList.addAll(addressList)
        notifyDataSetChanged()
    }

    //===========================================================================
    //                           PRIVATE CLASSES
    //===========================================================================

    /**
     * Class for the implementation of the ViewHolder design pattern used to represent the address
     * list.
     */
    class AddressViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        val line1 = item.findViewById<TextView>(R.id.line1)
        val line2 = item.findViewById<TextView>(R.id.line2)
        val city = item.findViewById<TextView>(R.id.city)
        val zip_code = item.findViewById<TextView>(R.id.zip_code)
        val phone_number = item.findViewById<TextView>(R.id.phone_number)

    }
}