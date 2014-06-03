cmake_minimum_required (VERSION 2.8)

find_package(Java REQUIRED)
include(UseJava)

project (audio)

set(CMAKE_JAVA_COMPILE_FLAGS "-source" "1.6" "-target" "1.6")

set(AUDIO_MAJOR_VERSION 0)
set(AUDIO_MINOR_VERSION 1)
set(AUDIO_PATCH_VERSION 0)
set(AUDIO_VERSION
    ${AUDIO_MAJOR_VERSION}.${AUDIO_MINOR_VERSION}.${AUDIO_PATCH_VERSION})

SET(SRC


)

SET(INC

)

SET(ENTRY_POINT
    
)

add_jar(tsu-audio ${SRC}
    INCLUDE_JARS ${INC}
    #ENTRY_POINT ${ENTRY_POINT}
    VERSION ${ARJ_VERSION})

