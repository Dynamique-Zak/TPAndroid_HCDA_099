package com.example.tpandroid.auth.signup

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tpandroid.R
import com.example.tpandroid.auth.SignUpRequest
import com.example.tpandroid.auth.login.AuthViewModel
import com.example.tpandroid.auth.login.LoginActivity
import com.example.tpandroid.common.AppContextHelper
import com.example.tpandroid.ui.theme.EniButton
import com.example.tpandroid.ui.theme.EniTextField
import com.example.tpandroid.ui.theme.TemplatePage
import com.example.tpandroid.ui.theme.TitlePage
import com.example.tpandroid.ui.theme.WrapPadding
import kotlinx.coroutines.flow.MutableStateFlow

class SignUpActivity : ComponentActivity() {

    lateinit var viewModel : MutableStateFlow<SignUpViewModel>;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        viewModel = MutableStateFlow(SignUpViewModel())

        setContent {
            SignUpPage(viewModel)
        }
    }
}
fun updateSignUpField(
    viewModel: MutableStateFlow<SignUpViewModel>,
    update: (SignUpRequest) -> SignUpRequest
) {
    val copy = update(viewModel.value.signUpRequest)
    viewModel.value = viewModel.value.copy(
        signUpRequest = copy
    )
}

@Composable
fun SignUpPage(viewModel: MutableStateFlow<SignUpViewModel>) {

    val viewModelState by viewModel.collectAsState()

    val context = LocalContext.current

    TemplatePage(backgroundId = R.drawable.mobile_bg_02) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(40.dp)
        ) {
            Spacer(modifier = Modifier.height(80.dp))
            TitlePage("Sign Up")
            Spacer(modifier = Modifier.height(20.dp))
            WrapPadding {
                EniTextField(
                    value = viewModelState.signUpRequest.pseudo,
                    onValueChange = { value -> updateSignUpField(viewModel, update = { it -> it.copy(pseudo = value) }) },
                    hintText = "Pseudo")
            }
            WrapPadding {
                EniTextField(
                    value = viewModelState.signUpRequest.email,
                    onValueChange = { value -> updateSignUpField(viewModel, update = { it -> it.copy(email = value) }) },
                    hintText = "Email")
            }
            WrapPadding {
                EniTextField(
                    value = viewModelState.signUpRequest.password,
                    onValueChange = { value -> updateSignUpField(viewModel, update = { it -> it.copy(password = value) }) },
                    hintText = "Password")
            }
            WrapPadding {
                EniTextField(
                    value = viewModelState.signUpRequest.passwordConfirm,
                    onValueChange = { value -> updateSignUpField(viewModel, update = { it -> it.copy(passwordConfirm = value) }) },
                    hintText = "Password Confirmation")
            }
            WrapPadding {
                EniTextField(
                    value = viewModelState.signUpRequest.cityCode,
                    onValueChange = { value -> updateSignUpField(viewModel, update = { it -> it.copy(cityCode = value) }) },
                    hintText = "City Code")
            }
            WrapPadding {
                EniTextField(
                    value = viewModelState.signUpRequest.city,
                    onValueChange = { value -> updateSignUpField(viewModel, update = { it -> it.copy(city = value)  }) },
                    hintText = "City")
            }
            WrapPadding {
                EniTextField(
                    value = viewModelState.signUpRequest.phone,
                    onValueChange = { value -> updateSignUpField(viewModel, update = { it -> it.copy(phone = value) }) },
                    hintText = "Phone Number")
            }
            WrapPadding {
                EniButton(label = "Confirm", onClick = {
                    viewModelState.callSignUpApi(onSignUpSuccess = {
                        AppContextHelper.openActivity(context, LoginActivity::class)
                    })
                })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpPreview() {
    val viewModel = MutableStateFlow(SignUpViewModel())
    SignUpPage(viewModel)
}