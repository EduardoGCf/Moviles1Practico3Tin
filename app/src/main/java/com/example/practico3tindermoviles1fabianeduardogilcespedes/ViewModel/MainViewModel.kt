package com.example.practico3tindermoviles1fabianeduardogilcespedes.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.practico3tindermoviles1fabianeduardogilcespedes.R
import com.example.practico3tindermoviles1fabianeduardogilcespedes.Persona

class MainViewModel : ViewModel() {

    private val _persona = MutableLiveData<Persona?>()
    val persona: LiveData<Persona?> = _persona

    private val personas = mutableListOf(
        Persona("Franklin Clinton ", listOf(R.drawable.persona1_1, R.drawable.persona1_2, R.drawable.persona1_3)),
        Persona("Michael De Santa", listOf(R.drawable.persona2_1, R.drawable.persona2_2, R.drawable.persona2_3)),
        Persona("Trevor Philips", listOf(R.drawable.persona3_1, R.drawable.persona3_2, R.drawable.persona3_3)),
        Persona("Lamar Davis", listOf(R.drawable.persona4_1, R.drawable.persona4_2, R.drawable.persona4_3, R.drawable.persona4_4)),
    )

    private val likedPeople = mutableListOf<Persona>()

    init {
        loadNextPerson()
    }

    fun likePerson() {
        _persona.value?.let { person ->
            likedPeople.add(person)
            personas.remove(person)
        }
        loadNextPerson()
    }

    fun dislikePerson() {
        _persona.value?.let { person ->
            personas.remove(person)
        }
        loadNextPerson()
    }

    private fun loadNextPerson() {
        if (personas.isNotEmpty()) {
            _persona.value = personas.firstOrNull()
        } else {
            _persona.value = null
        }
    }

    fun getLikedPeople(): List<Persona> = likedPeople
}
