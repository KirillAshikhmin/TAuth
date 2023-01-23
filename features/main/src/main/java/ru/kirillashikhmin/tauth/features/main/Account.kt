package ru.kirillashikhmin.tauth.features.main

data class Account(
    var id: Long,
    val service: String,
    val account: String,
    val secret: String,
)
