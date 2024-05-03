package com.example.noteappfirebasecrud.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.noteappfirebasecrud.R
import com.example.noteappfirebasecrud.ui.theme.noteAppFirebaseCrudFamily

@Composable
fun Note(
    modifier: Modifier = Modifier,
    titleText: String,
    noteText: String,
    date: String,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .height(216.dp)
            .width(142.dp)
            .background(Color(0xFFB3C8CF))
            .clickable {
                onClick()
            }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier.padding(12.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.time),
                    contentDescription = "clock",
                    tint = Color.Black,
                    modifier = Modifier.size(16.dp)
                )
                Text(
                    modifier = Modifier,
                    text = date,
                    color = Color.Black,
                    maxLines = 1,
                    style = TextStyle(
                        fontFamily = noteAppFirebaseCrudFamily,
                        fontWeight = FontWeight.Medium,
                        color = Color.Black,
                        fontSize = 12.sp
                    ),
                    textAlign = TextAlign.Start
                )
            }

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, start = 12.dp, end = 12.dp),
                text = titleText,
                color = Color.Black,
                maxLines = 1,
                style = TextStyle(
                    fontFamily = noteAppFirebaseCrudFamily,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                    fontSize = 16.sp
                ),
                textAlign = TextAlign.Start,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 8.dp,
                        start = 12.dp,
                        end = 12.dp,
                        bottom = 12.dp
                    ),
                text = noteText,
                color = Color.Black,
                style = TextStyle(
                    fontFamily = noteAppFirebaseCrudFamily,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black,
                    fontSize = 12.sp
                ),
                textAlign = TextAlign.Start,
                overflow = TextOverflow.Ellipsis
            )

        }
    }
}