package com.example.noteappfirebasecrud.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Notes(
    @SerialName("title") val title: String,
    @SerialName("note") val note: String,
    @SerialName("date") val date: String? = null,
    @SerialName("id") val documentId: String? = null,
)
