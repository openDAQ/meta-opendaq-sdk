SUMMARY             = "openDAQ native streaming"
AUTHOR              = "openDAQ d.o.o."
HOMEPAGE            = "https://opendaq.com/"
SECTION             = "openDAQ"
LICENSE             = "Apache-2.0"
PV                  = "1.0.14+git${SRCPV}"
DEPENDS             = "boost fmt libstream nlohmann-json spdlog"

inherit cmake

SRC_URI += "git://github.com/openDAQ/libNativeStreaming.git;protocol=https;branch=main"

LIC_FILES_CHKSUM += "\
    file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327 \
    file://LICENSE.txt;md5=ceb49615d7c1685dfffae5d7a2a5187f \
"

SRCREV = "fc0d0a58931f98663d29f828593bd597ace26c55"
S = "${WORKDIR}/git"

PARALLEL_MAKE = "-j 6"

EXTRA_OECMAKE += "\
    -DCMAKE_MODULE_PATH=${STAGING_DIR_TARGET}/usr/lib/cmake \
    -DFETCHCONTENT_FULLY_DISCONNECTED=ON \
    -DCMAKE_POSITION_INDEPENDENT_CODE=ON \
"
