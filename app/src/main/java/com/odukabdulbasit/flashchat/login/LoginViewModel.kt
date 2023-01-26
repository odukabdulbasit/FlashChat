package com.odukabdulbasit.flashchat.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.odukabdulbasit.flashchat.models.RegisterLoginUiState

class LoginViewModel : ViewModel() {

    private var auth: FirebaseAuth = Firebase.auth

    private val _isUserSignInSuccessfully = MutableLiveData<RegisterLoginUiState?>()
    val isUserSignInSuccessfully : LiveData<RegisterLoginUiState?> = _isUserSignInSuccessfully

    fun signInUser(email: String, password: String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("Status", "signInWithEmail:success")
                    _isUserSignInSuccessfully.value = RegisterLoginUiState.SuccessfullySignIn
                    /*val user = auth.currentUser
                    updateUI(user)*/
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("Status", "signInWithEmail:failure", task.exception)
                    _isUserSignInSuccessfully.value = RegisterLoginUiState.FailedSignIn("Check your credentials and please try again!")

                    /*Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)*/
                }
            }
    }


    fun signInHandled(){
        _isUserSignInSuccessfully.value = null
    }
}