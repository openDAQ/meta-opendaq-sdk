SUMMARY             = "A general purpose allocator with excellent performance characteristics."
AUTHOR              = "Microsoft"
HOMEPAGE            = "https://github.com/microsoft/mimalloc.git"
SECTION             = "libs"
LICENSE             = "MIT"
LIC_FILES_CHKSUM    = "file://LICENSE;md5=cd47cf280095d74b469beed1f28c8e77"

inherit cmake

SRC_URI = "\
    git://github.com/microsoft/mimalloc.git;protocol=git;branch=master \
"

SRCREV="v${PV}"

S = "${WORKDIR}/git"

FILES_${PN}-staticdev += "/usr/lib/*/*.a"
FILES_${PN}-staticdev += "/usr/lib/*/*.o"
