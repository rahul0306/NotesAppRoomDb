package com.example.notesapproomdb.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities = [Notes::class],
    version = 2
)
abstract class NoteDatabase : RoomDatabase() {

    abstract val dao: NoteDao

    companion object{
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Create the new table with the updated schema
                database.execSQL(
                    """
                    CREATE TABLE new_Notes (
                        id TEXT NOT NULL PRIMARY KEY,
                        title TEXT NOT NULL,
                        description TEXT
                    )
                    """.trimIndent()
                )

                // Copy the data from the old table to the new one
                database.execSQL(
                    """
                    INSERT INTO new_Notes (id, title, description)
                    SELECT id, title, description FROM Notes
                    """.trimIndent()
                )

                // Drop the old table
                database.execSQL("DROP TABLE Notes")

                // Rename the new table to match the old table name
                database.execSQL("ALTER TABLE new_Notes RENAME TO Notes")
            }
        }
    }


}