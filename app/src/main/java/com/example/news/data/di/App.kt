package com.example.news.data.di

import android.app.Application
import androidx.room.Room
import com.example.news.data.di.App.Companion.DATABASE_NAME
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
import com.example.news.components.search.model.datastore.DataStoreProvider
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
    companion object{
        const val DATABASE_NAME = "news_database"
    }
}

val appModule = module {//mOVE IT TO ANOTHER FILE

    single { HttpClientProvider().createClient() }
    single<GuardianApiService> { GuardianApiServiceImpl(get()) }
    single<GuardianRepository> { GuardianRepositoryImpl(get()) }
    single { DataStoreProvider(androidContext()) }
    single {
        Room.databaseBuilder(
            androidContext(),
            NewsDatabase::class.java,
            DATABASE_NAME
        ).build()
    }
    single { get<NewsDatabase>().favoriteArticleDao() }
    single<FavoriteArticlesRepository> { FavoriteArticlesRepositoryImpl(get()) }
    viewModel { SearchViewModel(get(), get()) }
    viewModel { FavoritesViewModel(get()) }
}

