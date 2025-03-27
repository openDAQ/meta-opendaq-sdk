SUMMARY             = "openDAQ streaming protocol"
AUTHOR              = "openDAQ d.o.o."
HOMEPAGE            = "https://opendaq.com/"
SECTION             = "openDAQ"
LICENSE             = "Apache-2.0"
PV                  = "1.2.2+git${SRCPV}"
DEPENDS             = "boost fmt libstream nlohmann-json spdlog"

inherit cmake

SRC_URI += "git://github.com/openDAQ/streaming-protocol-lt.git;protocol=https;branch=release/1.2"
SRCREV = "d63391051b9cb48450a59928d9dcc9f61b61d3d3"
S = "${WORKDIR}/git"

LIC_FILES_CHKSUM += "file://LICENSE;md5=86d3f3a95c324c9479bd8986968f4327"

EXTRA_OECMAKE += "\
    -DCMAKE_MODULE_PATH=${STAGING_DIR_TARGET}/usr/lib/cmake \
    -DFETCHCONTENT_FULLY_DISCONNECTED=ON \
"
