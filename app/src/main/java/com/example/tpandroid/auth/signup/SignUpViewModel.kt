package com.example.tpandroid.auth.signup

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.example.tpandroid.R
import com.example.tpandroid.auth.AuthService
import com.example.tpandroid.auth.SignUpRequest
import com.example.tpandroid.auth.User
import com.example.tpandroid.common.ENIViewModel
import com.example.tpandroid.common.commonCallApi

data class SignUpViewModel(val _application: Application, var signUpRequest: SignUpRequest = SignUpRequest()) : ENIViewModel(_application) {

    fun callSignUpApi(onSignUpSuccess : () -> Unit = {}){

        commonCallApi<User>(
            getString(R.string.auth_loading_try_signup_msg),
            viewModelScope,
            doAction = {
                val apiResponse = AuthService.AuthApi.authService.signup(signUpRequest)
                apiResponse
            }, onClose = { apiResponse ->
                // Si Code success alors ouvrir la page list Login
                if (apiResponse.code.equals("200")){
                    onSignUpSuccess()
                }
            })

    }
}