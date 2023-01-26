package com.odukabdulbasit.flashchat.chat

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.odukabdulbasit.flashchat.models.MessageItem
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList

class ChatViewModel : ViewModel() {
    private val fireStore = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()
    lateinit var loggedUser : FirebaseUser

    private val _messages = MutableLiveData<ArrayList<MessageItem>>()
    val messages : LiveData<ArrayList<MessageItem>?> = _messages

    fun getCurrentUser(){
        try {
            val user = auth.currentUser
            if (user != null){
                loggedUser = user
                Log.i("user Email : ", "${user.email}")
            }
        }catch (e : Exception){
            e.printStackTrace()
        }
    }

    fun getMessages(){
        fireStore.collection("messages")
            .addSnapshotListener { snapshot, e ->
                if (e != null) {
                    Log.w(TAG, "Listen failed.", e)
                    return@addSnapshotListener
                }

                if (snapshot != null && snapshot.documents.isNotEmpty()) {
                    Log.d(TAG, "Current data: ${snapshot.documents}")
                    updateUi(snapshot.documents.sortedBy { it["date"].toString() }.reversed())
                } else {
                    Log.d(TAG, "Current data: null")
                }
            }
        /*.get()
        .addOnSuccessListener { result ->
            for (document in result) {
                Log.d(TAG, "${document.id} => ${document.data}")
            }
        }
        .addOnFailureListener { exception ->
            Log.w(TAG, "Error getting documents.", exception)
        }*/
    }

    private fun updateUi(documents: List<DocumentSnapshot>) {
        val updatedMessages = ArrayList<MessageItem>()
        documents.map {
            updatedMessages.add(MessageItem(it.data?.get("sender") as String, it.data?.get("sender") as String,  it.data?.get("text") as String, loggedUser.email == it.data?.get("sender")))
        }

        _messages.value = updatedMessages
    }

    @SuppressLint("NewApi")
    fun sendMessage(messageText : String){


        val simpleDateFormat = SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z")
        val currentDateAndTime: String = simpleDateFormat.format(Date())
        Log.d("Date", "$currentDateAndTime")

        fireStore.collection("messages")
            .add(mapOf("text" to messageText, "sender" to loggedUser.email, "date" to currentDateAndTime))
            .addOnSuccessListener { documentReference ->
                Log.d("Success", "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w("Failure", "Error adding document", e)
            }
    }

    fun signOut() {
        auth.signOut()
    }
}