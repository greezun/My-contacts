package com.example.mycontacts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mycontacts.databinding.ActivityMainBinding
import com.example.mycontacts.vh.ContactAdapter
import com.example.mycontacts.vh.ContactViewModel
import androidx.activity.viewModels
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.mycontacts.vh.Contact
import com.example.mycontacts.vh.ContactActionListener
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity(), AddContactDialog.ConfirmationListener {

    private lateinit var addContact: AppCompatTextView
    private lateinit var binding: ActivityMainBinding
    private val adapter: ContactAdapter by lazy {
        ContactAdapter(contactActionListener = object : ContactActionListener {
            override fun onContactDelete(contact: Contact) {
                contactViewModel.deleteContact(contact)
            }
        })
    }
    private val contactViewModel: ContactViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val manager = LinearLayoutManager(this)
        addContact = binding.tvAddContact
        binding.recyclerView.layoutManager = manager
        binding.recyclerView.adapter = adapter
        setObservers()
        onClickListener()

    }

    private fun onClickListener()  {
        binding.tvAddContact.setOnClickListener{
            val contactAdder = AddContactDialog()
            contactAdder.show(supportFragmentManager,AddContactDialog.TAG)
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

    override fun confirmButtonClicked(contact: Contact) {
        contactViewModel.addContact(contact)
    }
}