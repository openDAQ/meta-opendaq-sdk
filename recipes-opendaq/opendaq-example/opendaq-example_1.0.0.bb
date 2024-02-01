# This is an example showing how to create a recipe for a package which links
# to the openDAQ SDK. The included files/example.cpp is a minimal skeleton
# whose only purpose is to ensure a program can correctly link to the SDK.

SUMMARY     = "openDAQ application recipe example"
AUTHOR      = "openDAQ d.o.o."
HOMEPAGE    = "https://opendaq.com/"
SECTION     = "openDAQ"
LICENSE     = "CLOSED"

# Make the openDAQ SDK available in this recipe's sysroot. It can then be
# found by, for example, CMake find_package().
DEPENDS = "opendaq-sdk"

inherit cmake

SRC_URI = "\
    file://CMakeLists.txt;subdir=${BPN}-${PV} \
    file://example.cpp;subdir=${BPN}-${PV} \
"
