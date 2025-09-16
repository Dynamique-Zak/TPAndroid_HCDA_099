package com.example.tpandroid.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tpandroid.common.AppAlertHelpers
import com.example.tpandroid.common.AppProgressHelpers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

class ArticleViewModel : ViewModel() {

    var articles = MutableStateFlow<List<Article>>(mutableListOf())

    var article = MutableStateFlow<Article>(Article("4",
        "Crevette nutella", desc = "Meilleur plat",
        author = "Olivia",
        imgPath = "uneimage-saumon-nutella.jpg"
        ))
    fun reloadArticleDetail(id: String){
        // Affiche un ecran de chargement avant un appel async
        AppProgressHelpers.get().show("Chargement de l'article")

        viewModelScope.launch {

            // Fake wait 1 sec
            delay(duration = 1.seconds)

            val apiResponse = ArticleService.ArticleApi.articleService.getArticleById(id)
            article.value = apiResponse.data!!

            AppProgressHelpers.get().close()

            // Afficher le message du back
            AppAlertHelpers.get().show(apiResponse.message)
        }
    }

    fun reloadArticles(){

        // Affiche un ecran de chargement avant un appel async
        AppProgressHelpers.get().show("Chargement des articles")

        viewModelScope.launch {
            
            // Fake wait 2 sec
            delay(duration = 2.seconds)

            val apiResponse = ArticleService.ArticleApi.articleService.getArticles()
            articles.value = apiResponse.data!!

            // Fermer ecran de chargement à la fin de l'appel async
            AppProgressHelpers.get().close()

            // Afficher le message du back
            AppAlertHelpers.get().show(apiResponse.message)
        }

    }
}