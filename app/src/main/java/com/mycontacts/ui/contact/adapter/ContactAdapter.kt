package com.mycontacts.ui.contact.adapter


import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mycontacts.databinding.ContactItemBinding
import com.mycontacts.model.Contact
import com.mycontacts.ui.utils.ext.setContactPhoto
import com.mycontacts.ui.contact.adapter.diffUtil.ContactComparator

// todo move out of here
interface ContactActionListener {
    fun onContactDelete(contact: Contact)
}

class ContactAdapter(private val contactActionListener: ContactActionListener) :
    ListAdapter<Contact, ContactAdapter.ContactHolder>(ContactComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ContactItemBinding.inflate(inflater, parent, false)
        return ContactHolder(binding) //todo rename and move the viewholder out or make it inner
    }

    override fun onBindViewHolder(holder: ContactHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    inner class ContactHolder(
        private val binding: ContactItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bindTo(contact: Contact) {
            with(binding) {
                textViewUserName.text = contact.userName
                textViewCareer.text = contact.address
                imageViewAvatar.setContactPhoto(contact.avatar)
            }
            setListeners(contact)
        }

        private fun setListeners(contact: Contact) {
            binding.basket.setOnClickListener {
                contactActionListener.onContactDelete(contact)
            }
        }
    }
}
