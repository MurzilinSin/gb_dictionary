package com.example.gb_coroutinekoin.model.datasource

import com.example.gb_coroutinekoin.model.data.DataModel
import com.example.gb_coroutinekoin.model.datasource.DataSource
import io.reactivex.Observable

class RoomDataBaseImplementation : DataSource<List<DataModel>> {

    override fun getData(word: String): Observable<List<DataModel>> {
        TODO("Not yet implemented")
    }

}
