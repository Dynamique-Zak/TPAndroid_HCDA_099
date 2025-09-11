package com.example.tpandroid.api

data class ApiResponse<T>(var code: String, var message : String, var data : T?) {
}