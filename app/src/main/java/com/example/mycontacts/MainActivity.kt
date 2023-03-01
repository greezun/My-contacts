package com.example.mycontacts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mycontacts.databinding.ActivityMainBinding
import com.example.mycontacts.vh.ContactAdapter
import com.example.mycontacts.vh.ContactViewModel
import androidx.activity.viewModels
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.lifecycleScope
import com.example.mycontacts.vh.Contact
import com.example.mycontacts.vh.ContactActionListener



class MainActivity : AppCompatActivity() {

    lateinit var addContact: AppCompatTextView
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
            contactAdder.show(supportFragmentManager,"add_contact")
        }
    }

    fun showAddContactDialog(v: View){
        val contactAdder:AddContactDialog = AddContactDialog()
        contactAdder.show(supportFragmentManager,"add_contact")
    }

    private fun setObservers() {
        lifecycleScope.launchWhenStarted {
            contactViewModel.contactState.collect { list ->
                adapter.submitList(list)
            }
        }
    }
}