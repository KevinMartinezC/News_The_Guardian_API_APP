package com.example.news

import android.app.Application
import androidx.room.Room
import com.example.news.components.favorite.viewmodel.FavoritesViewModel
import com.example.news.components.search.viewmodel.SearchViewModel
import com.example.news.components.favorite.model.local.NewsDatabase
import com.example.news.data.network.GuardianApiService
import com.example.news.data.network.GuardianApiServiceImpl
import com.example.news.data.network.HttpClientProvider
import com.example.news.components.search.model.repository.GuardianRepository
import com.example.news.components.search.model.repository.GuardianRepositoryImpl
import com.example.news.components.favorite.model.repository.FavoriteArticlesRepository
import com.example.news.components.favorite.model.repository.FavoriteArticlesRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(appModule)
        }
    }
}

val appModule = module {

    single { HttpClientProvider().createClient() }
    single<GuardianApiService> { GuardianApiServiceImpl(get()) }
    single<GuardianRepository> { GuardianRepositoryImpl(get()) }
    single { DataStoreProvider(androidContext()) }
    single {
        Room.databaseBuilder(
            androidContext(),
            NewsDatabase::class.java,
            "news_database"
        ).build()
    }
    single { get<NewsDatabase>().favoriteArticleDao() }
    single<FavoriteArticlesRepository> { FavoriteArticlesRepositoryImpl(get()) }
    viewModel { SearchViewModel(get(), get()) }
    viewModel { FavoritesViewModel(get()) }
}

