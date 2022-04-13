package com.example.gb_coroutinekoin.di

import android.app.Application
import androidx.room.Room
import com.example.gb_coroutinekoin.NAME_LOCAL
import com.example.gb_coroutinekoin.NAME_REMOTE
import com.example.gb_coroutinekoin.R
import com.example.gb_coroutinekoin.model.data.DataModel
import com.example.gb_coroutinekoin.model.datasource.RetrofitImpl
import com.example.gb_coroutinekoin.model.datasource.RoomDataBaseImplementation
import com.example.gb_coroutinekoin.model.datasource.db.DataBase
import com.example.gb_coroutinekoin.model.datasource.db.HistoryDao
import com.example.gb_coroutinekoin.model.repo.Repository
import com.example.gb_coroutinekoin.model.repo.RepositoryImpl
import com.example.gb_coroutinekoin.model.repo.RepositoryImplLocal
import com.example.gb_coroutinekoin.model.repo.RepositoryLocal
import com.example.gb_coroutinekoin.view.history.HistoryInteractor
import com.example.gb_coroutinekoin.viewmodels.HistoryViewModel
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
    single<Repository<List<DataModel>>>{
        RepositoryImpl(RetrofitImpl())
    }
    single<RepositoryLocal<List<DataModel>>> {
        RepositoryImplLocal(RoomDataBaseImplementation(get()))
    }
}

val interactorModule = module {
    single { MainInteractor(get(), get()) }
}

fun dataBaseModule(app: Application) = module {
    fun createDB(app: Application): DataBase {
        return Room.databaseBuilder(
            app, DataBase::class.java, app.getString(R.string.app_name) + "DataBase"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    fun createDataDao(dataBase: DataBase): HistoryDao {
        return dataBase.historyDao
    }
    single { createDB(app) }
    single { createDataDao(get()) }
}

val historyScreen = module {
    factory { HistoryViewModel(get()) }
    factory { HistoryInteractor(get(), get()) }
}


fun initKoin(app: Application): KoinApplication {
    val koinApplication = startKoin {
        modules(

            dataSourceModule,
            interactorModule,
            dataBaseModule(app),
            historyScreen,
            viewModel
        )
    }
    return koinApplication
}