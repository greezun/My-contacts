package com.example.mycontacts

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.mycontacts.databinding.AddContactDialodBinding
import java.util.*

class AddContactDialog : DialogFragment() {

    private lateinit var _binding:AddContactDialodBinding

//    @SuppressLint("InflateParams", "UseGetLayoutInflater")
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        Objects.requireNonNull(dialog)?.window!!.requestFeature(Window.FEATURE_NO_TITLE)
//        return inflater.inflate(R.layout.add_contact_dialod, null, false);
//    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//    }

//
@SuppressLint("UseGetLayoutInflater")
override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
    _binding = AddContactDialodBinding.inflate(LayoutInflater.from(context))
    return activity?.let {
        val builder = AlertDialog.Builder(requireContext())

        // Pass null as the parent view because its going in the dialog layout
        builder.setView(_binding.root).create()
    } ?: throw IllegalStateException("Activity cannot be null")
}
}