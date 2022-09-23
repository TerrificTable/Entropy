#include <iostream>
#include <string.h>
#include <stdio.h>


int main(int argc, char** argv) {
    if (argc < 3) { 
        std::cout << __FILE__ << " <operation: [htoi | sh]> <value (hex: without \"0x\")>" << std::endl;
        return -1;
    }

    std::string opt = argv[1];
    if (opt == "htoi") {
        char* in = argv[2];
        std::cout << std::stoul(in, nullptr, 16) << " | " << in << std::endl;
        return 0;
    } else if (opt == "sh") {
        if (argc < 4) {
            std::cout << __FILE__ << " sh <direction: [left | right | and | or]> <base value> <shift value>" << std::endl;
            return -1;
        }

        std::string direction = argv[2];
        char* base = argv[3];
        char* shift = argv[4];
        
        if (direction == "left") {
            std::cout << (std::stoul(base, nullptr, 16) << std::stoul(shift, nullptr, 16)) << " | " << base << " << " << shift << std::endl;
        } else if (direction == "right") {
            std::cout << (std::stoul(base, nullptr, 16) >> std::stoul(shift, nullptr, 16)) << " | " << base << " >> " << shift << std::endl;
        } else if (direction == "and") {
            std::cout << (std::stoul(base, nullptr, 16) & std::stoul(shift, nullptr, 16)) << " | " << base << " & " << shift << std::endl;
        } else if (direction == "or") {
            std::cout << (std::stoul(base, nullptr, 16) | std::stoul(shift, nullptr, 16)) << " | " << base << " | " << shift << std::endl;
        } else {
            std::cout << "invalid direction" << std::endl; 
            return -1;
        }
        
        return 0;
    } else {
        std::cout << "Invalid option" << std::endl;
        return -1;
    }



    return 0;
}

