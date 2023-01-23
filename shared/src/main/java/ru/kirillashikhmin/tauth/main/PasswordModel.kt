package ru.kirillashikhmin.tauth.main

data class PasswordModel(val id : Long, val service : String, val account : String, val secret : String)
