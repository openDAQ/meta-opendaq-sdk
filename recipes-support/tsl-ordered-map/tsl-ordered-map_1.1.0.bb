SUMMARY             = "C++ hash map and hash set which preserves the order of insertion"
AUTHOR              = "Thibaut Goetghebuer-Planchon <tessil@gmx.com>"
HOMEPAGE            = "https://github.com/Tessil/ordered-map"
SECTION             = "libs"
LICENSE             = "MIT"
LIC_FILES_CHKSUM    = "file://LICENSE;md5=7d9128c23e4bdb36bdceedce604442e0"

inherit cmake

SRC_URI = "\
    git://github.com/Tessil/ordered-map.git;protocol=git;branch=master \
"

SRCREV = "v${PV}"

S = "${WORKDIR}/git"
