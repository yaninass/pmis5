package com.example.myapplication3.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication3.utils.ListItem
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Insert(onConflict=OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: ListItem)
    @Delete
    suspend fun deleteItem(item: ListItem)
    @Query("select * from main where category like :dog")
    fun getAllItemsByCategory(dog:String): Flow<List<ListItem>>

    @Query("select * from main where isfav=1")
    fun getFavorites():Flow<List<ListItem>>
}