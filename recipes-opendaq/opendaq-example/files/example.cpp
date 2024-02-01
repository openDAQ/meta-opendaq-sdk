/**
 * Creates a simulated reference device and sets it as the root device.
 * An openDAQ server and a web-socket streaming server are started on the aplication.
 */

#include <iostream>
#include <opendaq/opendaq.h>

using namespace daq;

int main(int /*argc*/, const char* /*argv*/[])
{
    // Create an openDAQ instance
    const InstancePtr instance = Instance("/usr/lib/modules");

    // Add a reference device and set it as root
    instance.setRootDevice("daqref://device0");
    auto device = instance.getRootDevice();

    // Start openDAQ OpcUa and web-socket streaming servers
    instance.addStandardServers();

    std::cout << "Press \"enter\" to exit the application..." << std::endl;
    std::cin.get();
    return 0;
}
