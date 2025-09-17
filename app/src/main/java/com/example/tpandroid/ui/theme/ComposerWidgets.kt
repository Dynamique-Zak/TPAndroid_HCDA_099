package com.example.tpandroid.ui.theme

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.tpandroid.R
import com.example.tpandroid.article.Article
import com.example.tpandroid.article.ArticleDetailsActivity
import com.example.tpandroid.article.ArticleFormActivity
import com.example.tpandroid.common.AlertDialog
import com.example.tpandroid.common.AppContextHelper
import com.example.tpandroid.common.ProgressDialog

@Composable
fun BackgroundImage(@DrawableRes backgroundId: Int = R.drawable.mobile_bg_01) {
    Image(
        painter = painterResource(backgroundId),
        contentDescription = "",
        contentScale = ContentScale.Crop,
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
fun TemplatePage(
    @DrawableRes backgroundId: Int = R.drawable.mobile_bg_01,
    content: @Composable () -> Unit
) {
    TPAndroidTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                BackgroundImage(backgroundId)
                content()
                ProgressDialog()
                AlertDialog()
            }
        }
    }
}

@Composable
fun EniLogo() {
    Image(
        painter = painterResource(R.drawable.logo_eni_round),
        contentDescription = "logo"
    )
}

@Composable
fun WrapPadding(content: @Composable () -> Unit) {
    Box(modifier = Modifier.padding(5.dp)) {
        content()
    }
}

@Composable
fun TitlePage(title: String = "Titre") {
    Text(
        title,
        textAlign = TextAlign.Center,
        color = Color(0xFF134a80),
        fontSize = 42.sp,
        fontWeight = FontWeight.Bold,
        style = TextStyle(
            shadow = Shadow(
                color = Color(0x770b58d8),
                offset = Offset(0f, 0f), blurRadius = 5f
            )
        )
    )
}

@Composable
fun EniTextField(hintText: String = "Veuillez saisir...", value: String = "", onValueChange: (String) -> Unit = {}) {
    TextField(
        value = value, onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color(0x44000000),
            unfocusedIndicatorColor = Color.Transparent,
        ),
        shape = RoundedCornerShape(40.dp),
        placeholder = {
            Text(hintText, color = Color(0xCCFFFFFF))
        }
    )
}

@Composable
fun EniButton(label: String = "Invalid", onClick: () -> Unit = {}) {
    Button(
        onClick = onClick,
        border = BorderStroke(4.dp, Color(0x11000000)),
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(0.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .background(
                    brush = Brush.linearGradient(
                        listOf(Color(0xFF0b58d8), Color(0xFF31a9ff))
                    )
                )
                .fillMaxWidth()
                .padding(vertical = 18.dp)
        ) {
            Text(label)
        }
    }
}

@Composable
fun ArticleCard(article: Article, onRequestDelete : (id: String) -> Unit = {}) {
    val context = LocalContext.current

    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Column {
            Row(modifier = Modifier.padding(10.dp)) {
                AsyncImage(
                    model = article.imgPath,
                    contentDescription = "",
                    placeholder = painterResource(R.drawable.article_placeholder),
                    modifier = Modifier.width(92.dp)
                )
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(article.title, fontWeight = FontWeight.Bold)
                    Text(article.desc, color = Color(0xFF555555))
                    EniButton("Voir") {
                        AppContextHelper.openActivityWithString(
                            context,
                            ArticleDetailsActivity::class,
                            "id", article.id!!)
                    }
                    EniButton("Editer") {
                        AppContextHelper.openActivityWithString(
                            context,
                            ArticleFormActivity::class,
                            "id", article.id!!)
                    }
                    EniButton("Delete") {
                        onRequestDelete(article.id!!)
                    }
                }
            }
            Box(
                modifier = Modifier
                    .background(
                        brush = Brush.linearGradient(
                            listOf(Color(0xFF0b58d8), Color(0xFF31a9ff))
                        )
                    )
                    .fillMaxWidth()
                    .height(4.dp)
            )
        }
    }
}