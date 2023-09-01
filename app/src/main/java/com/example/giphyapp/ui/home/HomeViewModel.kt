package com.example.giphyapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.giphyapp.datastorage.NetworkDataStore
import com.example.giphyapp.model.GiphyModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(private val networkDataStore: NetworkDataStore) : ViewModel() {
    val firstGifts = MutableLiveData<ArrayList<GiphyModel>>()
    val gifs = MutableLiveData<ArrayList<GiphyModel>>()

    fun getGifs() {
        viewModelScope.launch(Dispatchers.IO) {
            val responseGifs = networkDataStore.getUserData(DEFAULT_OFFSET)?.let { ArrayList(it) } ?: arrayListOf()
            gifs.postValue(responseGifs)
        }
    }

    fun nextPage(offset: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val responseGifs = networkDataStore.getUserData(offset)?.let { ArrayList(it) } ?: arrayListOf()
            gifs.postValue(responseGifs)
        }
    }

    companion object {
        const val DEFAULT_OFFSET = 0
    }

}