package com.odukabdulbasit.flashchat.register

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.odukabdulbasit.flashchat.R
import com.odukabdulbasit.flashchat.databinding.FragmentRegisterBinding
import com.odukabdulbasit.flashchat.models.RegisterLoginUiState


class RegisterFragment : Fragment() {

    private val viewModel : RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val view = FragmentRegisterBinding.bind(view)


        view.registerButton.setOnClickListener {
            view.progressBar.visibility = View.VISIBLE
            if (!view.emailEditText.text.isNullOrEmpty() && !view.passwordEditText.text.isNullOrEmpty()) {
                viewModel.signUpNewUser(
                    view.emailEditText.text.toString().trim(),
                    view.passwordEditText.text.toString().trim()
                )
            }else{
                Toast.makeText(requireContext(), "Please fill credentials", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.isUserCreatedSuccessfully.observe(this.viewLifecycleOwner, {
            view.progressBar.visibility = View.GONE

            when(it){
                RegisterLoginUiState.SuccessfullyCreatedNewUser -> {

                    viewModel.registeringHandled()
                    findNavController().navigate(R.id.action_registerFragment_to_chatFragment)
                }
                is RegisterLoginUiState.FailedCreateNewUser -> {

                    viewModel.registeringHandled()

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