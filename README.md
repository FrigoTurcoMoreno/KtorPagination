# Ktor Pagination

[![Maven Central](https://img.shields.io/maven-central/v/io.github.frigoturcomoreno/ktor-pagination.svg?label=Maven%20Central)](https://search.maven.org/artifact/io.github.frigoturcomoreno/ktor-pagination)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

A lightweight Kotlin pagination extension library for Exposed ORM with built-in support for all entity types.

## Features

✅ Extension functions for all Exposed entity classes  
✅ Automatic total count and page calculation  
✅ Clean, serializable `PaginationDTO` response  
✅ Type-safe API with zero configuration  
✅ Full KDoc documentation

## Supported Entity Types

- `IntEntity`
- `LongEntity`
- `UIntEntity`
- `ULongEntity`
- `UUIDEntity`
- `CompositeEntity`

## Installation

### Gradle (Kotlin DSL)

```kotlin
dependencies {
    implementation("io.github.frigoturcomoreno:ktor-pagination:1.0.0")
}
```

### Gradle (Groovy)

```groovy
dependencies {
    implementation 'io.github.frigoturcomoreno:ktor-pagination:1.0.0'
}
```

### Maven

```xml
<dependency>
    <groupId>io.github.frigoturcomoreno</groupId>
    <artifactId>ktor-pagination</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Usage

### Basic Example

```kotlin
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

// Define your table
object Users : IntIdTable() {
    val name = varchar("name", 50)
    val email = varchar("email", 100)
}

// Define your entity
class User(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<User>(Users)

    var name by Users.name
    var email by Users.email
}

// Use pagination
val usersPage = User.page(pageNumber = 1, pageSize = 10)

println("Total users: ${usersPage.totalCount}")
println("Current page: ${usersPage.pageNumber} of ${usersPage.totalPages}")
println("Users on this page: ${usersPage.items.size}")
```

### Ktor Route Example

```kotlin
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.configureRouting() {
    routing {
        get("/users") {
            val pageNumber = call.parameters["page"]?.toIntOrNull() ?: 1
            val pageSize = call.parameters["size"]?.toIntOrNull() ?: 20

            val result = transaction {
                User.page(pageNumber = pageNumber, pageSize = pageSize)
            }

            call.respond(result)
        }
    }
}
```

### Response Format

The `PaginationDTO` class provides the following structure:

```json
{
  "items": [
    { "id": 1, "name": "John Doe", "email": "john@example.com" },
    { "id": 2, "name": "Jane Smith", "email": "jane@example.com" }
  ],
  "total_count": 100,
  "page_number": 1,
  "page_size": 10,
  "total_pages": 10
}
```

### Working with Different Entity Types

```kotlin
// LongEntity
class Product(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<Product>(Products)
}

val productsPage = Product.page(pageNumber = 2, pageSize = 20)

// UUIDEntity
class Session(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<Session>(Sessions)
}

val sessionsPage = Session.page(pageNumber = 1, pageSize = 50)
```

### Advanced Example with Filtering

```kotlin
fun Application.configureRouting() {
    routing {
        get("/users/search") {
            val query = call.parameters["q"] ?: ""
            val pageNumber = call.parameters["page"]?.toIntOrNull() ?: 1
            val pageSize = call.parameters["size"]?.toIntOrNull() ?: 20

            val result = transaction {
                // Note: You'll need to implement custom filtering
                // This library provides pagination on the result set
                val filteredUsers = User.find { Users.name like "%$query%" }

                // For filtered results, you might want to create a custom pagination function
                // or use the base .page() function on the entire dataset
                User.page(pageNumber = pageNumber, pageSize = pageSize)
            }

            call.respond(result)
        }
    }
}
```

## API Reference

### Extension Functions

All entity classes have access to the `page()` extension function:

```kotlin
fun <T : IntEntity> IntEntityClass<T>.page(
    pageNumber: Int,
    pageSize: Int
): PaginationDTO<T>
```

**Parameters:**
- `pageNumber` - The page number to retrieve (1-indexed)
- `pageSize` - The number of items per page

**Returns:**
- `PaginationDTO<T>` - Contains paginated items and metadata

### PaginationDTO

```kotlin
data class PaginationDTO<T>(
    val items: List<T>,        // Items on the current page
    val totalCount: Long,      // Total number of items
    val pageNumber: Int,       // Current page number (1-indexed)
    val pageSize: Int,         // Number of items per page
    val totalPages: Int        // Total number of pages
)
```

## Requirements

- Kotlin 1.9+
- Ktor 2.x
- Exposed (DAO module)
- kotlinx.serialization

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## Author

**Moreno Frigo Turco**  
GitHub: [@FrigoTurcoMoreno](https://github.com/FrigoTurcoMoreno)

## Support

If you encounter any issues or have questions, please [open an issue](https://github.com/FrigoTurcoMoreno/KtorPagination/issues) on GitHub.

---

Made with ❤️ for the Kotlin community