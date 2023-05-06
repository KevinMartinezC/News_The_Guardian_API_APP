package com.example.news.components.search.model.utils

import com.example.news.data.network.Filter

const val SECTION_POLITICS = "politics"
const val SECTION_TECHNOLOGY = "technology"
const val SECTION_CULTURE = "culture"
const val TYPE_LIVEBLOG = "liveblog"
const val TYPE_INTERACTIVE = "interactive"
const val TAG_ENVIRONMENT_RECYCLING = "environment/recycling"
const val TAG_POLITICS_BLOG = "politics/blog"

fun generateFilters(): List<Filter> {
    val sections = listOf(SECTION_POLITICS, SECTION_TECHNOLOGY, SECTION_CULTURE)
    val types = listOf(TYPE_LIVEBLOG, TYPE_INTERACTIVE)
    val tags = listOf(TAG_ENVIRONMENT_RECYCLING, TAG_POLITICS_BLOG)

    val sectionFilters = sections.map { section -> Filter(section, section = section) }
    val typeFilters = types.map { type -> Filter(type, type = type) }
    val tagFilters = tags.map { tag -> Filter(tag, tag = tag) }

    return sectionFilters + typeFilters + tagFilters

}

