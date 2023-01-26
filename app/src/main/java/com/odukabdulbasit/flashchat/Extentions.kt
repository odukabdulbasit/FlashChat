package com.odukabdulbasit.flashchat

import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun TextView.typeWrite(lifecycleOwner: LifecycleOwner, text: String, intervalMs: Long) {
    this@typeWrite.text = ""
    lifecycleOwner.lifecycleScope.launch {
        repeat(text.length) {
            delay(intervalMs)
            this@typeWrite.text = text.take(it + 1)
        }
    }
}