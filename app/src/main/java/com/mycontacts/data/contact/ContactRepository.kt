package com.mycontacts.data.contact

import com.mycontacts.model.Contact

private const val IS_GET_PHONE_CONTACTS = false

class ContactRepository {

    private val contactProvider = ContactGenerator()

    private val _contacts = mutableListOf<Contact>()
    val contacts: List<Contact> = _contacts //todo why do you need flow in here?

    init {

        //todo I don`t believe that contacts.value can be non empty when it reaches the code
        // so the check below is redundant
//        if (contacts.isEmpty()) {
            _contacts.addAll(
                if (!IS_GET_PHONE_CONTACTS) contactProvider.generateContacts()
                else contactProvider.getContactFromPhone()
            )
//        }
    }


    fun getContactsList(): List<Contact> = contacts // todo SOLID violation. I don`t recommend returning mutable data from here

    fun delete(contact: Contact): Int {
        val index: Int = _contacts.indexOf(contact)
        _contacts.remove(contact)

        return index
    }

    fun add(contact: Contact) {
        _contacts.add(contact)
    }

    fun add(index: Int, contact: Contact) {
        _contacts.add(index, contact)
    }

    //todo it would be better to handle the scenario when the index is out of bounds
    // so as not to receive crash with the exception
    fun getContactOfIndex(index: Int) = contacts.getOrNull(index)


}