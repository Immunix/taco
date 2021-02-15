package com.candybytes.taco.ui.vm

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.candybytes.taco.db.FoodDao
import timber.log.Timber

class SearchFoodViewModel @ViewModelInject constructor(
    private val foodDao: FoodDao,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val searchQuery = MutableLiveData("")

    // should filter through description
    val info = liveData {
        try {
            val foods = searchQuery.value?.let { foodDao.getAllAsync(it) }
            emit(foods)
        } catch (e: Exception) {
            Timber.e(e)
        }
    }.map { it }


}
