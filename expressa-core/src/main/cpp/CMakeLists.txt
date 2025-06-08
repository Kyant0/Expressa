cmake_minimum_required(VERSION 4.0.2)

project(mcu_jni)

set(CMAKE_C_STANDARD 23)

set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -O3 -flto -fdata-sections -ffunction-sections -fomit-frame-pointer")
set(CMAKE_SHARED_LINKER_FLAGS "${CMAKE_SHARED_LINKER_FLAGS} -flto -Wl,--exclude-libs,ALL -Wl,--gc-sections -Wl,-s -Wl,--build-id=none -Wl,-z,max-page-size=16384")

add_library(mcu_rs SHARED IMPORTED)
set_target_properties(mcu_rs PROPERTIES
        IMPORTED_LOCATION "${CMAKE_SOURCE_DIR}/../lib/${ANDROID_ABI}/libmcu_rs.so")

add_library(${CMAKE_PROJECT_NAME} SHARED
        lib.c)

target_compile_options(${CMAKE_PROJECT_NAME} PRIVATE -Wall)

add_custom_command(TARGET ${CMAKE_PROJECT_NAME} POST_BUILD
        COMMAND ${CMAKE_OBJCOPY}
        --remove-section .comment
        --remove-section .note
        --strip-debug $<TARGET_FILE:${CMAKE_PROJECT_NAME}>)

target_link_libraries(${CMAKE_PROJECT_NAME} PRIVATE
        android
        mcu_rs)
