DESCRIPTION         = "open62541 is an implementation of OPC UA (OPC Unified Architecture)"
HOMEPAGE            = "https://github.com/open62541/open62541.git"
LICENSE             = "MPL-2.0 & BSD-3-Clause & MIT"
LIC_FILES_CHKSUM    = "file://LICENSE;md5=815ca599c9df247a0c7f619bab123dad \
                       file://deps/mdnsd/LICENSE;md5=3bb4047dc4095cd7336de3e2a9be94f0 \
                       file://deps/mqtt-c/LICENSE;md5=9226377baf0b79174c89a1ab55592456"
PV                  = "1.3.6+git${SRCPV}"
RCONFLICTS:${PN}    = "open62541"

inherit cmake

SRCREV_FORMAT = "bbopen62541_mdnsd_ua-nodeset_mqtt-c"
SRCREV_bbopen62541 = "b5b632382032201de6e11f3fe7001d3fddaaa5a2"
SRCREV_mdnsd = "3151afe5899dba5125dffa9f4cf3ae1fe2edc0f0"
SRCREV_ua-nodeset = "f71b3f411d5cb16097c3ae0c744f67ad45535ffb"
SRCREV_mqtt-c = "f69ce1e7fd54f3b1834c9c9137ce0ec5d703cb4d"

SRC_URI += "\
    git://github.com/openDAQ/open62541.git;name=bbopen62541;branch=opendaq/1.3.6;protocol=https \
    git://github.com/OPCFoundation/UA-Nodeset;name=ua-nodeset;protocol=https;branch=v1.04;destsuffix=git/deps/ua-nodeset \
    git://github.com/LiamBindle/MQTT-C.git;name=mqtt-c;protocol=https;branch=master;destsuffix=git/deps/mqtt-c \
    git://github.com/Pro/mdnsd.git;name=mdnsd;protocol=https;branch=master;destsuffix=git/deps/mdnsd \
    file://0001-fix-build-do-not-install-git-files.patch \
"

S = "${WORKDIR}/git"

EXTRA_OECMAKE += "\
    -DFETCHCONTENT_FULLY_DISCONNECTED=ON \
    -DBUILD_SHARED_LIBS=ON \
    -DCMAKE_INTERPROCEDURAL_OPTIMIZATION=OFF \
    -DUA_NAMESPACE_ZERO=FULL \
    -DUA_ENABLE_TYPEDESCRIPTION=ON \
    -DUA_ENABLE_STATUSCODE_DESCRIPTIONS=ON \
"

PACKAGECONFIG ?= "multithreading subscriptions subscriptions-events werror"
PACKAGECONFIG[amalgamation] = "-DUA_ENABLE_AMALGAMATION=ON, -DUA_ENABLE_AMALGAMATION=OFF"
PACKAGECONFIG[encryption-mbedtls] = "-DUA_ENABLE_ENCRYPTION=MBEDTLS, , mbedtls, , , encryption-openssl"
PACKAGECONFIG[encryption-openssl] = "-DUA_ENABLE_ENCRYPTION=OPENSSL, , openssl, , , encryption-mbedtls"
PACKAGECONFIG[multithreading] = "-DUA_MULTITHREADING=100, -DUA_MULTITHREADING=0"
PACKAGECONFIG[pubsub] = "-DUA_ENABLE_PUBSUB=ON, -DUA_ENABLE_PUBSUB=OFF"
PACKAGECONFIG[pubsub-eth] = "-DUA_ENABLE_PUBSUB_ETH_UADP=ON, -DUA_ENABLE_PUBSUB_ETH_UADP=OFF"
PACKAGECONFIG[subscriptions] = "-DUA_ENABLE_SUBSCRIPTIONS=ON, -DUA_ENABLE_SUBSCRIPTIONS=OFF"
PACKAGECONFIG[subscriptions-events] = "-DUA_ENABLE_SUBSCRIPTIONS_EVENTS=ON, -DUA_ENABLE_SUBSCRIPTIONS_EVENTS=OFF"
PACKAGECONFIG[werror] = "-DUA_FORCE_WERROR=ON, -DUA_FORCE_WERROR=OFF"

do_configure:prepend:toolchain-clang:riscv64() {
    sed -i -e 's/set(CMAKE_INTERPROCEDURAL_OPTIMIZATION ON)/set(CMAKE_INTERPROCEDURAL_OPTIMIZATION OFF)/' ${S}/CMakeLists.txt
}

do_configure:prepend:toolchain-clang:riscv32() {
    sed -i -e 's/set(CMAKE_INTERPROCEDURAL_OPTIMIZATION ON)/set(CMAKE_INTERPROCEDURAL_OPTIMIZATION OFF)/' ${S}/CMakeLists.txt
}

FILES_${PN}-dev += "/usr/share/open62541/tools"
