package com.example.practico3tindermoviles1fabianeduardogilcespedes

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Persona(
    val nombre: String,
    val imagen
    : List<Int>)
    : Parcelable
