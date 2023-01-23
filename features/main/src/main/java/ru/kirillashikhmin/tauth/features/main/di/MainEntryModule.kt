package ru.kirillashikhmin.tauth.features.main.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.multibindings.IntoMap
import dagger.multibindings.IntoSet
import dagger.multibindings.StringKey
import ru.kirillashikhmin.tauth.core.FeatureEntry
import ru.kirillashikhmin.tauth.core.di.FeatureEntryKey
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
interface MainEntryModule {

    @Binds
    @IntoSet
//    @IntoMap
//    @StringKey("Main")
//    @FeatureEntryKey(MainEntryImpl::class)
    fun mainEntry(entry: MainEntryImpl): FeatureEntry
}
