package com.example.masalaboxtest.util

data class JsonNameData(
    val page1: List<NameData>,
    val page2up: List<SmallImageData>,
    val page2middle: List<MediumImageData>,
    val page2down: List<ItemDetailData>
)

data class NameData(
    val name: String,
    val type: String,
)

data class SmallImageData(
    val name: String,
    val smallImage: String?,
)


data class MediumImageData(
    val name: String,
    val middleImage: String?,
)

data class ItemDetailData(
    val name: String,
    val smallImage: String?,
    val middleImage: String?,
    val textOne: String,
    val textTwo: String,
)
