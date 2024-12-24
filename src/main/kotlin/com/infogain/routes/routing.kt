package com.infogain.routes

import com.infogain.repository.UserRepository
import com.infogain.repository.impl.RoleRepositoryImpl
import com.infogain.repository.impl.UserRepositoryImpl
import com.infogain.service.impl.RoleServiceImpl
import com.infogain.service.impl.UserServiceImpl
import io.ktor.server.routing.*


fun Route.rootRoutes(){

     val userRepository =UserRepositoryImpl()
    val userService = UserServiceImpl(userRepository)
    userRoutes(userService)
    val roleRepository = RoleRepositoryImpl()
    val roleService = RoleServiceImpl(roleRepository)
    roleRoutes(roleService)
}