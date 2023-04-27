package com.mycontacts.ui.addContact

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.mycontacts.databinding.DialogAddContactBinding
import com.mycontacts.model.Contact
import com.mycontacts.data.contact.ContactGenerator



class AddContactDialog : DialogFragment() {

    //todo I recommned moving this outta here
    interface ConfirmationListener {
        fun onConfirmButtonClicked(contact: Contact)
    }

    private lateinit var listener: ConfirmationListener
    private lateinit var _binding: DialogAddContactBinding //todo there is no need in the "_" before "binding"
    private val _contactGenerator = ContactGenerator()

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            listener = context as ConfirmationListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$context must implement ConfirmationListener")
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = DialogAddContactBinding.inflate(layoutInflater)
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            addContactButtonListener()
            tvBackListener()
            builder.setView(_binding.root).create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    private fun tvBackListener() {
        _binding.ivBack.setOnClickListener { dismiss() }
    }

    private fun addContactButtonListener() {
        _binding.buttonAddContact.setOnClickListener {
            listener.onConfirmButtonClicked(
                with(_binding) {
                    _contactGenerator.createContact(
                        userName = userNameField.text?.toString() ?: "", //todo pls note that in case of null you`ll receive "null"
                        address = userAddressField.text?.toString() ?: ""
                    )
                }
            )
            dismiss()
        }
    }


    companion object {
        const val TAG = "add_contact"
    }
}


