package ru.kirillashikhmin.tauth.core.di

import dagger.MapKey
import ru.kirillashikhmin.tauth.core.FeatureEntry
import kotlin.reflect.KClass

@MapKey
annotation class FeatureEntryKey(val value: KClass<out FeatureEntry>)
