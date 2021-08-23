package com.fiskus.nbaitemsstore.screens.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.fiskus.nbaitemsstore.R
import com.fiskus.nbaitemsstore.databinding.LoginFragmentBinding
import com.google.android.material.textfield.TextInputLayout
import timber.log.Timber

class LoginFragment : Fragment() {

    private lateinit var binding: LoginFragmentBinding

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        Timber.i("onCreateView")

        //init binding
        binding = LoginFragmentBinding.inflate(layoutInflater, container, false)

        //init view model
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        //set view model observers
        //email form state
        viewModel.emailFormState.observe(viewLifecycleOwner, { emailFormCurrentState ->
            //handle email state
            handleInputState(emailFormCurrentState, binding.emailTIL, getString(R.string.invalid_email))
        })

        //password form state
        viewModel.passwordFormState.observe(viewLifecycleOwner, { passwordFormCurrentState ->
            //handle password state
            handleInputState(passwordFormCurrentState, binding.passwordTIL, getString(R.string.invalid_password))
        })

        //login result
        viewModel.loginResult.observe(viewLifecycleOwner, { loginResult ->
            if (loginResult) {
                //navigate to welcome page
                findNavController().navigate(LoginFragmentDirections.actionLoginToWelcomeFragment().actionId)
            }
        })

        //buttons click listeners
        //login btn
        binding.loginBtn.setOnClickListener {
            viewModel.login(getETData())
        }

        //register btn
        binding.registerBtn.setOnClickListener {
            viewModel.register(getETData())
        }

        //edit text focus change listener
        //email et focus changed
        binding.emailET.onFocusChangeListener = View.OnFocusChangeListener{ _ , hasFocus: Boolean ->
            //when out of focus
            if (!hasFocus) {
                viewModel.emailFormDataChanged(getEmailData())
            }
        }

        //password et focus changed
        binding.passwordET.onFocusChangeListener = View.OnFocusChangeListener{ _ , hasFocus: Boolean ->
            //when out of focus
            if (!hasFocus) {
                viewModel.passwordFormDataChanged(getPasswordData())
            }
        }

        return binding.root
    }

    //get edit text data
    private fun getETData() = LoginFormData(email = getEmailData(), password = getPasswordData())

    //get email edit text data
    private fun getEmailData() = binding.emailET.text.toString()

    //get password edit text data
    private fun getPasswordData() = binding.passwordET.text.toString()

    //show error message for edit text
    private fun handleInputState(isValid: Boolean, til: TextInputLayout, errorMsg: String) {
        if (isValid) {
            til.isErrorEnabled = false
        } else {
            til.error = errorMsg
        }
    }
}