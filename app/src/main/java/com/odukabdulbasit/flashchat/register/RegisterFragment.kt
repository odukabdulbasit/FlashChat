package com.odukabdulbasit.flashchat.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.odukabdulbasit.flashchat.R
import com.odukabdulbasit.flashchat.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment() {

    private val viewModel = RegisterViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val view = FragmentRegisterBinding.bind(view)

        val email = view.emailEditText.text
        view.registerButton.setOnClickListener {
            viewModel.signUpNewUser("a@gmail.com", "123456789")
        }
    }

}