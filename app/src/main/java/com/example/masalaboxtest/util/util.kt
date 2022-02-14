package com.example.masalaboxtest.util

import com.example.masalaboxtest.data.TypeOne
import com.example.masalaboxtest.data.TypeThree
import com.example.masalaboxtest.data.TypeTwo

sealed class ItemRow {
    data class One(val typeOne: TypeOne): ItemRow()
    data class Two(val typeTwo: TypeTwo): ItemRow()
    data class Three(val typeThree: TypeThree): ItemRow()
}