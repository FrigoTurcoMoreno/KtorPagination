package dev.mft.ktor_pagination.service

import dev.mft.ktor_pagination.dto.PaginationDTO
import org.jetbrains.exposed.dao.CompositeEntity
import org.jetbrains.exposed.dao.CompositeEntityClass
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.UIntEntity
import org.jetbrains.exposed.dao.UIntEntityClass
import org.jetbrains.exposed.dao.ULongEntity
import org.jetbrains.exposed.dao.ULongEntityClass
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass

/**
 * Extension function that provides pagination support for [IntEntityClass].
 *
 * This function retrieves a paginated subset of entities from the database,
 * along with metadata about the total count and number of pages.
 *
 * @param T the entity type that extends [IntEntity]
 * @param pageNumber the page number to retrieve (1-indexed)
 * @param pageSize the number of items per page
 * @return [PaginationDTO] containing the paginated items and metadata
 *
 * @throws IllegalArgumentException if pageNumber is less than 1 or pageSize is less than 1
 *
 * @sample
 * ```
 * val usersPage = Users.page(pageNumber = 1, pageSize = 10)
 * println("Total users: ${usersPage.totalCount}")
 * println("Current page: ${usersPage.pageNumber} of ${usersPage.totalPages}")
 * ```
 */
fun <T : IntEntity> IntEntityClass<T>.page(
    pageNumber: Int,
    pageSize: Int
): PaginationDTO<T> {
    val totalCount = this.all().count()
    val offset = (pageNumber - 1) * pageSize
    val items = this.all().limit(pageSize).offset(offset.toLong()).toList()
    val totalPages = (totalCount + pageSize - 1) / pageSize

    return PaginationDTO(
        items = items,
        totalCount = totalCount,
        pageNumber = pageNumber,
        pageSize = pageSize,
        totalPages = totalPages.toInt()
    )
}

/**
 * Extension function that provides pagination support for [LongEntityClass].
 *
 * This function retrieves a paginated subset of entities from the database,
 * along with metadata about the total count and number of pages.
 *
 * @param T the entity type that extends [LongEntity]
 * @param pageNumber the page number to retrieve (1-indexed)
 * @param pageSize the number of items per page
 * @return [PaginationDTO] containing the paginated items and metadata
 *
 * @throws IllegalArgumentException if pageNumber is less than 1 or pageSize is less than 1
 *
 * @sample
 * ```
 * val productsPage = Products.page(pageNumber = 2, pageSize = 20)
 * productsPage.items.forEach { product -> println(product.name) }
 * ```
 */
fun <T : LongEntity> LongEntityClass<T>.page(
    pageNumber: Int,
    pageSize: Int
): PaginationDTO<T> {
    val totalCount = this.all().count()
    val offset = (pageNumber - 1) * pageSize
    val items = this.all().limit(pageSize).offset(offset.toLong()).toList()
    val totalPages = (totalCount + pageSize - 1) / pageSize

    return PaginationDTO(
        items = items,
        totalCount = totalCount,
        pageNumber = pageNumber,
        pageSize = pageSize,
        totalPages = totalPages.toInt()
    )
}

/**
 * Extension function that provides pagination support for [UIntEntityClass].
 *
 * This function retrieves a paginated subset of entities from the database,
 * along with metadata about the total count and number of pages.
 *
 * @param T the entity type that extends [UIntEntity]
 * @param pageNumber the page number to retrieve (1-indexed)
 * @param pageSize the number of items per page
 * @return [PaginationDTO] containing the paginated items and metadata
 *
 * @throws IllegalArgumentException if pageNumber is less than 1 or pageSize is less than 1
 *
 * @sample
 * ```
 * val ordersPage = Orders.page(pageNumber = 1, pageSize = 50)
 * ```
 */
fun <T : UIntEntity> UIntEntityClass<T>.page(
    pageNumber: Int,
    pageSize: Int
): PaginationDTO<T> {
    val totalCount = this.all().count()
    val offset = (pageNumber - 1) * pageSize
    val items = this.all().limit(pageSize).offset(offset.toLong()).toList()
    val totalPages = (totalCount + pageSize - 1) / pageSize

    return PaginationDTO(
        items = items,
        totalCount = totalCount,
        pageNumber = pageNumber,
        pageSize = pageSize,
        totalPages = totalPages.toInt()
    )
}

/**
 * Extension function that provides pagination support for [ULongEntityClass].
 *
 * This function retrieves a paginated subset of entities from the database,
 * along with metadata about the total count and number of pages.
 *
 * @param T the entity type that extends [ULongEntity]
 * @param pageNumber the page number to retrieve (1-indexed)
 * @param pageSize the number of items per page
 * @return [PaginationDTO] containing the paginated items and metadata
 *
 * @throws IllegalArgumentException if pageNumber is less than 1 or pageSize is less than 1
 *
 * @sample
 * ```
 * val transactionsPage = Transactions.page(pageNumber = 3, pageSize = 25)
 * ```
 */
fun <T : ULongEntity> ULongEntityClass<T>.page(
    pageNumber: Int,
    pageSize: Int
): PaginationDTO<T> {
    val totalCount = this.all().count()
    val offset = (pageNumber - 1) * pageSize
    val items = this.all().limit(pageSize).offset(offset.toLong()).toList()
    val totalPages = (totalCount + pageSize - 1) / pageSize

    return PaginationDTO(
        items = items,
        totalCount = totalCount,
        pageNumber = pageNumber,
        pageSize = pageSize,
        totalPages = totalPages.toInt()
    )
}

/**
 * Extension function that provides pagination support for [UUIDEntityClass].
 *
 * This function retrieves a paginated subset of entities from the database,
 * along with metadata about the total count and number of pages.
 *
 * @param T the entity type that extends [UUIDEntity]
 * @param pageNumber the page number to retrieve (1-indexed)
 * @param pageSize the number of items per page
 * @return [PaginationDTO] containing the paginated items and metadata
 *
 * @throws IllegalArgumentException if pageNumber is less than 1 or pageSize is less than 1
 *
 * @sample
 * ```
 * val sessionsPage = Sessions.page(pageNumber = 1, pageSize = 15)
 * ```
 */
fun <T : UUIDEntity> UUIDEntityClass<T>.page(
    pageNumber: Int,
    pageSize: Int
): PaginationDTO<T> {
    val totalCount = this.all().count()
    val offset = (pageNumber - 1) * pageSize
    val items = this.all().limit(pageSize).offset(offset.toLong()).toList()
    val totalPages = (totalCount + pageSize - 1) / pageSize

    return PaginationDTO(
        items = items,
        totalCount = totalCount,
        pageNumber = pageNumber,
        pageSize = pageSize,
        totalPages = totalPages.toInt()
    )
}

/**
 * Extension function that provides pagination support for [CompositeEntityClass].
 *
 * This function retrieves a paginated subset of entities from the database,
 * along with metadata about the total count and number of pages.
 *
 * @param T the entity type that extends [CompositeEntity]
 * @param pageNumber the page number to retrieve (1-indexed)
 * @param pageSize the number of items per page
 * @return [PaginationDTO] containing the paginated items and metadata
 *
 * @throws IllegalArgumentException if pageNumber is less than 1 or pageSize is less than 1
 *
 * @sample
 * ```
 * val compositeRecordsPage = CompositeRecords.page(pageNumber = 1, pageSize = 10)
 * ```
 */
fun <T : CompositeEntity> CompositeEntityClass<T>.page(
    pageNumber: Int,
    pageSize: Int
): PaginationDTO<T> {
    val totalCount = this.all().count()
    val offset = (pageNumber - 1) * pageSize
    val items = this.all().limit(pageSize).offset(offset.toLong()).toList()
    val totalPages = (totalCount + pageSize - 1) / pageSize

    return PaginationDTO(
        items = items,
        totalCount = totalCount,
        pageNumber = pageNumber,
        pageSize = pageSize,
        totalPages = totalPages.toInt()
    )
}