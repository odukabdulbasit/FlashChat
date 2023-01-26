package com.odukabdulbasit.flashchat.chat

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.odukabdulbasit.flashchat.R
import com.odukabdulbasit.flashchat.models.MessageItem

class MessagesAdapter() :
    RecyclerView.Adapter<MessagesAdapter.ViewHolder>() {

    private var messages = ArrayList<MessageItem>()

    @SuppressLint("NotifyDataSetChanged")
    fun bind(messages: ArrayList<MessageItem>){
        this.messages = messages
        notifyDataSetChanged()
    }
    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val mesageText: TextView
        val mesageSender: TextView

        init {
            // Define click listener for the ViewHolder's View
            mesageText = view.findViewById(R.id.messageTextView)
            mesageSender = view.findViewById(R.id.senderTextView)
        }

        fun bind(message : MessageItem){
            mesageText.text = message.text
            mesageSender.text = message.sender
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = if (viewType == 0){
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.chat_item_my_message, viewGroup, false)
        }else{
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.chat_item_user_message, viewGroup, false)
        }

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.bind(messages[position])
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = messages.size

    override fun getItemViewType(position: Int): Int {
        return if(messages[position].isMe){
            0
        }else{
            1
        }
    }

}
