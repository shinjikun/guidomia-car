package com.leoilagan.guidomia.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leoilagan.guidomia.data.local.CarInfo
import com.leoilagan.guidomia.data.repositories.CarRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val repo: CarRepository
) : ViewModel() {


    private val _carList = MutableLiveData<List<CarInfo>>()

    val carList: LiveData<List<CarInfo>>
        get() = _carList

    init {
        getData()
    }

    fun getData() {
        viewModelScope.launch {
        var list =    repo.getDataFromDB()
            if(list.isEmpty()){
               list = repo.getJsonData()
                repo.storedLocally(list)
            }
            _carList.value = list
        }

    }

    fun searchForModel(model : String?) {
        model?.let {

            if(model.isEmpty()) {
                getData()
            }else {
                viewModelScope.launch {
                    _carList.value =   repo.queryModel(model)
                }
            }


        }
    }

    fun searchForMaker(maker :String?) {
        maker?.let {
            if(maker.isEmpty()){
                getData()
            }else {
                viewModelScope.launch {
                    _carList.value =  repo.queryMaker(maker)
                }
            }


        }
    }



}
