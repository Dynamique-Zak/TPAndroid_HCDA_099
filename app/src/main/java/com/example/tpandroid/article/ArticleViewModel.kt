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
    var isLoading = MutableStateFlow<Boolean>(false)

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