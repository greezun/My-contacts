package com.mycontacts.ui.contact.adapter.diffUtil

import androidx.recyclerview.widget.DiffUtil
import com.mycontacts.model.Contact
//todo rename to diffCallBack?
class ContactComparator: DiffUtil.ItemCallback<Contact>() {
    override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
        return oldItem == newItem //todo how can we compare two Data classes?

    }
}