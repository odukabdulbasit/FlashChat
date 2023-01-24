package com.odukabdulbasit.flashchat.firebase

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class FirebaseService {
    // Initialize Firebase Auth

    private var auth: FirebaseAuth = Firebase.auth

    fun signUpNewUser(email: String, password: String){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("Status", "createUserWithEmail:success")
                    /*val user = auth.currentUser
                    updateUI(user)*/
                } else {
                    // If sign in fails, display a message to the user.
                    /*Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)*/
                }
            }
    }


    fun signInUser(email: String, password: String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("Status", "signInWithEmail:success")
                    /*val user = auth.currentUser
                    updateUI(user)*/
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("Status", "signInWithEmail:failure", task.exception)
                    /*Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)*/
                }
            }
    }
}