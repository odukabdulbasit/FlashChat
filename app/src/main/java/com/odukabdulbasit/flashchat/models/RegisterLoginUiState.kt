package com.odukabdulbasit.flashchat.models

sealed class RegisterLoginUiState {
    object SuccessfullyCreatedNewUser : RegisterLoginUiState()
    class  FailedCreateNewUser(val errorMessage : String) : RegisterLoginUiState()
    class  FailedSignIn(val errorMessage : String) : RegisterLoginUiState()
    object SuccessfullySignIn : RegisterLoginUiState()
    object WrongCredentials : RegisterLoginUiState()
}