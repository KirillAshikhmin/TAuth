package ru.kirillashikhmin.tauth

import ru.kirillashikhmin.tauth.core.ComposableFeatureEntry
import ru.kirillashikhmin.tauth.core.FeatureEntry

interface INavigationManager {
    val features: Set<FeatureEntry>
    val composableFeatures: List<ComposableFeatureEntry>
    val screens: Map<String, ComposableFeatureEntry>
}
