package com.mycontacts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mycontacts.databinding.ActivityMainBinding
import com.mycontacts.vh.ContactAdapter
import com.mycontacts.vh.ContactViewModel
import androidx.activity.viewModels
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.mycontacts.model.Contact
import com.mycontacts.vh.ContactActionListener
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), AddContactDialog.ConfirmationListener {

    private lateinit var addContact: AppCompatTextView
    private lateinit var binding: ActivityMainBinding
    private val adapter: ContactAdapter by lazy { createAdapter() }
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

    private fun bindFields(binding: ActivityMainBinding) {
        val manager = LinearLayoutManager(this)
        with(receiver = binding) {
            addContact = tvAddContact
            recyclerView.layoutManager = manager
            recyclerView.adapter = adapter
        }
    }

    private fun onSwipeToDeleteListener() {
        val calBack = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = false

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val index = viewHolder.adapterPosition
                val contact = contactViewModel.getContact(index)
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
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                contactViewModel.contactState.collect { list ->
                    adapter.submitList(list)
                }
            }
        }
    }

    override fun onConfirmButtonClicked(contact: Contact) {
        contactViewModel.addContact(contact)
        Snackbar.make(binding.root, MESSAGE_ADD_CONTACT , Snackbar.LENGTH_SHORT)
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
        Snackbar.make(binding.root, MESSAGE_DELETE , Snackbar.LENGTH_LONG)
            .setAction(SNACKBAR_ACTION_BUTTON_TEXT) {
                contactViewModel.addContactOnIndex(index, contact)
            }
            .show()
    }
        companion object{
        private const val MESSAGE_ADD_CONTACT = "Contact added"
        private const val MESSAGE_DELETE = "Contact has been deleted"
        private const val SNACKBAR_ACTION_BUTTON_TEXT = "UNDO"
    }


}