package com.example.tpandroid.article

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.tpandroid.R
import com.example.tpandroid.ui.theme.ArticleCard
import com.example.tpandroid.ui.theme.EniButton
import com.example.tpandroid.ui.theme.EniTextField
import com.example.tpandroid.ui.theme.TemplatePage
import com.example.tpandroid.ui.theme.TitlePage
import com.example.tpandroid.ui.theme.WrapPadding

class ArticleFormActivity : ComponentActivity() {

    lateinit var viewModel: ArticleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        viewModel = ArticleViewModel()

        setContent {
            ArticleFormPage(viewModel)
        }

        // Si un id est envoyer dans la page alors precharger l'article
        if (intent.hasExtra("id")) {
            // Récupérer une parametre suite à une nav d'activité
            val id = intent.getStringExtra("id")!!

            // Appel api
            viewModel.reloadArticleDetail(id)
        }
    }
}

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
            TitlePage("Article Form")
            Spacer(modifier = Modifier.height(40.dp))
            WrapPadding {
                EniTextField(
                    value = articleState.title,
                    onValueChange = { value -> viewModel.article.value = viewModel.article.value.copy(title = value) },
                    hintText = "Title"
                )
            }
            WrapPadding {
                EniTextField(
                    value = articleState.desc,
                    onValueChange = { value -> viewModel.article.value = viewModel.article.value.copy(desc = value) },
                    hintText = "Description"
                )
            }
            WrapPadding {
                EniTextField(
                    value = articleState.author,
                    onValueChange = { value -> viewModel.article.value = viewModel.article.value.copy(author = value) },
                    hintText = "Author"
                )
            }
            WrapPadding {
                EniButton(label = "Enregister") {
                    viewModel.saveArticle()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AArticleFormPagePreview() {
    ArticleFormPage(ArticleViewModel())
}