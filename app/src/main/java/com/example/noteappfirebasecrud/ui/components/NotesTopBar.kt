package com.example.noteappfirebasecrud.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.noteappfirebasecrud.R
import com.example.noteappfirebasecrud.ui.theme.noteAppFirebaseCrudFamily

@Composable
fun NotesTopBar(
    value: String,
    onValueChange: (String) -> Unit,
    focusRequester: () -> FocusRequester = { FocusRequester() },
    onBackPressed: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        IconButton(
            onClick = onBackPressed,
            content = {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_back),
                    contentDescription = "back_icon",
                    tint = Color.Black,
                )
            }
        )

        BasicTextField(
            modifier = Modifier
                .weight(1f, fill = true)
                .focusRequester(focusRequester())
                .onFocusChanged {
                    if (it.isFocused)
                        onValueChange(
                            if (value.equals("Enter your Title..", ignoreCase = true))
                                "" else value
                        )
                    else
                        if (value.isEmpty())
                            onValueChange("Enter your Title..")

                },
            value = value,
            onValueChange = onValueChange,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            textStyle = TextStyle(
                fontFamily = noteAppFirebaseCrudFamily,
                fontWeight = FontWeight.Bold,
                color = if (value.equals("Enter your Title..", false))
                    Color.Black.copy(0.5f)
                else
                    Color.Black,
                fontSize = 28.sp
            ),
            maxLines = 1,
        )
        AnimatedVisibility(
            visible = (!value.equals("Enter your Title..", false)) && value.isNotEmpty(),
            enter = slideInVertically() + fadeIn(),
            exit = slideOutHorizontally() + fadeOut()
        ) {
            IconButton(
                onClick = {
                    onValueChange("")
                },
                content = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_close),
                        contentDescription = "back_icon",
                        tint = Color.Black,
                    )
                }
            )
        }
    }
}