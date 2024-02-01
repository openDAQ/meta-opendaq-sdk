SUMMARY = "Cross-platform C++ utility library"
AUTHOR = "Hottinger Brüel & Kjær"
HOMEPAGE = "https://github.com/hbkworld/libhbk"
LICENSE = "MIT"
LIC_FILES_CHKSUM += "file://LICENSE;md5=e3fc50a88d0a364313df4b21ef20c29e"

SRC_URI = "git://github.com/hbkworld/libhbk.git;branch=main;protocol=https"
SRCREV = "v${PV}"
S = "${WORKDIR}/git"

DEPENDS = "boost"

inherit cmake

EXTRA_OECMAKE = "\
    -DFETCHCONTENT_FULLY_DISCONNECTED=ON \
    -DBUILD_SHARED_LIBS=ON \
"

EXTRA_OECMAKE:append:qemux86-64 = "-DHBK_HARDWARE=OFF"
