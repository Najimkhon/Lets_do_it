package com.hfad.letsdoit.fragments

import android.app.Application
import android.text.TextUtils
import androidx.lifecycle.AndroidViewModel
import com.hfad.letsdoit.data.models.Priority

class SharedViewModel(application: Application) : AndroidViewModel(application) {
    fun verifyDataFromUser(title: String, description: String): Boolean {
        return if (TextUtils.isEmpty(title) || TextUtils.isEmpty(description)) {
            false
        } else !(title.isEmpty() || description.isEmpty())
    }

    fun parsePriority(priority: String): Priority {
        return when (priority) {
            "High Priority" -> {
                Priority.HIGH
            }
            "High Medium" -> {
                Priority.MEDIUM
            }
            "High Low" -> {
                Priority.LOW
            }
            else -> {
                Priority.LOW
            }
        }

    }
}