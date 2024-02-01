SUMMARY             = "A date and time library based on C++11/14/17."
AUTHOR              = "Howard Hinnant"
HOMEPAGE            = "https://github.com/HowardHinnant/date.git"
SECTION             = "libs"
LICENSE             = "MIT"
LIC_FILES_CHKSUM    = "file://LICENSE.txt;md5=b5d973344b3c7bbf7535f0e6e002d017"
PV                  = "3.0.1"
DEPENDS             = "curl"

inherit cmake

SRC_URI = "\
    git://github.com/HowardHinnant/date.git;protocol=https;branch=master \
    file://pkgconfig.patch \
"

SRCREV = "22ceabf205d8d678710a43154da5a06b701c5830"

S = "${WORKDIR}/git"

EXTRA_OECMAKE += "\
    -DBUILD_TZ_LIB=ON \
    -DBUILD_SHARED_LIBS=ON \
    -DUSE_SYSTEM_TZ_DB=ON \
"
