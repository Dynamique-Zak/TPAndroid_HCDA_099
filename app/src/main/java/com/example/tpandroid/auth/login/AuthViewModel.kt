package com.example.tpandroid.auth.login

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.example.tpandroid.R
import com.example.tpandroid.auth.AuthContext
import com.example.tpandroid.auth.AuthService
import com.example.tpandroid.auth.LoginRequest
import com.example.tpandroid.auth.ResetPasswordRequest
import com.example.tpandroid.common.AppAlertHelpers
import com.example.tpandroid.common.AppContextHelper.Companion.debugLoading
import com.example.tpandroid.common.AppProgressHelpers
import com.example.tpandroid.common.ENIViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

data class AuthViewModel(val application: Application, var email: String = "", var password: String = "", var resetPasswordRequest: ResetPasswordRequest = ResetPasswordRequest()) : ENIViewModel(application) {

    fun callLoginApi(onLoginSuccess : () -> Unit = {}){

        // Affiche un ecran de chargement avant un appel async
        AppProgressHelpers.get().show(getString(R.string.auth_login_progress_msg))

        viewModelScope.launch {

            // Fake wait 1 sec
            delay(duration = debugLoading)

            // DTO pour request body a partir des valeurs saisies
            val loginRequest = LoginRequest(email, password)

            val apiResponse = AuthService.AuthApi.authService.login(loginRequest)

            // Fermer ecran de chargement à la fin de l'appel async
            AppProgressHelpers.get().close()

            // Stocker le token
            if (apiResponse.code.equals("200")){
                AuthContext.Companion.get().setAuthToken(apiResponse.data!!);
            }

            // Afficher le message du back
            AppAlertHelpers.get().show(apiResponse.message, onClose = {
                // Si Code success alors ouvrir la page list article
                if (apiResponse.code.equals("200")){
                   onLoginSuccess()
                }
            })
        }
    }

    fun callResetPasswordApi(){

        // Affiche un ecran de chargement avant un appel async
        AppProgressHelpers.get().show(getString(R.string.auth_loading_send_mail_msg))

        viewModelScope.launch {

            // Fake wait 1 sec
            delay(duration = debugLoading)

            val apiResponse = AuthService.AuthApi.authService.resetPassword(resetPasswordRequest)

            // Fermer ecran de chargement à la fin de l'appel async
            AppProgressHelpers.get().close()

            // Afficher le message
            AppAlertHelpers.get().show(apiResponse.message)
        }

    }

}