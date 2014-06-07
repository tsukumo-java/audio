cmake_minimum_required (VERSION 2.8)

find_package(Java REQUIRED)
include(UseJava)

project (audio)

SET(JAVA_TARGET_VERSION 1.6)

SET(AUDIO_MAJOR_VERSION 0)
SET(AUDIO_MINOR_VERSION 1)
SET(AUDIO_PATCH_VERSION 0)
SET(AUDIO_VERSION
    ${AUDIO_MAJOR_VERSION}.${AUDIO_MINOR_VERSION}.${AUDIO_PATCH_VERSION})

SET(CMAKE_JAVA_COMPILE_FLAGS
    "-source" ${JAVA_TARGET_VERSION}
    "-target" ${JAVA_TARGET_VERSION}
)

SET(SRC

# Utilities
../utils/AudioConstants.java

# Data structures
../data/RingBuffer.java

# Manager components
../manager/AudioManager.java

)

SET(INC

)

SET(ENTRY_POINT
# This is a library/service and therefore should not have
# An entry point unless it is for testing purposes.
)

add_jar(tsu-audio-j${JAVA_TARGET_VERSION} ${SRC}
    INCLUDE_JARS ${INC}
    #ENTRY_POINT ${ENTRY_POINT}
    VERSION ${AUDIO_VERSION})

