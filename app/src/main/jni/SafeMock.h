#pragma once

#include "Includes.h"
#include <map>
#include <string>

// Non-operational CI/test implementation.
// Does not inspect libil2cpp.so, read game memory,
// enumerate players, or modify aim.

static std::map<std::string, u_long> Config;

inline void native_onCanvasDraw(
        JNIEnv *,
        jobject,
        jobject,
        int,
        int,
        float) {
    // Intentionally empty mock callback.
}

inline void native_Init(
        JNIEnv *,
        jclass,
        jobject) {
    // Intentionally empty mock initialization.
}
