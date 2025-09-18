package com.example.tpandroid.auth.signup

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.example.tpandroid.R
import com.example.tpandroid.auth.AuthService
import com.example.tpandroid.auth.SignUpRequest
import com.example.tpandroid.common.AppAlertHelpers
import com.example.tpandroid.common.AppProgressHelpers
import com.example.tpandroid.common.ENIViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

data class SignUpViewModel(val _application: Application, var signUpRequest: SignUpRequest = SignUpRequest()) : ENIViewModel(_application) {

    fun callSignUpApi(onSignUpSuccess : () -> Unit = {}){

        // Affiche un ecran de chargement avant un appel async
        AppProgressHelpers.get().show(getString(R.string.auth_loading_try_signup_msg))

        viewModelScope.launch {

            // Fake wait 1 sec
            delay(duration = 1.seconds)

            val apiResponse = AuthService.AuthApi.authService.signup(signUpRequest)

            // Fermer ecran de chargement à la fin de l'appel async
            AppProgressHelpers.get().close()

            // Afficher le message du back
            AppAlertHelpers.get().show(apiResponse.message, onClose = {
                // Si Code success alors ouvrir la page list Login
                if (apiResponse.code.equals("200")){
                    onSignUpSuccess()
                }
            })
        }

    }
}