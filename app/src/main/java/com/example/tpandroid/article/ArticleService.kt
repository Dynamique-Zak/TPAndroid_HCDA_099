package com.example.tpandroid.article

import com.example.tpandroid.api.RetrofitTools.Companion.retrofit
import retrofit2.http.GET

interface ArticleService {

    @GET("android-articles.json")
    suspend fun getArticles() : List<Article>

    object ArticleApi {
        val articleService : ArticleService by lazy { retrofit.create(ArticleService::class.java) }
    }
}