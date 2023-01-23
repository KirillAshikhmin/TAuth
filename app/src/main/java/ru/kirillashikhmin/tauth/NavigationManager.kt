package ru.kirillashikhmin.tauth

import ru.kirillashikhmin.tauth.core.ComposableFeatureEntry
import ru.kirillashikhmin.tauth.core.FeatureEntry
import javax.inject.Singleton

@Singleton
class NavigationManager(override val features: Set<@JvmSuppressWildcards FeatureEntry>): INavigationManager {
    override val composableFeatures: List<ComposableFeatureEntry>
        get() = features.map { entry -> entry as  ComposableFeatureEntry}
    override val screens: Map<String, ComposableFeatureEntry>
        get() = features.associate { entry -> entry.featureRoute to entry as ComposableFeatureEntry }
}
