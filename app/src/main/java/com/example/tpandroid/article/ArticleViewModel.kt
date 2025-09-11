package com.example.tpandroid.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ArticleViewModel : ViewModel() {

    var articles = MutableStateFlow<List<Article>>(mutableListOf())

    fun reloadArticles(){

        viewModelScope.launch {

            articles.value = ArticleService.ArticleApi.articleService.getArticles()
        }
    }
}