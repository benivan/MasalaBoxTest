package com.example.masalaboxtest.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.masalaboxtest.data.ItemDetail
import com.example.masalaboxtest.data.MediumImage
import com.example.masalaboxtest.data.SmallImage
import com.example.masalaboxtest.repository.RoomRepository
import com.example.masalaboxtest.util.ItemRow
import com.example.masalaboxtest.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiscoverVIewModel @Inject constructor(
    private val roomRepository: RoomRepository
) : ViewModel() {

    private var _topData: MutableSharedFlow<Resource<List<SmallImage>>> = MutableSharedFlow(1)
    val topData = _topData.asSharedFlow()

    private var _middleData: MutableSharedFlow<Resource<List<MediumImage>>> = MutableSharedFlow(1)
    val middleData = _middleData.asSharedFlow()

    private var _itemDetailData: MutableSharedFlow<Resource<List<ItemDetail>>> = MutableSharedFlow(1)
    val itemDetailData = _itemDetailData.asSharedFlow()

    fun getTopData(){
        viewModelScope.launch {
            try {
                _topData.emit(Resource.Loading())
                val topData = roomRepository.getTopData()
                _topData.emit(Resource.Success(topData))
            } catch (e: Exception) {
                _topData.emit(Resource.Failure(e))
            }
        }
    }

    fun getMiddleData(){
        viewModelScope.launch {
            try {
                _middleData.emit(Resource.Loading())
                val middleData = roomRepository.getMiddleData()
                _middleData.emit(Resource.Success(middleData))

            }catch (e:Exception){
                _middleData.emit(Resource.Failure(e))
            }
        }
    }

    fun getItemDetail(){
        viewModelScope.launch {
            try {
                _itemDetailData.emit(Resource.Loading())
                val itemDetailData = roomRepository.getItemDetail()
                _itemDetailData.emit(Resource.Success(itemDetailData))
            }catch (e:Exception){
                _itemDetailData.emit(Resource.Failure(e))
            }
        }
    }
}