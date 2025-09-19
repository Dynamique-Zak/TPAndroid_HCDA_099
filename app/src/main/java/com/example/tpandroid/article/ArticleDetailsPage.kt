package com.example.tpandroid.article

import android.app.Application
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.tpandroid.R
import com.example.tpandroid.ui.theme.TemplatePage
import com.example.tpandroid.ui.theme.TitlePage

@Composable
fun ArticleDetailsPage(viewModel: ArticleViewModel) {

    val articleState by viewModel.article.collectAsState()

    TemplatePage(backgroundId = R.drawable.mobile_bg_02) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(40.dp)
                .padding(top = 10.dp)
        ) {
            Spacer(modifier = Modifier.height(140.dp))
            TitlePage(stringResource(R.string.article_details_title))
            Spacer(modifier = Modifier.height(40.dp))
            AsyncImage(
                model = articleState.imgPath,
                contentDescription = "",
                placeholder = painterResource(R.drawable.article_placeholder),
                modifier = Modifier.width(164.dp)
            )
            Text(articleState.title, fontWeight = FontWeight.Bold)
            Text(articleState.desc)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArticleDetailsPagePreview() {
    ArticleDetailsPage(ArticleViewModel(Application()))
}