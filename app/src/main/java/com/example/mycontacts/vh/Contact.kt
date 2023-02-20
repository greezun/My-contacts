package com.example.mycontacts.vh
import java.io.Serializable

data class Contact(
    val avatar: String ="",
    val userName: String="",
    val address: String = ""): Serializable
