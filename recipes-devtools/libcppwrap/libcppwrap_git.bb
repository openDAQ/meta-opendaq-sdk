SUMMARY = "A collection of C++ wrappers for native APIs"
AUTHOR = "David Norris <danorris@gmail.com>"
HOMEPAGE = "https://github.com/dvnrrs/libcppwrap"
LICENSE = "MIT"
PV = "1.3.11"

SRC_URI = "git://github.com/dvnrrs/libcppwrap;protocol=https;branch=main"
SRCREV = "v${PV}"
S = "${WORKDIR}/git"

LIC_FILES_CHKSUM += "file://LICENSE.txt;md5=2dda4ed76e474303b791a4915007a50c"

inherit cmake

EXTRA_OECMAKE += "\
    -DFETCHCONTENT_FULLY_DISCONNECTED=ON \
"
