package com.instil.model

object Builder {
    fun buildPortfolio(): MutableMap<String, Course> {
        val portfolio = mutableMapOf<String, Course>()
        portfolio["AB12"] =
            Course("AB12", "Programming in Scala", CourseDifficulty.BEGINNER, 4)
        portfolio["CD34"] = Course(
            "CD34",
            "Machine Learning in Python",
            CourseDifficulty.INTERMEDIATE,
            3
        )
        portfolio["EF56"] =
            Course("EF56", "Advanced Kotlin Coding", CourseDifficulty.ADVANCED, 2)
        portfolio["GH78"] = Course(
            "GH78",
            "Intro to Domain Driven Design",
            CourseDifficulty.BEGINNER,
            3
        )
        portfolio["IJ90"] = Course(
            "IJ90",
            "Database Access with JPA",
            CourseDifficulty.INTERMEDIATE,
            3
        )
        portfolio["KL12"] = Course(
            "KL12",
            "Functional Design Patterns in F#",
            CourseDifficulty.ADVANCED,
            2
        )
        portfolio["MN34"] = Course(
            "MN34",
            "Building Web UIs with Angular",
            CourseDifficulty.BEGINNER,
            4
        )
        portfolio["OP56"] = Course(
            "OP56",
            "Version Control with Git",
            CourseDifficulty.INTERMEDIATE,
            1
        )
        portfolio["QR78"] =
            Course("QR78", "SQL Server Masterclass", CourseDifficulty.ADVANCED, 2)
        portfolio["ST90"] = Course(
            "ST90",
            "Go Programming for Beginners",
            CourseDifficulty.BEGINNER,
            5
        )
        portfolio["UV12"] = Course(
            "UV12",
            "Coding with Lock Free Algorithms",
            CourseDifficulty.INTERMEDIATE,
            2
        )
        portfolio["WX34"] = Course(
            "WX34",
            "Coaching Skills for SCRUM Masters",
            CourseDifficulty.ADVANCED,
            3
        )

        return portfolio
    }
}