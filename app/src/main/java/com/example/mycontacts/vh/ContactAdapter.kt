package com.example.mycontacts.vh


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mycontacts.R
import com.example.mycontacts.databinding.ContactItemBinding

interface ContactActionListener {
    fun onContactDelete(contact: Contact)
}

class ContactAdapter(private  val contactActionListener: ContactActionListener) : ListAdapter<Contact, ContactAdapter.ContactHolder>(ContactComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =  ContactItemBinding.inflate(inflater, parent, false)
        return ContactHolder(binding )
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun onBindViewHolder(holder: ContactHolder, position: Int) {
        holder.bind(getItem(position))
    }
    inner class ContactHolder(private val binding: ContactItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(contact: Contact) = with(binding) {
            textViewUserName.text = contact.userName
            textViewCareer.text= contact.address
            Glide.with(imageViewAvatar).load(contact.avatar).circleCrop()
                .error(R.drawable.ic_launcher_foreground)
                .placeholder(R.drawable.ic_launcher_foreground).into(imageViewAvatar)
            setListener(contact)
        }

        private fun  setListener(contact: Contact){
            binding.basket.setOnClickListener {
                contactActionListener.onContactDelete(contact)
            }


        }
    }

}