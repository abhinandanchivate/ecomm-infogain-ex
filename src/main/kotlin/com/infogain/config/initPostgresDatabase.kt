package com.infogain.config

import com.infogain.dto.RolesTable
import com.infogain.dto.UsersTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun initPostgresDatabase() {
    Database.connect(
        url = "jdbc:postgresql://localhost:5432/test", // Replace with your database URL
        driver = "org.postgresql.Driver",
        user = "root", // Replace with your database username
        password = "root" // Replace with your database password
    )
    println("PostgreSQL database connected successfully!")


    transaction {
        SchemaUtils.create(UsersTable)
        SchemaUtils.create(RolesTable)
    }
}
