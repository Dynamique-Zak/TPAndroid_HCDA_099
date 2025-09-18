package com.example.tpandroid.auth.login

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.example.tpandroid.R
import com.example.tpandroid.auth.AuthContext
import com.example.tpandroid.auth.AuthService
import com.example.tpandroid.auth.LoginRequest
import com.example.tpandroid.auth.ResetPasswordRequest
import com.example.tpandroid.common.ENIViewModel
import com.example.tpandroid.common.commonCallApi

data class AuthViewModel(
    val _application: Application,
    var email: String = "",
    var password: String = "",
    var resetPasswordRequest: ResetPasswordRequest = ResetPasswordRequest()
) : ENIViewModel(_application) {

    fun callLoginApi(onLoginSuccess: () -> Unit = {}) {

        commonCallApi<String>(
            getString(R.string.auth_login_progress_msg),
            viewModelScope, doAction = {

                // DTO pour request body a partir des valeurs saisies
                val loginRequest = LoginRequest(email, password)
                val apiResponse = AuthService.AuthApi.authService.login(loginRequest)

                // Stocker le token
                if (apiResponse.code.equals("200")) {
                    AuthContext.Companion.get().setAuthToken(apiResponse.data!!);
                }

                apiResponse
            }, onClose = { apiResponse ->
                // Si Code success alors ouvrir la page list article
                if (apiResponse.code.equals("200")) {
                    onLoginSuccess()
                }
            })
    }

    fun callResetPasswordApi() {

        commonCallApi<String>(
            getString(R.string.auth_loading_send_mail_msg),
            viewModelScope, doAction = {
                val apiResponse =
                    AuthService.AuthApi.authService.resetPassword(resetPasswordRequest)
                apiResponse
            })
    }

}