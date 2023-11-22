package com.example.massive.model

data class UnitModel(
    val nama : String,
    val deskripsi : String,
    val panduan : String,
    val stage : List<StageModel>
)
