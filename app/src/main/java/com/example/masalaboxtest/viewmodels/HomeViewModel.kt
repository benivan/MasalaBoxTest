package com.example.masalaboxtest.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.masalaboxtest.repository.RoomRepository
import com.example.masalaboxtest.util.ItemRow
import com.example.masalaboxtest.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: RoomRepository) : ViewModel() {

    private var _namedData: MutableSharedFlow<Resource<List<ItemRow>>> = MutableSharedFlow(1)
    val namedData = _namedData.asSharedFlow()

    fun getNamedData() {
        viewModelScope.launch {
            try {
                _namedData.emit(Resource.Loading())
                val data = repository.getAllNamedData()
                _namedData.emit(Resource.Success(data))

            } catch (e: Exception) {
                _namedData.emit(Resource.Failure(e))
            }
        }
    }
}