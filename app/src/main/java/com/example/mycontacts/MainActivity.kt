package com.example.mycontacts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mycontacts.databinding.ActivityMainBinding
import com.example.mycontacts.vh.ContactAdapter
import com.example.mycontacts.vh.ContactViewModel
import androidx.activity.viewModels
import com.example.mycontacts.vh.Contact
import com.example.mycontacts.vh.ContactActionListener

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter: ContactAdapter by lazy{
        ContactAdapter(contactActionListener = object: ContactActionListener{
            override fun onContactDelete (contact: Contact){
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

        binding.recyclerView.layoutManager = manager
        binding.recyclerView.adapter = adapter
        setObservers()
        }

    private fun setObservers() {
        contactViewModel.contactState.observe(this){
            adapter.submitList(it.toMutableList())
        }
    }

}
