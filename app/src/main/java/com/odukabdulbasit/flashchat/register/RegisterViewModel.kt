package com.odukabdulbasit.flashchat.register

import androidx.lifecycle.ViewModel
import com.odukabdulbasit.flashchat.firebase.FirebaseService

class RegisterViewModel : ViewModel() {

    private val firebaseService = FirebaseService()

    fun signUpNewUser(email: String, password: String){
        firebaseService.signUpNewUser(email, password)
    }
}