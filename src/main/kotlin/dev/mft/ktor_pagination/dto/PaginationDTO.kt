package dev.mft.ktor_pagination.dto

import kotlinx.serialization.Required
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data Transfer Object that encapsulates paginated data along with pagination metadata.
 *
 * This class is used to represent a single page of results from a paginated query,
 * including the items on the current page and information about the total dataset
 * and pagination state.
 *
 * @param T the type of items contained in the paginated result
 * @property items the list of items for the current page
 * @property totalCount the total number of items across all pages
 * @property pageNumber the current page number (1-indexed)
 * @property pageSize the number of items per page
 * @property totalPages the total number of pages available
 *
 * @constructor Creates a new pagination result with the specified items and metadata
 *
 * @sample
 * ```
 * val pagination = PaginationDTO(
 *     items = listOf(user1, user2, user3),
 *     totalCount = 100L,
 *     pageNumber = 1,
 *     pageSize = 10,
 *     totalPages = 10
 * )
 *
 * println("Showing ${pagination.items.size} of ${pagination.totalCount} items")
 * println("Page ${pagination.pageNumber} of ${pagination.totalPages}")
 * ```
 */
@Serializable
data class PaginationDTO<T>(
    /**
     * The list of items for the current page.
     *
     * This list contains the actual data items retrieved for the requested page.
     * The size of this list will be at most [pageSize], and may be less on the last page.
     */
    @SerialName("items")
    @Required
    val items: List<T>,

    /**
     * The total number of items across all pages.
     *
     * This represents the complete count of items in the dataset before pagination is applied.
     * This value remains constant regardless of which page is requested.
     */
    @SerialName("total_count")
    @Required
    val totalCount: Long,

    /**
     * The current page number (1-indexed).
     *
     * The first page is numbered 1, the second page is 2, and so on.
     * This value should be between 1 and [totalPages] inclusive.
     */
    @SerialName("page_number")
    @Required
    val pageNumber: Int,

    /**
     * The number of items per page.
     *
     * This represents the maximum number of items that should be included in each page.
     * The actual number of items in [items] may be less than this value on the last page.
     */
    @SerialName("page_size")
    @Required
    val pageSize: Int,

    /**
     * The total number of pages available.
     *
     * This is calculated by dividing [totalCount] by [pageSize] and rounding up.
     * A value of 0 indicates an empty result set.
     */
    @SerialName("total_pages")
    @Required
    val totalPages: Int
)