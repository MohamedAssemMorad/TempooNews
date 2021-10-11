package com.example.newproject.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class SourceResponse(

    @SerializedName("id")
    val id: String? = null,

    @SerializedName("name")
    val name: String? = null
) : Parcelable