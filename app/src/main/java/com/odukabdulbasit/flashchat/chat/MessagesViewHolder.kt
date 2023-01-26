package com.odukabdulbasit.flashchat.chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.odukabdulbasit.flashchat.R
import com.odukabdulbasit.flashchat.models.MessageItem


class MessagesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    constructor(parent: ViewGroup)
            : this(LayoutInflater.from(parent.context).inflate(R.layout.chat_item_my_message, parent, false))

    /*override fun bindTo(item: MessageItem) {
        with(itemView) {
            llText.show()
            tvBotMessage.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(item.message, HtmlCompat.FROM_HTML_MODE_LEGACY)
            } else {
                Html.fromHtml(item.message)
            }
        }
    }*/
}