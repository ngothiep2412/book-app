package com.plcoding.bookpedia.book.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


//version -> xác định phiên bản database
@Database(
    entities = [BookEntity::class],
    version = 1
)


@TypeConverters(
    StringListTypeConverter::class
)
abstract class FavoriteBookDatabase: RoomDatabase() {
    abstract val favoriteBooksDao: FavoriteBookDao

    companion object {
        const val DB_NAME = "book.db"
    }
}