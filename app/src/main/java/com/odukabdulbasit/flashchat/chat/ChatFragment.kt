package com.odukabdulbasit.flashchat.chat

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.odukabdulbasit.flashchat.R
import com.odukabdulbasit.flashchat.databinding.FragmentChatBinding
import com.odukabdulbasit.flashchat.models.MessageItem


class ChatFragment : Fragment() {

    private val viewModel : ChatViewModel by viewModels()
    private lateinit var messagesAdapter : MessagesAdapter
    private var messages = ArrayList<MessageItem>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val view = FragmentChatBinding.bind(view)

        viewModel.getCurrentUser()
        setHasOptionsMenu(true)

        viewModel.getMessages()
        messagesAdapter = MessagesAdapter()
        view.messagesRecyclerView.adapter = messagesAdapter

        view.sendButton.setOnClickListener {
            val messageText = view.messageTextEditText.text.toString()
            if (messageText.isNotEmpty()){
                view.messageTextEditText.text.clear()
                view.messageTextEditText.setText("")
                viewModel.sendMessage(messageText)
            }
        }

        viewModel.messages.observe(viewLifecycleOwner, {
            if (it!!.isNotEmpty()){
                messages = it
                messagesAdapter.bind(messages)
            }
        })

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.signOut -> {
                viewModel.signOut()
                findNavController().navigate(R.id.action_chatFragment_to_loginFragment)
                return true
            }
            else -> {}
        }
        return false
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.sign_out_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }
}