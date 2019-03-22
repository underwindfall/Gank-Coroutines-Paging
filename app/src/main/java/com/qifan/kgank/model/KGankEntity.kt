package com.qifan.kgank.model

import com.squareup.moshi.Json

/**
 * Created by Qifan on 11/02/2019.
 */
data class KGankEntity(
    val error: Boolean? = null,
    val results: List<KGankResultsItem>? = null
)

data class KGankResultsItem(
    @field:Json(name = "_id") val id: String? = null,
    val createdAt: String? = null,
    val desc: String? = null,
    val images: List<String>? = null,
    val publishedAt: String? = null,
    val source: String? = null,
    val type: String? = null,
    val url: String? = null,
    val used: Boolean? = null,
    val who: String? = null
)