package com.example.tpandroid.article

import android.app.Application
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tpandroid.R
import com.example.tpandroid.ui.theme.EniButton
import com.example.tpandroid.ui.theme.EniTextField
import com.example.tpandroid.ui.theme.TemplatePage
import com.example.tpandroid.ui.theme.TitlePage
import com.example.tpandroid.ui.theme.WrapPadding

@Composable
fun ArticleFormPage(viewModel: ArticleViewModel) {

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
            TitlePage(stringResource(R.string.article_form_title))
            Spacer(modifier = Modifier.height(40.dp))
            WrapPadding {
                EniTextField(
                    value = articleState.title,
                    onValueChange = { value -> viewModel.article.value = viewModel.article.value.copy(title = value) },
                    hintText = stringResource(R.string.article_field_hint_title)
                )
            }
            WrapPadding {
                EniTextField(
                    value = articleState.desc,
                    onValueChange = { value -> viewModel.article.value = viewModel.article.value.copy(desc = value) },
                    hintText = stringResource(R.string.article_field_hint_desc)
                )
            }
            WrapPadding {
                EniTextField(
                    value = articleState.author,
                    onValueChange = { value -> viewModel.article.value = viewModel.article.value.copy(author = value) },
                    hintText = stringResource(R.string.article_field_hint_author)
                )
            }
            WrapPadding {
                EniButton(label = stringResource(R.string.btn_save)) {
                    viewModel.saveArticle()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArticleFormPagePreview() {
    ArticleFormPage(ArticleViewModel(Application()))
}