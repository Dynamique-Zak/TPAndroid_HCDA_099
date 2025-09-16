package com.example.tpandroid.auth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tpandroid.auth.login.AuthViewModel
import com.example.tpandroid.ui.theme.EniButton
import com.example.tpandroid.ui.theme.EniLogo
import com.example.tpandroid.ui.theme.EniTextField
import com.example.tpandroid.ui.theme.TemplatePage
import com.example.tpandroid.ui.theme.TitlePage
import com.example.tpandroid.ui.theme.WrapPadding
import kotlinx.coroutines.flow.MutableStateFlow

class ResetPasswordActivity : ComponentActivity() {

    lateinit var viewModel : MutableStateFlow<AuthViewModel>;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        viewModel = MutableStateFlow(AuthViewModel(resetPasswordRequest = ResetPasswordRequest(email = "user@example.com")))

        setContent {
            ResetPasswordPage(viewModel)
        }
    }
}

@Composable
fun ResetPasswordPage(viewModel : MutableStateFlow<AuthViewModel>) {

    val viewModelState by viewModel.collectAsState()

    TemplatePage {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(40.dp)
                .padding(top = 10.dp)
        ) {
            EniLogo()
            Spacer(modifier = Modifier.height(180.dp))
            TitlePage("Password Recovery")
            Spacer(modifier = Modifier.height(60.dp))
            WrapPadding {
                EniTextField(
                    value = viewModelState.resetPasswordRequest.email,
                    onValueChange = { value -> viewModel.value = viewModel.value.copy(resetPasswordRequest = viewModelState.resetPasswordRequest.copy(email = value))},
                    hintText = "Veuillez saisir un email")
            }
            WrapPadding {
                EniButton(label = "Send link recovery", onClick = {
                    viewModelState.callResetPasswordApi()
                })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ResetPasswordPreview() {

    val viewModel = MutableStateFlow(AuthViewModel(resetPasswordRequest = ResetPasswordRequest(email = "user@example.com")))

    ResetPasswordPage(viewModel)
}