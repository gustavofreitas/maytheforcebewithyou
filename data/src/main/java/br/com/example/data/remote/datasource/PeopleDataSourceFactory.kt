package br.com.example.data.remote.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import br.com.example.data.remote.api.StarWarsApi
import br.com.example.domain.entity.People
import io.reactivex.disposables.CompositeDisposable

class PeopleDataSourceFactory(
    private val compositeDisposable: CompositeDisposable,
    private val starWarsApi: StarWarsApi
): DataSource.Factory<Int, People>() {

    val peopleDataSourceLiveData = MutableLiveData<PeopleDataSource>()

    var search: String? = null

    override fun create(): DataSource<Int, People> {
        val peopleDataSource = PeopleDataSource(starWarsApi, compositeDisposable, search)
        peopleDataSourceLiveData.postValue(peopleDataSource)
        return peopleDataSource
    }

    fun doSearch(search: String?) {
        this.search = search
        peopleDataSourceLiveData.value?.invalidate()
    }

    fun clearSearch(){
        search = null
    }

}