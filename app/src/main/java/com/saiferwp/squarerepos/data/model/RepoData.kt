package com.saiferwp.squarerepos.data.model

data class RepoData(
    val id: String,
    val archived: Boolean,
    val name: String,
    val language: String?,
    val description: String?,
    val stargazers_count: Long
)