package ru.kirillashikhmin.tauth.features.main.api

import ru.kirillashikhmin.tauth.core.ComposableFeatureEntry

abstract class MainEntry : ComposableFeatureEntry {

    final override val featureRoute = "main"

    fun destination() = featureRoute
}
