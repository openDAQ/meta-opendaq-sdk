SUMMARY             = "openDAQ native streaming"
AUTHOR              = "openDAQ d.o.o."
HOMEPAGE            = "https://opendaq.com/"
SECTION             = "openDAQ"
LICENSE             = "Apache-2.0"
PV                  = "1.0.2+git${SRCPV}"
DEPENDS             = "boost fmt libstream nlohmann-json spdlog"

inherit cmake

SRC_URI += "git://github.com/openDAQ/libNativeStreaming.git;protocol=https;branch=main"

LIC_FILES_CHKSUM += "\
    file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327 \
    file://LICENSE.txt;md5=93cbd52bfc499580f50db5762ac5fe31 \
"

SRCREV = "5e40d6b6c4c9e5d6ebf72e0d98253a242e7fe317"
S = "${WORKDIR}/git"

EXTRA_OECMAKE += "\
    -DCMAKE_MODULE_PATH=${STAGING_DIR_TARGET}/usr/lib/cmake \
    -DFETCHCONTENT_FULLY_DISCONNECTED=ON \
    -DCMAKE_POSITION_INDEPENDENT_CODE=ON \
"
