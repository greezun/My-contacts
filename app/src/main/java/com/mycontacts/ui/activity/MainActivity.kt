package com.mycontacts.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mycontacts.databinding.ActivityMainBinding
import com.mycontacts.ui.contact.adapter.ContactAdapter
import com.mycontacts.ui.contact.ContactViewModel
import androidx.activity.viewModels
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.mycontacts.model.Contact
import com.mycontacts.ui.contact.adapter.ContactActionListener
import com.google.android.material.snackbar.Snackbar
import com.mycontacts.ui.addContact.AddContactDialog
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

//todo format the code
// refactor packages
// java.lang.IndexOutOfBoundsException: Index: -1, Size: 0 when removing the items quickly
// Element duplication on removing items
class MainActivity : AppCompatActivity(), AddContactDialog.ConfirmationListener {

    private lateinit var addContact: AppCompatTextView
    private lateinit var binding: ActivityMainBinding
    private val adapter: ContactAdapter by lazy {
        //todo it is not necessary to move this functionality to a method
        createAdapter()
    }
    private val contactViewModel: ContactViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bindFields(binding)
        setObservers()
        onClickAddContactListener()
        onSwipeToDeleteListener()

    }

    //todo binding is a global variable, no need to pass it as a parameter
    private fun bindFields(binding: ActivityMainBinding) {
        val manager = LinearLayoutManager(this)
        with(receiver = binding) { //todo receiver looks weird to me, never used it like this
            addContact = tvAddContact
            recyclerView.layoutManager = manager
            recyclerView.adapter = adapter
        }
    }

    private fun onSwipeToDeleteListener() {
        //todo maybe it`s better to replace "RIGHT" with "END"
        val calBack = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val index = viewHolder.adapterPosition
                val contact = contactViewModel.getContact(index) ?: return
                contactViewModel.deleteContact(contact)
                showDeleteMessage(index, contact)
            }
        }
        val myHelper = ItemTouchHelper(calBack)
        myHelper.attachToRecyclerView(binding.recyclerView)

    }

    private fun onClickAddContactListener() {
        binding.tvAddContact.setOnClickListener {
            val contactAdder = AddContactDialog()
            contactAdder.show(supportFragmentManager, AddContactDialog.TAG)
        }
    }

    private fun setObservers() {
        lifecycleScope.launch {
                contactViewModel.contactState.collect { list ->
                    Log.d("MainActivity", "list is $list")
                    adapter.submitList(list.toMutableList())
                }
        }
    }

    override fun onConfirmButtonClicked(contact: Contact) {
        contactViewModel.addContact(contact)
        Snackbar.make(binding.root, MESSAGE_ADD_CONTACT, Snackbar.LENGTH_SHORT)
            .show()
    }

    private fun createAdapter(): ContactAdapter {
        return ContactAdapter(contactActionListener = object : ContactActionListener {
            override fun onContactDelete(contact: Contact) {
                val index = contactViewModel.deleteContact(contact)
                showDeleteMessage(index, contact)
            }
        })
    }

    private fun showDeleteMessage(index: Int, contact: Contact) {
        Snackbar.make(binding.root, MESSAGE_DELETE, Snackbar.LENGTH_LONG)
            .setAction(SNACKBAR_ACTION_BUTTON_TEXT) {
                contactViewModel.addContactOnIndex(index, contact)
            }
            .show()
    }

    companion object {
        private const val MESSAGE_ADD_CONTACT = "Contact added"//todo Move to resources!!!
        private const val MESSAGE_DELETE = "Contact has been deleted" //todo Move to resources!!!
        private const val SNACKBAR_ACTION_BUTTON_TEXT = "UNDO"
    }


}