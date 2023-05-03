package com.example.news

import android.app.Application
import com.example.news.model.GuardianApiService
import com.example.news.model.GuardianApiServiceImpl
import com.example.news.model.GuardianRepository
import com.example.news.model.GuardianRepositoryImpl
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
    single<GuardianApiService> { GuardianApiServiceImpl() }
    single<GuardianRepository> { GuardianRepositoryImpl(get()) }
}
