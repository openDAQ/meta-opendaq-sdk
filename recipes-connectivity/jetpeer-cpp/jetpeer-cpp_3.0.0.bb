SUMMARY = "C++ Jet client library"
AUTHOR = "Hottinger Brüel & Kjær"
HOMEPAGE = "https://github.com/hbkworld/jetpeer-cpp"
LICENSE = "MIT"
LIC_FILES_CHKSUM += "file://LICENSE;md5=e9c25c065298302e51de856f486719b6"

SRC_URI = "git://github.com/hbkworld/jetpeer-cpp.git;protocol=https;branch=main"
SRCREV = "v${PV}"
S = "${WORKDIR}/git"

DEPENDS = "jsoncpp libhbk"

inherit cmake

EXTRA_OECMAKE = "\
    -DFETCHCONTENT_FULLY_DISCONNECTED=ON \
    -DBUILD_SHARED_LIBS=ON \
    -DJETPEER_TOOLS=ON \
"
