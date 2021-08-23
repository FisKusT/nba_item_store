package com.fiskus.nbaitemsstore.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fiskus.nbaitemsstore.model.StoreItem
import java.lang.Exception

class MainViewModel: ViewModel() {

    //items list
    private val _itemsList = MutableLiveData<ArrayList<StoreItem>>()
    val itemsList: LiveData<ArrayList<StoreItem>>
        get() = _itemsList

    //init list
    init {
        _itemsList.value = arrayListOf()
    }

    //Items list actions
    //add item to list
    fun addStoreItem(item: StoreItem) {
        //if name is empty throw an exception
        if (item.name.isEmpty()) {
            throw Exception()
        }
        _itemsList.value?.add(item)
    }
}