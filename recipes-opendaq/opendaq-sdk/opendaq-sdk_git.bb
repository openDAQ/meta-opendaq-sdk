SUMMARY             = "openDAQ data acquisition SDK"
AUTHOR              = "openDAQ d.o.o."
HOMEPAGE            = "https://opendaq.com/"
SECTION             = "openDAQ"
LICENSE             = "Apache-2.0"
PV                  = "2.1.0+git${SRCPV}"
DEPENDS             = "boost date fmt mjansson-mdns spdlog taskflow tsl-ordered-map xxhash"

# if mono is not in HOSTTOOLS, add mono-native as a dependency; see README.md at the top of this layer
DEPENDS += "${@bb.utils.contains('HOSTTOOLS', 'mono', '', 'mono-native', d)}"

inherit cmake

SRC_URI = "\
    git://github.com/openDAQ/openDAQ.git;protocol=https;name=opendaq-sdk;branch=main \
    git://github.com/openDAQ/opc-ua-companion-spec.git;protocol=https;name=tmsspec;branch=master;destsuffix=deps/src/daqspec \
    git://github.com/hbkworld/opc-ua-specs.git;name=daqhbkspec;protocol=https;destsuffix=deps/src/daqhbkspec;branch=main \
"

LIC_FILES_CHKSUM += "file://LICENSE;md5=98b4c298fafe3a9dc30f957028ce3224"

SRCREV_FORMAT = "opendaq-sdk_tmsspec"
SRCREV_opendaq-sdk = "63e6b923b89e63bb37df3f0cf690f0bc9ffc38dd"
SRCREV_tmsspec = "9f7306e702e3cf698ff7fcabe421ef4c9b77139d"
SRCREV_daqhbkspec = "cd7e6033eb74b7f1725edbf2035f0e4c6fa2cc96"

S = "${WORKDIR}/git"

EXTRA_OECMAKE += "\
    -DFETCHCONTENT_FULLY_DISCONNECTED=ON \
    -DFETCHCONTENT_EXTERNALS_DIR=${WORKDIR}/deps \
    -DOPENDAQ_ALWAYS_FETCH_DEPENDENCIES=OFF \
    -DOPENDAQ_RELEASE_WARNINGS_AS_ERRORS=OFF \
    -DOPENDAQ_ENABLE_TESTS=OFF \
    -DOPENDAQ_ENABLE_NATIVE_STREAMING=ON \
"

PACKAGECONFIG ?= "\
    opcua \
    parameter-validation \
    server \
    streaming \
    thread-safe \
"

PACKAGECONFIG[client] = "-DDAQMODULES_OPENDAQ_CLIENT_MODULE=ON, -DDAQMODULES_OPENDAQ_CLIENT_MODULE=OFF"
PACKAGECONFIG[mimalloc] = "-DOPENDAQ_MIMALLOC_SUPPORT=ON, -DOPENDAQ_MIMALLOC_SUPPORT=OFF, mimalloc"
PACKAGECONFIG[opcua] = "-DOPENDAQ_ENABLE_OPCUA=ON, -DOPENDAQ_ENABLE_OPCUA=OFF, bbopen62541"
PACKAGECONFIG[parameter-validation] = "-DOPENDAQ_ENABLE_PARAMETER_VALIDATION=ON, -DOPENDAQ_ENABLE_PARAMETER_VALIDATION=OFF"
PACKAGECONFIG[reference-device] = "-DDAQMODULES_REF_DEVICE_MODULE=ON, -DDAQMODULES_REF_DEVICE_MODULE=OFF"
PACKAGECONFIG[server] = "-DDAQMODULES_OPENDAQ_SERVER_MODULE=ON, -DDAQMODULES_OPENDAQ_SERVER_MODULE=OFF"
PACKAGECONFIG[streaming] = "-DOPENDAQ_ENABLE_STREAMING=ON, -DOPENDAQ_ENABLE_STREAMING=OFF, native-streaming streaming-protocol"
PACKAGECONFIG[thread-safe] = "-DOPENDAQ_THREAD_SAFE=ON, -DOPENDAQ_THREAD_SAFE=OFF"
PACKAGECONFIG[ws-streaming] = "-DOPENDAQ_ENABLE_WEBSOCKET_STREAMING=ON, -DOPENDAQ_ENABLE_WEBSOCKET_STREAMING=OFF, streaming-protocol"

FILES_${PN} += "/usr/bindings"
FILES_${PN} += "/usr/lib/*.so"
FILES_${PN} += "/usr/lib/modules/*.so"

FILES_SOLIBSDEV = ""

do_install_append(){
    install -m 0644 ${S}/cmake/openDAQUtils.cmake ${D}/usr/lib/cmake/opendaq
}
