package com.fiskus.nbaitemsstore.screens.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    //ui data
    private val _emailFormState = MutableLiveData<Boolean>()
    val emailFormState: LiveData<Boolean>
        get() = _emailFormState

    private val _passwordFormState = MutableLiveData<Boolean>()
    val passwordFormState: LiveData<Boolean>
        get() = _passwordFormState

    private val _loginResult = MutableLiveData<Boolean>()
    val loginResult: LiveData<Boolean>
        get() = _loginResult

    //set login result to false
    init {
        _loginResult.value = false
    }

    //register- same as login
    fun register(loginFormData: LoginFormData) = login(loginFormData)

    //login to app
    fun login(loginFormData: LoginFormData) {
        //check if form data is valid
        if (isFormDataValid(loginFormData)) {
            //if so login to app
            _loginResult.value = true
        }
    }

    //check if form data is valid
    private fun isFormDataValid(loginFormData: LoginFormData): Boolean {
        //update current data
        emailFormDataChanged(loginFormData.email)
        passwordFormDataChanged(loginFormData.password)

        //return form data validate
        return _emailFormState.value == true && _passwordFormState.value == true
    }

    //called when the email form data is changed
    fun emailFormDataChanged(email: String) {
        _emailFormState.value = isEmailValid(email)
    }

    //called when the password form data is changed
    fun passwordFormDataChanged(password: String) {
        _passwordFormState.value = isPasswordValid(password)
    }

    //check if email is valid
    private fun isEmailValid(email: String) = Patterns.EMAIL_ADDRESS.matcher(email).matches()

    //check if password is valid
    private fun isPasswordValid(password: String) = password.length > 5

}