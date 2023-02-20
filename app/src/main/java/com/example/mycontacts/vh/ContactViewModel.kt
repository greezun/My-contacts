package com.example.mycontacts.vh

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
class ContactViewModel: ViewModel() {

    private val _contactState = MutableLiveData<List<Contact>>(emptyList())
    val contactState: LiveData<List<Contact>> = _contactState
    private val contactBase = ContactGenerator()

    init {
        _contactState.value = contactBase.getContacts()
    }

    fun deleteContact (contact: Contact){
        _contactState.value = _contactState.value?.toMutableList()?.apply {
            remove(contact)
        }
    }

}