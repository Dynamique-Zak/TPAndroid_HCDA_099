package com.example.tpandroid.article

import com.example.tpandroid.api.ApiResponse
import com.example.tpandroid.api.RetrofitTools.Companion.retrofit
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ArticleService {

    @GET("articles")
    suspend fun getArticles() : ApiResponse<List<Article>>

    @GET("articles/{id}")
    suspend fun getArticleById(@Path("id") id: String) : ApiResponse<Article>

    @POST("articles/save")
    suspend fun saveArticle(@Body article: Article) : ApiResponse<Article>

    @DELETE("articles/{id}")
    suspend fun deleteArticle(@Path("id") id: String) : ApiResponse<Boolean>

    object ArticleApi {
        val articleService : ArticleService by lazy { retrofit.create(ArticleService::class.java) }
    }
}