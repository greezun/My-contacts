package com.mycontacts.ui.contact


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mycontacts.model.Contact
import com.mycontacts.data.contact.ContactRepository
import com.mycontacts.ui.addContact.AddContactDialog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch



class ContactViewModel : ViewModel(), AddContactDialog.ConfirmationListener {

    private val _contactState = MutableSharedFlow<List<Contact>>()
    val contactState: SharedFlow<List<Contact>> = _contactState

    private val contactRepository = ContactRepository()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            _contactState.emit(contactRepository.contacts.toMutableList())
        }
    }

    fun deleteContact(contact: Contact): Int {
        val index = contactRepository.delete(contact)
        viewModelScope.launch(Dispatchers.IO) {

            _contactState.emit(contactRepository.contacts)
        }
        return index
    }

    fun addContact(contact: Contact) {
        viewModelScope.launch(Dispatchers.IO) {
            contactRepository.add(contact)
            _contactState.emit(contactRepository.contacts)
        }
    }

    fun addContactOnIndex(index: Int, contact: Contact) {
        viewModelScope.launch(Dispatchers.IO) {
            contactRepository.add(index, contact)
            _contactState.emit(contactRepository.contacts)
        }
    }

    fun getContact(index: Int) = contactRepository.getContactOfIndex(index)

    override fun onConfirmButtonClicked(contact: Contact) {
        addContact(contact)
    }
}
