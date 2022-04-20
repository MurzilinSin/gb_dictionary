package com.example.gb_coroutinekoin.di

import android.app.Application
import androidx.room.Room
import com.example.gb_coroutinekoin.R
import com.example.gb_coroutinekoin.view.main.MainInteractor
import com.example.historyscreen.history.HistoryInteractor
import com.example.model.DataModel
import com.example.repo.repo.Repository
import com.example.repo.repo.RepositoryImpl
import com.example.repo.repo.RepositoryImplLocal
import com.example.repo.repo.RepositoryLocal
import com.example.repo.room.RoomDataBaseImplementation
import com.example.repo.room.db.DataBase
import com.example.repo.room.db.HistoryDao
import org.koin.core.KoinApplication
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module

val viewModel = module {
    scope(named<MainActivity>()) {
        scoped { MainInteractor(get(), get()) }
        viewModel { MainViewModel(get()) }
    }
}
val dataSourceModule = module {
    single<Repository<List<DataModel>>> {
        RepositoryImpl(com.example.repo.room.RetrofitImpl())
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
            app,
            DataBase::class.java,
            app.getString(R.string.app_name) + "DataBase"
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
    scope(named<HistoryActivity>()) {
        scoped { HistoryInteractor(get(), get()) }
        viewModel { HistoryViewModel(get()) }
    }
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