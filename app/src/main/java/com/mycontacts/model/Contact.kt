package com.mycontacts.model

//todo not sure whether it is a good idea to keep the avatar as string
data class Contact(
    val id: Long,
    val avatar: String,
    val userName: String,
    val address: String
)
