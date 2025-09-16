package com.example.tpandroid.article

import com.example.tpandroid.api.ApiResponse
import com.example.tpandroid.api.RetrofitTools.Companion.retrofit
import retrofit2.http.GET
import retrofit2.http.Path

interface ArticleService {

    @GET("articles")
    suspend fun getArticles() : ApiResponse<List<Article>>

    @GET("articles/{id}")
    suspend fun getArticleById(@Path("id") id: String) : ApiResponse<Article>

    object ArticleApi {
        val articleService : ArticleService by lazy { retrofit.create(ArticleService::class.java) }
    }
}