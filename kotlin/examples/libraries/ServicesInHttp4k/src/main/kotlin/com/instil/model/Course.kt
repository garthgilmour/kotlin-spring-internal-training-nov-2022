package com.instil.model

enum class CourseDifficulty {
    BEGINNER,
    INTERMEDIATE,
    ADVANCED
}

class Course(val id: String,
             val title: String,
             val difficulty: CourseDifficulty,
             val duration: Int)
