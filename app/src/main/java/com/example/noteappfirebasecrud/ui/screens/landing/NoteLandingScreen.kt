package com.example.noteappfirebasecrud.ui.screens.landing

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.noteappfirebasecrud.R
import com.example.noteappfirebasecrud.ui.theme.noteAppFirebaseCrudFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteLandingScreen(
    navigateToNoteScreen: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets(0.dp, 0.dp, 0.dp, 0.dp),
        containerColor = Color(0xFFF1EEDC)
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 112.dp)
                    .heightIn(max = 325.dp),
                painter = painterResource(id = R.drawable.reading_girl),
                contentDescription = "reading_girl"
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Create & Design ",
                color = Color.Black,
                maxLines = 1,
                style = TextStyle(
                    fontFamily = noteAppFirebaseCrudFamily,
                    fontWeight = FontWeight.Thin,
                    color = Color.Black,
                    fontSize = 28.sp
                ),
                textAlign = TextAlign.Start
            )

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "your notes Easily",
                color = Color.Black,
                maxLines = 1,
                style = TextStyle(
                    fontFamily = noteAppFirebaseCrudFamily,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 32.sp
                ),
                textAlign = TextAlign.Start
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Your digital canvas for ideas, dreams, and daily musings. Unleash your creativity with a tap",
                color = Color.Black,
                style = TextStyle(
                    fontFamily = noteAppFirebaseCrudFamily,
                    fontWeight = FontWeight.Thin,
                    color = Color.Black,
                    fontSize = 16.sp
                ),
                textAlign = TextAlign.Start
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 64.dp, start = 48.dp, end = 48.dp)
                    .height(48.dp),
                colors = ButtonDefaults
                    .buttonColors(
                        containerColor = Color.Black
                    ),
                onClick = navigateToNoteScreen
            ) {
                Text(
                    text = "Get started",
                    color = Color.White,
                    style = TextStyle(
                        fontFamily = noteAppFirebaseCrudFamily,
                        fontWeight = FontWeight.Medium,
                        color = Color.White,
                        fontSize = 18.sp
                    ),
                    textAlign = TextAlign.Start
                )
            }

        }
    }
}