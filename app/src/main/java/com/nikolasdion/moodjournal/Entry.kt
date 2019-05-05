package com.nikolasdion.moodjournal

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName="entry_table")
data class Entry (@PrimaryKey(autoGenerate = true) val id : Int = 0,
                  @ColumnInfo(name = "date") val date : Long,
                  @ColumnInfo(name = "trigger") val trigger : String,
                  @ColumnInfo(name = "thoughts") val thoughts : List<String>,
                  @ColumnInfo(name = "feelings") val feelings : List<String>,
                  @ColumnInfo(name = "physical") val physical : List<String>,
                  @ColumnInfo(name = "behaviour") val behaviour : List<String>,
                  @ColumnInfo(name = "notes") val notes : String)