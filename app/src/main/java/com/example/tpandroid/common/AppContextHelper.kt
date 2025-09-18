package com.example.tpandroid.common

import android.content.Context
import android.content.Intent
import com.example.tpandroid.api.ApiResponse
import com.example.tpandroid.common.AppContextHelper.Companion.debugLoading
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.reflect.KClass
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

class AppContextHelper {

    // Tout ce qui est à l'intérieur est statique
    companion object {

        val debugLoading : Duration = 1.milliseconds

        /**
         * activityClass: Typage en parametre (ex: MainActivity::class)
         */
        fun openActivity(context: Context, activityClass: KClass<*>){
            val intent = Intent(context, activityClass.java)
            context.startActivity(intent)
        }

        /**
         * Key : le nom du param à envoyer (ex: id)
         * Value : la valeur (ex "1")
         */
        fun openActivityWithString(context: Context, activityClass: KClass<*>, key: String, value: String){
            val intent = Intent(context, activityClass.java)

            intent.putExtra(key, value)

            context.startActivity(intent)
        }

    }
}

fun <T> commonCallApi(loadingMsg : String = "Chargement", coroutineScope : CoroutineScope, doAction: suspend () -> ApiResponse<T>, onClose : (ApiResponse<T>) -> Unit = {}){

    // Affiche un ecran de chargement avant un appel async
    AppProgressHelpers.get().show(loadingMsg)

    coroutineScope.launch {

        // Fake wait 2 sec
        delay(duration = debugLoading)

        val apiResponse = doAction()

        // Fermer ecran de chargement à la fin de l'appel async
        AppProgressHelpers.get().close()

        // Afficher le message du back
        AppAlertHelpers.get().show(apiResponse.message, onClose = { onClose(apiResponse) })
    }
}