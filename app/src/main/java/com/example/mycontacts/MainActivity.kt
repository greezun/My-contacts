package com.example.mycontacts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mycontacts.databinding.ActivityMainBinding
import com.example.mycontacts.vh.App
import com.example.mycontacts.vh.ContactAdapter
import com.example.mycontacts.vh.ContactGenerator

private lateinit var binding: ActivityMainBinding
private lateinit var adapter: ContactAdapter
private val contactGenerator = ContactGenerator()


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val manager = LinearLayoutManager(this)
        adapter = ContactAdapter()
        adapter.contactList = contactGenerator.getContacts()

        binding.recyclerView.layoutManager = manager
        binding.recyclerView.adapter = adapter

    }
}