SUMMARY             = "Public domain mDNS/DNS-SD library in C"
AUTHOR              = "Mattias Jansson <mjansson@gmail.com>"
HOMEPAGE            = "https://github.com/mjansson/mdns"
SECTION             = "libs"
LICENSE             = "PD"
LIC_FILES_CHKSUM    = "file://LICENSE;md5=911690f51af322440237a253d695d19f"

inherit cmake

SRC_URI = "\
    git://github.com/mjansson/mdns.git;protocol=git;branch=main \
"

SRCREV = "${PV}"

S = "${WORKDIR}/git"

EXTRA_OECMAKE += "\
    -DMDNS_BUILD_EXAMPLE=OFF \
"
