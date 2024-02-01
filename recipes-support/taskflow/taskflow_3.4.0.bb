SUMMARY             = "A General-purpose Parallel and Heterogeneous Task Programming System"
AUTHOR              = "Dr. Tsung-Wei Huang"
HOMEPAGE            = "https://taskflow.github.io/"
SECTION             = "libs"
LICENSE             = "MIT"
LIC_FILES_CHKSUM    = "file://LICENSE;md5=81efd69646bdd88ae97f93e7460da20d"

inherit cmake

SRC_URI = "\
    git://github.com/taskflow/taskflow.git;protocol=git;branch=master \
    file://0001-TBBAS-280-Fix-Executor-async-functions-to-set-except.patch \
    file://0002-Implement-fallback-uncaught-exception-handler.patch \
    file://0003-Ignore-False-Positive-Mem-Leak.patch \
"

SRCREV = "v${PV}"

S = "${WORKDIR}/git"

EXTRA_OECMAKE += "\
    -DTF_BUILD_TESTS=OFF \
    -DTF_BUILD_EXAMPLES=OFF \
    -DBUILD_TESTING=OFF \
"
