package ru.kirillashikhmin.tauth

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import dagger.Module
import dagger.Provides
import dagger.hilt.EntryPoint
import dagger.hilt.EntryPoints
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import ru.kirillashikhmin.tauth.core.FeatureEntry
import javax.inject.Singleton


@Module
@InstallIn(ActivityComponent::class)
class NavigationModule {

    private lateinit var navigationManager: NavigationManager

    @Provides
    fun provideNavigationManager(
        features: Set<@JvmSuppressWildcards FeatureEntry>
    ): INavigationManager {
        if (!::navigationManager.isInitialized) {
            navigationManager = NavigationManager(features)
        }
        return navigationManager
    }
}


private lateinit var inavigationManager: NavigationEntryPoint

@Composable
fun requireNavigationEntryPoint(): NavigationEntryPoint {
    if (!::inavigationManager.isInitialized) {
        inavigationManager =
            EntryPoints.get(
                LocalContext.current.applicationContext,
                NavigationEntryPoint::class.java,
            )
    }
    return inavigationManager
}

//@EntryPoint
//@InstallIn(SingletonComponent::class)
interface NavigationEntryPoint {
    val navigationManager: INavigationManager
}
