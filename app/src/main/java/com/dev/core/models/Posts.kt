package com.dev.core.models
import androidx.annotation.Keep

import kotlinx.serialization.Serializable

import kotlinx.serialization.SerialName


@Keep
@Serializable
data class Posts(
    @SerialName("data")
    val `data`: Data,
    @SerialName("kind")
    val kind: String
)

@Keep
@Serializable
data class Data(
    @SerialName("after")
    val after: String,
    @SerialName("children")
    val children: List<Children>,
    @SerialName("dist")
    val dist: Int,
    @SerialName("modhash")
    val modhash: String
)

@Keep
@Serializable
data class Children(
    @SerialName("data")
    val `data`: DataX,
    @SerialName("kind")
    val kind: String
)

@Keep
@Serializable
data class DataX(
    @SerialName("allow_live_comments")
    val allowLiveComments: Boolean,
    @SerialName("archived")
    val archived: Boolean,
    @SerialName("author")
    val author: String,
    @SerialName("author_flair_background_color")
    val authorFlairBackgroundColor: String,
    @SerialName("author_flair_text_color")
    val authorFlairTextColor: String,
    @SerialName("author_flair_type")
    val authorFlairType: String,
    @SerialName("author_fullname")
    val authorFullname: String,
    @SerialName("author_patreon_flair")
    val authorPatreonFlair: Boolean,
    @SerialName("author_premium")
    val authorPremium: Boolean,
    @SerialName("can_gild")
    val canGild: Boolean,
    @SerialName("can_mod_post")
    val canModPost: Boolean,
    @SerialName("clicked")
    val clicked: Boolean,
    @SerialName("contest_mode")
    val contestMode: Boolean,
    @SerialName("created")
    val created: Double,
    @SerialName("created_utc")
    val createdUtc: Double,
    @SerialName("domain")
    val domain: String,
    @SerialName("downs")
    val downs: Int,
    @SerialName("edited")
    val edited: Boolean,
    @SerialName("gilded")
    val gilded: Int,
    @SerialName("gildings")
    val gildings: Gildings,
    @SerialName("hidden")
    val hidden: Boolean,
    @SerialName("hide_score")
    val hideScore: Boolean,
    @SerialName("id")
    val id: String,
    @SerialName("is_created_from_ads_ui")
    val isCreatedFromAdsUi: Boolean,
    @SerialName("is_crosspostable")
    val isCrosspostable: Boolean,
    @SerialName("is_meta")
    val isMeta: Boolean,
    @SerialName("is_original_content")
    val isOriginalContent: Boolean,
    @SerialName("is_reddit_media_domain")
    val isRedditMediaDomain: Boolean,
    @SerialName("is_robot_indexable")
    val isRobotIndexable: Boolean,
    @SerialName("is_self")
    val isSelf: Boolean,
    @SerialName("is_video")
    val isVideo: Boolean,
    @SerialName("link_flair_background_color")
    val linkFlairBackgroundColor: String,
    @SerialName("link_flair_text_color")
    val linkFlairTextColor: String,
    @SerialName("link_flair_type")
    val linkFlairType: String,
    @SerialName("locked")
    val locked: Boolean,
    @SerialName("media_embed")
    val mediaEmbed: MediaEmbed,
    @SerialName("media_only")
    val mediaOnly: Boolean,
    @SerialName("name")
    val name: String,
    @SerialName("no_follow")
    val noFollow: Boolean,
    @SerialName("num_comments")
    val numComments: Int,
    @SerialName("num_crossposts")
    val numCrossposts: Int,
    @SerialName("over_18")
    val over18: Boolean,
    @SerialName("permalink")
    val permalink: String,
    @SerialName("pinned")
    val pinned: Boolean,
    @SerialName("post_hint")
    val postHint: String,
    @SerialName("preview")
    val preview: Preview,
    @SerialName("quarantine")
    val quarantine: Boolean,
    @SerialName("saved")
    val saved: Boolean,
    @SerialName("score")
    val score: Int,
    @SerialName("secure_media_embed")
    val secureMediaEmbed: SecureMediaEmbed,
    @SerialName("selftext")
    val selftext: String,
    @SerialName("selftext_html")
    val selftextHtml: String,
    @SerialName("send_replies")
    val sendReplies: Boolean,
    @SerialName("spoiler")
    val spoiler: Boolean,
    @SerialName("stickied")
    val stickied: Boolean,
    @SerialName("subreddit")
    val subreddit: String,
    @SerialName("subreddit_id")
    val subredditId: String,
    @SerialName("subreddit_name_prefixed")
    val subredditNamePrefixed: String,
    @SerialName("subreddit_subscribers")
    val subredditSubscribers: Int,
    @SerialName("subreddit_type")
    val subredditType: String,
    @SerialName("thumbnail")
    val thumbnail: String,
    @SerialName("title")
    val title: String,
    @SerialName("total_awards_received")
    val totalAwardsReceived: Int,
    @SerialName("ups")
    val ups: Int,
    @SerialName("upvote_ratio")
    val upvoteRatio: Double,
    @SerialName("url")
    val url: String,
    @SerialName("url_overridden_by_dest")
    val urlOverriddenByDest: String,
    @SerialName("visited")
    val visited: Boolean
)

@Keep
@Serializable
class Gildings(
)

@Keep
@Serializable
data class MediaEmbed(
    @SerialName("content")
    val content: String,
    @SerialName("height")
    val height: Int,
    @SerialName("scrolling")
    val scrolling: Boolean,
    @SerialName("width")
    val width: Int
)

@Keep
@Serializable
data class Preview(
    @SerialName("enabled")
    val enabled: Boolean,
    @SerialName("images")
    val images: List<Image>
)

@Keep
@Serializable
data class SecureMediaEmbed(
    @SerialName("content")
    val content: String,
    @SerialName("height")
    val height: Int,
    @SerialName("media_domain_url")
    val mediaDomainUrl: String,
    @SerialName("scrolling")
    val scrolling: Boolean,
    @SerialName("width")
    val width: Int
)

@Keep
@Serializable
data class Image(
    @SerialName("id")
    val id: String,
    @SerialName("resolutions")
    val resolutions: List<Resolution>,
    @SerialName("source")
    val source: Source,
    @SerialName("variants")
    val variants: Variants
)

@Keep
@Serializable
data class Resolution(
    @SerialName("height")
    val height: Int,
    @SerialName("url")
    val url: String,
    @SerialName("width")
    val width: Int
)

@Keep
@Serializable
data class Source(
    @SerialName("height")
    val height: Int,
    @SerialName("url")
    val url: String,
    @SerialName("width")
    val width: Int
)

@Keep
@Serializable
class Variants(
)