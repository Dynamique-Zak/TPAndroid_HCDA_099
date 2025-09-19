package com.example.tpandroid.article

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.example.tpandroid.R
import com.example.tpandroid.common.ENIViewModel
import com.example.tpandroid.common.commonCallApi
import kotlinx.coroutines.flow.MutableStateFlow

class ArticleViewModel(application: Application) : ENIViewModel(application) {

    var articleListener : ArticleListener? = null

    var articles = MutableStateFlow<List<Article>>(mutableListOf())

    var article = MutableStateFlow<Article>(
        Article(
            null,
            "Crevette nutella", desc = "Meilleur plat",
            author = "Olivia",
            imgPath = "uneimage-saumon-nutella.jpg"
        )
    )

    fun reloadArticleDetail(id: String) {
        commonCallApi<Article>(getString(R.string.article_loading_msg), viewModelScope, doAction = {
            val apiResponse = ArticleService.ArticleApi.articleService.getArticleById(id)
            article.value = apiResponse.data!!

            apiResponse
        })
    }

    fun reloadArticles() {
        commonCallApi<List<Article>>(
            getString(R.string.articles_loading_msg),
            viewModelScope,
            doAction = {
                val apiResponse = ArticleService.ArticleApi.articleService.getArticles()
                articles.value = apiResponse.data!!

                apiResponse // return implicite
            })
    }

    fun saveArticle() {
        commonCallApi<Article>(
            getString(R.string.articles_start_save_msg),
            viewModelScope,
            doAction = {
                val apiResponse =
                    ArticleService.ArticleApi.articleService.saveArticle(article.value)
                article.value = apiResponse.data!!

                apiResponse
            })
    }

    fun deleteArticle(id: String) {

        commonCallApi<Boolean>(
            getString(R.string.articles_delete_loading_msg),
            viewModelScope, doAction = {
                val apiResponse = ArticleService.ArticleApi.articleService.deleteArticle(id)
                apiResponse
            }, onClose = {
                // Reload les articles
                reloadArticles()
            })
    }
}