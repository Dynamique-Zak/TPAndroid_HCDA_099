package com.example.tpandroid.article

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

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
        onRequestEdit = { article -> navController.navigate("form/${article.id!!}") },
        onRequestDelete = { article -> viewModel.deleteArticle(article.id!!) },
    )

    NavHost(navController, startDestination = "list") {
        composable("list") { ListArticlePage(viewModel) }

        composable(
            "detail/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")

            // Appel api
            viewModel.reloadArticleDetail(id!!)

            ArticleDetailsPage(viewModel)
        }

        composable(
            "form/{id}",
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->

            val id = backStackEntry.arguments?.getString("id")

            // Si un id est envoyer dans la page alors precharger l'article
            if (id != null) {
                // Appel api
                viewModel.reloadArticleDetail(id)
            }
            // Vider l'article au cas si on est en creation
            else {
                viewModel.clearArticle()
            }

            ArticleFormPage(viewModel)

        }
    }
}


@Preview(showBackground = true)
@Composable
fun ArticlePagePreview() {
    ArticlePage(ArticleViewModel(Application()))
}