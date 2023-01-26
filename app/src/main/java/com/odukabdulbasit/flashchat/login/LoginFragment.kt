package com.odukabdulbasit.flashchat.login

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.odukabdulbasit.flashchat.R
import com.odukabdulbasit.flashchat.databinding.FragmentLoginBinding
import com.odukabdulbasit.flashchat.models.RegisterLoginUiState


class LoginFragment : Fragment() {

    private val viewModel = LoginViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val view = FragmentLoginBinding.bind(view)

        view.signInButton.setOnClickListener {
            view.progressBar.visibility = View.VISIBLE

            if (!view.emailEditText.text.isNullOrEmpty() && !view.passwordEditText.text.isNullOrEmpty()){
                viewModel.signInUser(view.emailEditText.text.toString().trim(), view.passwordEditText.text.toString().trim())
            }else{
                Toast.makeText(requireContext(), "Please fill credentials", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.isUserSignInSuccessfully.observe(this.viewLifecycleOwner, {
            view.progressBar.visibility = View.GONE

            when(it){
                RegisterLoginUiState.SuccessfullySignIn -> {

                    viewModel.signInHandled()
                    findNavController().navigate(R.id.action_loginFragment_to_chatFragment)
                }
                is RegisterLoginUiState.FailedSignIn -> {

                    viewModel.signInHandled()
                    androidx.appcompat.app.AlertDialog.Builder(requireContext())
                        .setTitle("Warning!")
                        .setCancelable(false)
                        .setMessage(it.errorMessage)
                        .setPositiveButton("Try Again", object : DialogInterface.OnClickListener {
                            override fun onClick(dialog: DialogInterface?, which: Int) {
                                dialog?.dismiss()
                            }
                        })
                        .show()
                }
            }
        })

    }

}