package com.example.mycontacts.vh

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mycontacts.R
import com.example.mycontacts.databinding.ContactItemBinding

class ContactAdapter : RecyclerView.Adapter<ContactAdapter.ContactHolder>() {

    var contactList: List<Contact> = emptyList()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }

    class ContactHolder(val binding: ContactItemBinding) : RecyclerView.ViewHolder(binding.root) {



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =  ContactItemBinding.inflate(inflater, parent, false)
        return ContactHolder((binding ))
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    override fun onBindViewHolder(holder: ContactHolder, position: Int) {
        val contact = contactList[position]
        val content = holder.itemView.context

        with(holder.binding){

            textViewUserName.text = contact.userName
            textViewCareer.text= contact.address
            Glide.with(content).load(contact.avatar).circleCrop()
                .error(R.drawable.ic_launcher_foreground)
                .placeholder(R.drawable.ic_launcher_foreground).into(imageViewAvatar)

        }
    }
}