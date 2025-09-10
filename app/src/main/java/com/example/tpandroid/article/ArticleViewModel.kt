package com.example.tpandroid.article

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class ArticleViewModel : ViewModel() {

    var articles = MutableStateFlow<List<Article>>(mutableListOf(
        Article(title = "Skarnado", description = "Film de requin"),
        Article(title = "Teletubies", description = "Soleil et l'aspirateur chelou"),
        Article(title = "Crevette Nutella", description = "Faut goûter avant de juger")
    ))

    fun addMockArticle(){
        articles.value += Article("Article test add", "Aucune description")
    }
}