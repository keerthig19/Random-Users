package com.example.randomusers.ui.screens.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.randomusers.ui.theme.black

@Composable
fun SearchView(
    state: MutableState<TextFieldValue>,
    hint: String,
    modifier: Modifier = Modifier,
) {
    Box(modifier = Modifier.padding(10.dp)) {
        TextField(
            value = state.value,
            onValueChange = { value ->
                state.value = value
            },
            modifier = modifier.width(800.dp),
            placeholder = { Text(text = hint, color = black) },
            textStyle = TextStyle(color = black, fontSize = 18.sp),
            leadingIcon = {},
            trailingIcon = {
                if (state.value != TextFieldValue("")) {
                    IconButton(
                        onClick = {
                            state.value =
                                TextFieldValue("")
                        },
                    ) {
                        Icon(
                            Icons.Default.Close,
                            contentDescription = "",
                            modifier =
                            Modifier
                                .padding(5.dp)
                                .size(24.dp),
                        )
                    }
                } else {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = "",
                        tint = black,
                        modifier =
                        Modifier
                            .padding(15.dp)
                            .size(24.dp),
                    )
                }
            },
            singleLine = true,
            shape = RoundedCornerShape(30.dp),
            colors =
            TextFieldDefaults.colors(
                focusedTextColor = black,
                unfocusedTextColor = black,
                cursorColor = black,
                focusedLeadingIconColor = black,
                unfocusedLeadingIconColor = black,
                focusedTrailingIconColor = black,
                unfocusedTrailingIconColor = black,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
            ),
        )
    }
}