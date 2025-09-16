package com.example.tpandroid.auth.login

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tpandroid.article.ListArticleActivity
import com.example.tpandroid.auth.AuthContext
import com.example.tpandroid.auth.AuthService
import com.example.tpandroid.auth.LoginRequest
import com.example.tpandroid.auth.ResetPasswordPage
import com.example.tpandroid.auth.ResetPasswordRequest
import com.example.tpandroid.common.AppAlertHelpers
import com.example.tpandroid.common.AppContextHelper
import com.example.tpandroid.common.AppProgressHelpers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

data class AuthViewModel(var email: String = "", var password: String = "", var resetPasswordRequest: ResetPasswordRequest = ResetPasswordRequest()) : ViewModel() {

    fun callLoginApi(context: Context){

        // Affiche un ecran de chargement avant un appel async
        AppProgressHelpers.get().show("Tentative de connexion")

        viewModelScope.launch {

            // Fake wait 1 sec
            delay(duration = 1.seconds)

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
                    AppContextHelper.openActivity(context, ListArticleActivity::class)
                }
            })
        }

    }

    fun callResetPasswordApi(){

        // Affiche un ecran de chargement avant un appel async
        AppProgressHelpers.get().show("Envoie du mail...")

        viewModelScope.launch {

            // Fake wait 1 sec
            delay(duration = 1.seconds)

            val apiResponse = AuthService.AuthApi.authService.resetPassword(resetPasswordRequest)

            // Fermer ecran de chargement à la fin de l'appel async
            AppProgressHelpers.get().close()

            // Afficher le message
            AppAlertHelpers.get().show(apiResponse.message)

        }

    }

}