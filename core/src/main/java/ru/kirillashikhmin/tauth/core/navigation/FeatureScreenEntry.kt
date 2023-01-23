package ru.kirillashikhmin.tauth.core.navigation

import ru.kirillashikhmin.tauth.core.ComposableFeatureEntry


abstract class FeatureScreenEntry : ComposableFeatureEntry {

    final override val featureRoute = "main"

    fun destination() = featureRoute
}
