package com.example.mycontacts.vh

import androidx.recyclerview.widget.DiffUtil

class ContactComparator: DiffUtil.ItemCallback<Contact>() {
    override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
        return oldItem.userName == newItem.userName
    }

    override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
        return oldItem == newItem

    }
}