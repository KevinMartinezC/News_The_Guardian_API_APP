package com.example.news

import android.app.Application
import androidx.room.Room
import com.example.news.components.search.viewmodel.SearchViewModel
import com.example.news.data.local.NewsDatabase
import com.example.news.data.network.GuardianApiService
import com.example.news.data.network.GuardianApiServiceImpl
import com.example.news.data.network.HttpClientProvider
import com.example.news.data.repository.GuardianRepository
import com.example.news.data.repository.GuardianRepositoryImpl
import com.example.news.data.repository.favorite.FavoriteArticlesRepository
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
    single { FavoriteArticlesRepository(get()) }
    viewModel { SearchViewModel(get(), get(), get()) }
}
