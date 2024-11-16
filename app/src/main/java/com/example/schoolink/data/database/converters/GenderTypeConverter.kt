package com.example.schoolink.data.database.converters

import androidx.room.TypeConverter
import com.example.schoolink.domain.models.Gender

class GenderTypeConverter {
    @TypeConverter
    fun fromGender(gender: Gender): String {
        return gender.name
    }

    @TypeConverter
    fun toGender(genderString: String): Gender {
        return Gender.valueOf(genderString)
    }
}