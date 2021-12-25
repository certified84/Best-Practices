package com.certified.streamchatexample.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChatUser(val firstName: String, val lastName: String): Parcelable