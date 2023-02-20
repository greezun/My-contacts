package com.example.mycontacts.vh


import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ContactViewModel: ViewModel() {

    private val _contactState = MutableStateFlow<List<Contact>>(emptyList())
    val contactState: StateFlow<List<Contact>> = _contactState
    private val contactBase = ContactGenerator()

    init {
        _contactState.value = contactBase.getContacts()
    }

    fun deleteContact (contact: Contact){
        _contactState.value = _contactState.value.toMutableList().apply {
            remove(contact)
        }
    }

}