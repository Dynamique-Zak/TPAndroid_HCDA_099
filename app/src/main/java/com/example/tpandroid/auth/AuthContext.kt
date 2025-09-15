package com.example.tpandroid.auth

import com.example.tpandroid.common.AppAlertHelpers

class AuthContext(var token: String = "") {

    // Singleton
    companion object {
        val instance : AuthContext by lazy { AuthContext() }

        fun get() : AuthContext {
            return instance;
        }
    }

    fun setAuthToken(newToken: String){
        token = newToken

        println("Stocker le token en cache : ${token}")
    }
}