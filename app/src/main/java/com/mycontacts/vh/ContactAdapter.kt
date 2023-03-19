package com.mycontacts.vh


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

import com.example.mycontacts.R
import com.example.mycontacts.databinding.ContactItemBinding
import com.mycontacts.model.Contact
import com.mycontacts.setContactPhoto

interface ContactActionListener {
    fun onContactDelete(contact: Contact)
}

class ContactAdapter(private val contactActionListener: ContactActionListener) :
    ListAdapter<Contact, ContactAdapter.ContactHolder>(ContactComparator()), View.OnClickListener {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ContactItemBinding.inflate(inflater, parent, false)
        binding.basket.setOnClickListener(this)
        return ContactHolder(binding)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun onBindViewHolder(holder: ContactHolder, position: Int) {
        val contact = getItem(position)
        with(holder.binding) {
            basket.tag = contact
            textViewUserName.text = contact.userName
            textViewCareer.text= contact.address
            imageViewAvatar.setContactPhoto(contact.avatar)
        }

    }
    class ContactHolder(
        val binding: ContactItemBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onClick(view: View) {
        val contact = view.tag as Contact
        when (view.id) {
            R.id.basket -> contactActionListener.onContactDelete(contact)
        }

    }
}
