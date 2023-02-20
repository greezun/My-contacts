package com.example.mycontacts.vh
import java.io.Serializable

data class Contact(
    val contactId: Int = 0,
    val avatar: String ="",
    val userName: String="",
    val address: String = ""): Serializable
