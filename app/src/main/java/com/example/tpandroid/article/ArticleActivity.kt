package com.example.tpandroid.article

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tpandroid.R
import com.example.tpandroid.ui.theme.ArticleCard
import com.example.tpandroid.ui.theme.EniButton
import com.example.tpandroid.ui.theme.TemplatePage
import com.example.tpandroid.ui.theme.TitlePage
import com.example.tpandroid.ui.theme.WrapPadding

class ArticleActivity : ComponentActivity() {

    lateinit var viewModel: ArticleViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        viewModel = ArticleViewModel(application)

        setContent {
            ArticlePage(viewModel)
        }
    }
}

@Composable
fun ArticlePage(viewModel: ArticleViewModel) {

    val navController = rememberNavController()

    // Redéfinir ce qu'on fait dans les 3 evenements
    viewModel.articleListener = ArticleListener(
        onRequestView = { article -> navController.navigate("detail/${article.id!!}") },
        onRequestEdit = { article -> navController.navigate("form") },
        onRequestDelete = { article -> viewModel.deleteArticle(article.id!!) },
    )

    NavHost(navController, startDestination = "list"){
        composable("list") { ListArticlePage(viewModel) }

        composable("detail/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType})) {
            backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")

            // Appel api
            viewModel.reloadArticleDetail(id!!)

            ArticleDetailsPage(viewModel)
        }

        composable("form") { ArticleFormPage(viewModel) }
    }
}


@Preview(showBackground = true)
@Composable
fun ArticlePagePreview() {
    ArticlePage(ArticleViewModel(Application()))
}