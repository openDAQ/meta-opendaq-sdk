SUMMARY             = "openDAQ Jet interface module"
AUTHOR              = "openDAQ d.o.o."
HOMEPAGE            = "https://opendaq.com/"
SECTION             = "openDAQ"
LICENSE             = "Apache-2.0"
PV                  = "1.0.0+git${SRCPV}"
DEPENDS             = "jetpeer-cpp jsoncpp libhbk opendaq-sdk pugixml"

inherit cmake

SRC_URI += "git://github.com/openDAQ/JetModule.git;protocol=https;branch=main"

LIC_FILES_CHKSUM += "file://LICENSE;md5=98b4c298fafe3a9dc30f957028ce3224"

SRCREV = "92a77bcb89d01691165a0df50915df3ba0d027b6"
S = "${WORKDIR}/git"

EXTRA_OECMAKE += "\
    -DCMAKE_MODULE_PATH=${STAGING_DIR_TARGET}/usr/lib/cmake \
    -DFETCHCONTENT_FULLY_DISCONNECTED=ON \
"

FILES_${PN} += "/usr/lib/modules/*.so"
