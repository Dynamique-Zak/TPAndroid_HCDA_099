package com.example.tpandroid.article

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.tpandroid.R
import com.example.tpandroid.ui.theme.ArticleCard
import com.example.tpandroid.ui.theme.EniButton
import com.example.tpandroid.ui.theme.TemplatePage
import com.example.tpandroid.ui.theme.TitlePage
import com.example.tpandroid.ui.theme.WrapPadding

@Composable
fun ListArticlePage(viewModel: ArticleViewModel) {
    TemplatePage(backgroundId = R.drawable.mobile_bg_02) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(40.dp)
                .padding(top = 10.dp)
        ) {
            Spacer(modifier = Modifier.height(140.dp))
            TitlePage(stringResource(R.string.articles_title))
            Spacer(modifier = Modifier.height(40.dp))
            EniButton(
                label = stringResource(R.string.articles_refresh), onClick = {
                    viewModel.reloadArticles()
                })
            ArticleListView(viewModel)
        }
    }
}

@Composable
fun ArticleListView(viewModel: ArticleViewModel) {
    val articles by viewModel.articles.collectAsState()

    LazyColumn {
        items(articles) { article ->
            WrapPadding {
                ArticleCard(article, viewModel.articleListener!!)
            }
        }
    }
}
