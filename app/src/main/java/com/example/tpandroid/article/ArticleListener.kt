package com.example.tpandroid.article

class ArticleListener(var onRequestView : (article: Article) -> Unit,
                      var onRequestEdit : (article: Article) -> Unit,
                      var onRequestDelete : (article: Article) -> Unit) {
}