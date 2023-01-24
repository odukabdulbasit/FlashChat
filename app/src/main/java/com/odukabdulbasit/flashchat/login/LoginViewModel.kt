package com.odukabdulbasit.flashchat.login

import androidx.lifecycle.ViewModel
import com.odukabdulbasit.flashchat.firebase.FirebaseService

class LoginViewModel : ViewModel() {

    private val firebaseService = FirebaseService()

    fun signInUser(email: String, password: String){
        firebaseService.signInUser(email, password)
    }

}