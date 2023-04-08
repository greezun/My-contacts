package com.mycontacts.model

import kotlinx.coroutines.flow.MutableStateFlow

private const val IS_GET_PHONE_CONTACTS = false

class ContactRepository {

    private val contactProvider = ContactGenerator()
    private var contactsFlow = MutableStateFlow<List<Contact>>(emptyList())

    init {
        if (contactsFlow.value.isEmpty()){
            contactsFlow = if (!IS_GET_PHONE_CONTACTS) contactProvider.generateContacts()
            else contactProvider.getContactFromPhone()
        }
    }



    fun getContacts() = contactsFlow

    fun delete(contact: Contact): Int {
        var index: Int
        contactsFlow.value = contactsFlow.value.toMutableList().apply {
            index = indexOf(contact)
            remove(contact)

        }
        return index
    }

    fun add(contact: Contact) {
        contactsFlow.value = contactsFlow.value.toMutableList().apply {
            add(contact)
        }
    }

    fun add(index: Int, contact: Contact) {
        contactsFlow.value = contactsFlow.value.toMutableList().apply {
            add(index, contact)
        }
    }

    fun getContactOfIndex(index: Int) = contactsFlow.value[index]


}


