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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tpandroid.R
import com.example.tpandroid.ui.theme.EniButton
import com.example.tpandroid.ui.theme.EniTextField
import com.example.tpandroid.ui.theme.TemplatePage
import com.example.tpandroid.ui.theme.TitlePage
import com.example.tpandroid.ui.theme.WrapPadding

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SignUpPage()
        }
    }
}

@Composable
fun SignUpPage() {
    TemplatePage(backgroundId = R.drawable.mobile_bg_02) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(40.dp)
                .padding(top = 10.dp)
        ) {
            Spacer(modifier = Modifier.height(140.dp))
            TitlePage("Sign Up")
            Spacer(modifier = Modifier.height(40.dp))
            WrapPadding {
                EniTextField(hintText = "Pseudo")
            }
            WrapPadding {
                EniTextField(hintText = "Email")
            }
            WrapPadding {
                EniTextField(hintText = "Password")
            }
            WrapPadding {
                EniTextField(hintText = "Password Confirmation")
            }
            WrapPadding {
                EniTextField(hintText = "City Code")
            }
            WrapPadding {
                EniTextField(hintText = "City")
            }
            WrapPadding {
                EniTextField(hintText = "Phone Number")
            }
            WrapPadding {
                EniButton(label = "Confirm")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpPreview() {
    SignUpPage()
}