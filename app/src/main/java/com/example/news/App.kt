package com.example.news

import android.app.Application
import com.example.news.components.search.viewmodel.SearchViewModel
import com.example.news.model.network.GuardianApiService
import com.example.news.model.network.GuardianApiServiceImpl
import com.example.news.model.network.HttpClientProvider
import com.example.news.model.repository.GuardianRepository
import com.example.news.model.repository.GuardianRepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule)
        }
    }
}

val appModule = module {
    single { HttpClientProvider() }
    single<GuardianApiService> { GuardianApiServiceImpl(get<HttpClientProvider>().createClient()) }
    single<GuardianRepository> { GuardianRepositoryImpl(get()) }
    viewModel { SearchViewModel(get()) }
}
