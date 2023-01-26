package com.odukabdulbasit.flashchat.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.odukabdulbasit.flashchat.models.RegisterLoginUiState

class RegisterViewModel : ViewModel() {

    private var auth: FirebaseAuth = Firebase.auth

    private val _isUserCreatedSuccessfully = MutableLiveData<RegisterLoginUiState?>()
    val isUserCreatedSuccessfully : MutableLiveData<RegisterLoginUiState?> = _isUserCreatedSuccessfully

    fun signUpNewUser(email: String, password: String){
        try {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("Status", "createUserWithEmail:success")

                        _isUserCreatedSuccessfully.value = RegisterLoginUiState.SuccessfullyCreatedNewUser

                    /*val user = auth.currentUser
                        updateUI(user)*/
                    } else {
                        // If sign in fails, display a message to the user.
                        _isUserCreatedSuccessfully.value = RegisterLoginUiState.FailedCreateNewUser("Register Failed. Please try Again")
                        /*Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                        updateUI(null)*/
                    }
                }
        }catch (e : Exception){
            e.printStackTrace()
        }
    }

    fun registeringHandled(){
        _isUserCreatedSuccessfully.value = null
    }
}