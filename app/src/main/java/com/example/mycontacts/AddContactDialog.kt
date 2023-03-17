package com.example.mycontacts

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.mycontacts.databinding.DialogAddContactBinding
import com.example.mycontacts.vh.Contact
import com.example.mycontacts.vh.ContactGenerator


class AddContactDialog : DialogFragment() {

    interface ConfirmationListener {
        fun confirmButtonClicked(contact: Contact)
    }

    private lateinit var listener: ConfirmationListener
    private lateinit var _binding: DialogAddContactBinding
    private val _contactGenerator = ContactGenerator()

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            listener = activity as ConfirmationListener
        } catch (e: ClassCastException) {
            throw ClassCastException(activity.toString() + " must implement ConfirmationListener")
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
            listener.confirmButtonClicked(
                with(_binding) {
                    Log.i("myTag", "Створюємо контакт")
                    _contactGenerator.getContact(
                        userName = userNameField.text.toString(),
                        address = userAddressField.text.toString()
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


