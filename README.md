<picture>
    <source media="(prefers-color-scheme: dark)" srcset="docs/images/opendaq-logo-dark.png">
    <img alt="OpenDAQ logo" src="docs/images/opendaq-logo.png">
</picture>

# **meta-opendaq-sdk**<br><small>OpenEmbedded / Yocto Project Layer for the openDAQ SDK</small>

## Overview

`meta-opendaq-sdk` is an OpenEmbedded layer for the [openDAQ SDK][opendaq]. It provides recipes to
configure and build the SDK, as well as all required dependencies that are not already provided by
the core OpenEmbedded layers. It also provides an example recipe which demonstrates how to create
a recipe for a package which links to the openDAQ SDK. The layer can be integrated with a stock
distribution such as Poky, or with any custom embedded firmware build.

## Compatibility

This version of the openDAQ layer is compatible with [`dunfell`][yocto-releases]. A version of the
layer compatible with [`kirkstone`][yocto-releases] will be provided in the future. The layer has
been tested with [Poky][poky] as well as with internal custom embedded firmware builds for a
variety of hardware platforms.

<a name="deps"></a>

## Dependencies

The `meta-opendaq-sdk` layer depends on the following layers:

* [openembedded-core](https://layers.openembedded.org/layerindex/branch/dunfell/layer/openembedded-core/)
* [meta-oe](https://layers.openembedded.org/layerindex/branch/dunfell/layer/meta-oe/)

## Recipes provided

The `meta-opendaq-sdk` layer provides the following recipes:

| Group                | Name               | Version  |Git Hash | Rationale |
|----------------------|--------------------|----------|---------|-----------|
| recipes-opendaq      | opendaq-example    | 1.0.0    |         | [Example recipe](#example) for linking to the SDK |
| recipes-opendaq      | **opendaq-sdk**    | latest   | 63e6b92 | **openDAQ SDK** |
| recipes-opendaq      | jet-module         | latest   | 92a77bc | openDAQ library |
| recipes-opendaq      | libstream          | 1.0.0    | c30615c | openDAQ library |
| recipes-opendaq      | native-streaming   | 1.0.2    | 5e40d6b | openDAQ library |
| recipes-opendaq      | streaming-protocol | latest   | c9f6643 | openDAQ library |
| recipes-devtools     | cmake-native       | 3.26.4   |         | openDAQ SDK requires &ge; 3.24 but Dunfell core only provides 3.16.5 |
| recipes-extended     | date               | 3.0.1`*` |         | Not provided in any core Dunfell layer |
| recipes-support      | fmt                | 7.1.0    |         | openDAQ SDK requires &ge; 7.x but Dunfell core only provides 6.2.0 |
| recipes-devtools     | jetpeer-cpp        | 3.0.0    |         | C++ Jet client library |
| recipes-devtools     | libhbk             | 2.0.0    |         | Cross-platform C++ utility library |
| recipes-support      | mimalloc           | 2.1.1    |         | Not provided in any core Dunfell layer |
| recipes-protocols    | mjansson-mdns      | 1.4.3    |         | Not provided in any core Dunfell layer |
| recipes-devtools     | nlohmann-json      | 3.10.5   |         | openDAQ SDK requires &ge; 3.10.5 but Dunfell core only provides 3.7.3 |
| recipes-protocols    | bbopen62541        | 1.3.6    |         |openDAQ fork of open62541 project |
| recipes-support      | spdlog             | 1.8.1    |         |openDAQ SDK requires &ge; 1.8.1 but Dunfell core only provides 1.5.0 |
| recipes-support      | taskflow           | 3.4.0    |         |Not provided in any core Dunfell layer |
| recipes-support      | tsl-ordered-map    | 1.1.0    |         |Not provided in any core Dunfell layer |
| recipes-support      | xxhash             | 0.8.1    |         |openDAQ SDK requires &ge; 0.8.1 but Dunfell core only provides 0.7.3 |

`*` Git ref `22ceabf205d8d678710a43154da5a06b701c5830` is used because the latest release, 3.0.1,
    is old and out of date.

# Integration

## Quick start (TL;DR)

### `bblayers.conf`:

    BBLAYERS ?= " \
        ...
        /path/to/openembedded-core/meta \
        /path/to/meta-openembedded/meta-oe \
        /path/to/meta-opendaq-sdk \
        ...
    "

### `local.conf`:

    CORE_IMAGE_EXTRA_INSTALL += "opendaq-sdk"
    HOSTTOOLS += "mono"

## Detailed integration istructions

To add the openDAQ SDK:

1.  **Get the openDAQ SDK layer**

    Make the `meta-opendaq-sdk` layer and [its dependencies](#deps) available to the build, using
    Git submodules, Google's `repo` tool, or whatever other mechanism is appropriate to your build
    environment.

2.  **Add the openDAQ SDK layer to `bblayers.conf`**

    Add the `meta-opendaq-sdk` layer (and if not already present, [its dependencies](#deps)) to
   `bblayers.conf`:

        # in bblayers.conf
        BBLAYERS ?= "\
            ... \
            /path/to/openembedded-core/meta \
            /path/to/meta-openembedded/meta-oe \
            /path/to/meta-opendaq-sdk \
            ... \
        "

3.  **Include the `opendaq-sdk` package**

    Add the `opendaq-sdk` package to `CORE_IMAGE_EXTRA_INTSTALL`, or as a dependency of another
    recipe (see for example [`opendaq-example`](#example)), or in any other way suitable to the
    platform:

        # in local.conf, an image recipe, etc.
        CORE_IMAGE_EXTRA_INSTALL += "opendaq-sdk"

        # or, in another installed recipe (see opendaq-example_*.bb)...
        DEPENDS = "opendaq-sdk"

4.  **Enable `mono`**

    The openDAQ SDK requires [Mono][mono] to execute a code generation tool at build time. There
    are two methods to make Mono available. The first is to install Mono on the build machine, and
    whitelist the `mono` command using `HOSTTOOLS`. The second is to compile Mono itself within
    Yocto using the [`meta-mono`][meta-mono] layer.

    a.  **As a whitelisted host tool**

    Install `mono` on the host using whatever package manager is appropriate for the distribution.
    Then add the following code to `local.conf`:

        # allow recipes to invoke mono on the host; throw error if mono is not installed
        HOSTTOOLS += "mono"

    b.  **As a Yocto layer**

    Add the [`meta-mono`][meta-mono] layer in the same way `meta-opendaq-sdk` was added in steps
    (1) and (2) above. The absence of `mono` in `HOSTTOOLS` causes the SDK recipe to automatically
    add a dependency on `mono-native`.

    Note that at least one `meta-mono` layer available in the OpenEmbedded Layer Index does not
    correctly configure `mono-native` to use the correct configuration file. In this case, the
    correct configuration file path can be specified by creating an `opendaq-sdk_%.bbappend` file
    with the following contents:

        export MONO_CONFIG = "${STAGING_DIR_NATIVE}/etc/mono/config"

<a name="example"></a>

## Example recipe

A sample recipe, [`opendaq-example`][opendaq-example-bb], is included in `recipes-opendaq` which
demonstrates how to create a recipe for a package which links to the openDAQ SDK. It contains a
skeleton C++ program built with CMake which links to the SDK using `find_package()`.

## Package features

The following feature flags can be enabled using the `PACKAGECONFIG` mechanism. Features **in
bold** are enabled by default (but can be disabled).

* `mimalloc`
* `opcua`
* `parameter-validation`
* `streaming`
* `thread-safe`

To set the enabled features, set `PACKAGECONFIG` in an `opendaq-sdk_%.bbappend` file, or set
`PACKAGECONFIG_pn-opendaq-sdk` in e.g. `local.conf`:

    PACKAGECONFIG_pn-opendaq-sdk = "mimalloc streaming"

[meta-mono]: https://layers.openembedded.org/layerindex/branch/dunfell/layer/meta-mono/
[mono]: https://www.mono-project.com/
[opendaq]: https://opendaq.com/
[opendaq-example-bb]: recipes-opendaq/opendaq-example/opendaq-example_1.0.0.bb
[poky]: https://www.yoctoproject.org/software-item/poky/
[yocto-releases]: https://wiki.yoctoproject.org/wiki/Releases
