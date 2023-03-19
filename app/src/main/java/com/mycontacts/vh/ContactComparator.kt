package com.mycontacts.vh

import androidx.recyclerview.widget.DiffUtil
import com.mycontacts.model.Contact

class ContactComparator: DiffUtil.ItemCallback<Contact>() {
    override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
        return oldItem == newItem

    }
}