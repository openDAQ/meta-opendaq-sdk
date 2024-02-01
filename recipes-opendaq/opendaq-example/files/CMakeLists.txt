cmake_minimum_required(VERSION 3.21)

project(opendaq-example VERSION 1.0.0 LANGUAGES CXX)

find_package(openDAQ REQUIRED)

add_executable(${PROJECT_NAME} example.cpp)

set_target_properties(${PROJECT_NAME} PROPERTIES
    CXX_STANDARD            17
    CXX_STANDARD_REQUIRED   ON
)

target_link_libraries(${PROJECT_NAME} PUBLIC daq::opendaq)

install(TARGETS ${PROJECT_NAME})
