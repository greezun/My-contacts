package com.mycontacts.vh


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mycontacts.AddContactDialog
import com.mycontacts.model.Contact
import com.mycontacts.model.ContactRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ContactViewModel : ViewModel(), AddContactDialog.ConfirmationListener {

    private var _contactState = MutableStateFlow<List<Contact>>(emptyList())
    val contactState: StateFlow<List<Contact>> = _contactState
    private val contactRepository = ContactRepository()

    init {
        viewModelScope.launch {
            contactRepository.getContacts().collectLatest { contactList ->
                _contactState.value = contactList
            }
        }
    }

    fun deleteContact(contact: Contact): Int {
        return contactRepository.delete(contact)
    }

    fun addContact(contact: Contact) {
        contactRepository.add(contact)
    }

    fun addContactOnIndex(index: Int, contact: Contact) {
        contactRepository.add(index, contact)
    }

    fun getContact(index: Int) = contactRepository.getContactOfIndex(index)

    override fun onConfirmButtonClicked(contact: Contact) {
        addContact(contact)
    }



}