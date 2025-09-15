package com.example.tpandroid.auth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tpandroid.R
import com.example.tpandroid.article.ListArticleActivity
import com.example.tpandroid.common.AppContextHelper
import com.example.tpandroid.ui.theme.EniButton
import com.example.tpandroid.ui.theme.EniLogo
import com.example.tpandroid.ui.theme.EniTextField
import com.example.tpandroid.ui.theme.TemplatePage
import com.example.tpandroid.ui.theme.TitlePage
import com.example.tpandroid.ui.theme.WrapPadding
import kotlinx.coroutines.flow.MutableStateFlow

class LoginActivity : ComponentActivity() {

    lateinit var viewModel : MutableStateFlow<AuthViewModel>;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        viewModel = MutableStateFlow(AuthViewModel(email = "isaac@gmail.com", password = "password"))

        setContent {
            LoginPage(viewModel)
        }
    }
}

@Composable
fun LoginPage(viewModel: MutableStateFlow<AuthViewModel>) {

    // Ecouter les changements de tout le view model
    val viewModelState by viewModel.collectAsState()

    val context = LocalContext.current

    TemplatePage {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(40.dp)
        ) {
            EniLogo()
            Spacer(modifier = Modifier.height(120.dp))
            TitlePage("Login")
            Spacer(modifier = Modifier.height(40.dp))
            WrapPadding {
                EniTextField(
                    value = viewModelState.email,
                    onValueChange = { value -> viewModel.value = viewModel.value.copy(email = value)},
                    hintText = "Veuillez saisir un email")
            }
            WrapPadding {
                EniTextField(
                    value = viewModelState.password,
                    onValueChange = { value -> viewModel.value = viewModel.value.copy(password = value)},
                    hintText = "Veuillez saisir un mot de passe")
            }
            WrapPadding {
                EniButton(label = "Connexion", onClick = {
                    viewModelState.callLoginApi(context)
                })
            }
            Spacer(modifier = Modifier.weight(1f))
            WrapPadding {
                EniButton(label = "Sign Up", onClick = {
                    AppContextHelper.openActivity(context, SignUpActivity::class)
                })
            }
            WrapPadding {
                EniButton(label = "Reset Password", onClick = {
                    AppContextHelper.openActivity(context, ResetPasswordActivity::class)
                })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    val viewModel = MutableStateFlow(AuthViewModel(email = "isaac@gmail.com", password = "password"))

    LoginPage(viewModel)
}