package com.example.tpandroid.article

import android.app.Application
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tpandroid.R
import com.example.tpandroid.common.AppAlertHelpers
import com.example.tpandroid.common.AppContextHelper.Companion.debugLoading
import com.example.tpandroid.common.AppProgressHelpers
import com.example.tpandroid.common.ENIViewModel
import com.example.tpandroid.common.commonCallApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

class ArticleViewModel(application: Application) : ENIViewModel(application) {

    var articles = MutableStateFlow<List<Article>>(mutableListOf())

    var article = MutableStateFlow<Article>(Article(null,
        "Crevette nutella", desc = "Meilleur plat",
        author = "Olivia",
        imgPath = "uneimage-saumon-nutella.jpg"
        ))
    fun reloadArticleDetail(id: String){
        // Affiche un ecran de chargement avant un appel async
        AppProgressHelpers.get().show(getString(R.string.article_loading_msg))

        viewModelScope.launch {

            // Fake wait 1 sec
            delay(duration = debugLoading)

            val apiResponse = ArticleService.ArticleApi.articleService.getArticleById(id)
            article.value = apiResponse.data!!

            AppProgressHelpers.get().close()

            // Afficher le message du back
            AppAlertHelpers.get().show(apiResponse.message)
        }
    }

    fun reloadArticles(){
        commonCallApi<List<Article>>(getString(R.string.articles_loading_msg), viewModelScope, doAction = {
            val apiResponse = ArticleService.ArticleApi.articleService.getArticles()
            articles.value = apiResponse.data!!

            apiResponse // return implicite
        })
    }

    fun saveArticle(){
        // Affiche un ecran de chargement avant un appel async
        AppProgressHelpers.get().show(getString(R.string.articles_start_save_msg))

        viewModelScope.launch {

            // Fake wait 1 sec
            delay(duration = debugLoading)

            val apiResponse = ArticleService.ArticleApi.articleService.saveArticle(article.value)
            article.value = apiResponse.data!!

            AppProgressHelpers.get().close()

            // Afficher le message du back
            AppAlertHelpers.get().show(apiResponse.message)
        }
    }

    fun deleteArticle(id: String){
        // Affiche un ecran de chargement avant un appel async
        AppProgressHelpers.get().show(getString(R.string.articles_delete_loading_msg))

        viewModelScope.launch {

            // Fake wait 1 sec
            delay(duration = debugLoading)

            val apiResponse = ArticleService.ArticleApi.articleService.deleteArticle(id)

            AppProgressHelpers.get().close()

            // Afficher le message du back
            AppAlertHelpers.get().show(apiResponse.message, onClose = {
                // Reload les articles
                reloadArticles()
            })

        }
    }
}