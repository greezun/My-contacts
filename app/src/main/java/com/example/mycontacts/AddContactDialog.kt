package com.example.mycontacts

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context

import android.os.Bundle


import android.view.LayoutInflater
import android.view.View

import android.view.ViewGroup
import android.view.Window
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
    private lateinit var _contactGenerator: ContactGenerator

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try {
            listener = activity as ConfirmationListener
        } catch (e: ClassCastException) {
            throw ClassCastException(activity.toString() + " must implement ConfirmationListener")
        }
    }

    @SuppressLint("InflateParams", "UseGetLayoutInflater")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dialog_add_contact, container, false)
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = DialogAddContactBinding.inflate(layoutInflater)
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }

    companion object {
        const val TAG = "add_contact"
    }


}

//
//
////        _binding.buttonAddContact.setOnClickListener {
////            Log.i("myTag", "відкрили діалог")
////            listener.confirmButtonClicked(
////                with(_binding) {
////                    Log.i("myTag", "Створюємо контакт")
////                    _contactGenerator.getContact(
////                        userName = userNameField.toString(),
////                        address = userAddressField.toString()
////                    )
////                }
////            )
////
////        }