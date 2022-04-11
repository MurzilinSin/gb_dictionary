package com.example.gb_coroutinekoin.di

import com.example.gb_coroutinekoin.NAME_LOCAL
import com.example.gb_coroutinekoin.NAME_REMOTE
import com.example.gb_coroutinekoin.model.data.DataModel
import com.example.gb_coroutinekoin.model.datasource.DataSourceLocal
import com.example.gb_coroutinekoin.model.datasource.RetrofitImpl
import com.example.gb_coroutinekoin.model.datasource.RoomDataBaseImplementation
import com.example.gb_coroutinekoin.model.repo.Repository
import com.example.gb_coroutinekoin.model.repo.RepositoryImpl
import com.example.gb_coroutinekoin.view.main.MainInteractor
import com.example.gb_coroutinekoin.viewmodels.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module

val viewModel = module {
    viewModel {
        MainViewModel(get())
    }
}
val dataSourceModule = module {
    single<Repository<List<DataModel>>>(named(NAME_REMOTE)) {
        RepositoryImpl(RetrofitImpl())
    }
    single<Repository<List<DataModel>>>(named(NAME_LOCAL)) {
        RepositoryImpl(RoomDataBaseImplementation())
    }
}

val interactorModule = module {
    single { MainInteractor(get(named(NAME_REMOTE)),get(named(NAME_LOCAL))) }
}

fun initKoin(): KoinApplication {
    val koinApplication = startKoin {
        modules(
            viewModel,
            dataSourceModule,
            interactorModule
        )
    }
    return koinApplication
}