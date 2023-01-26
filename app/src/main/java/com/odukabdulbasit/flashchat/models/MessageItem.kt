package com.odukabdulbasit.flashchat.models

import java.util.*

data class MessageItem(
    val date : String,
    val sender : String,
    val text : String,
    var isMe : Boolean
)
