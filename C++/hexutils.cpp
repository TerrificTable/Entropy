#include <iostream>
#include <string.h>
#include <stdio.h>


int main(int argc, char** argv) {
    if (argc < 2) { 
        std::cout << __FILE__ << " <operation: [htoi | sh | help]>" << std::endl;
        return -1;
    }

    std::string opt = argv[1];
    if (opt == "htoi") {
        if (argc < 3) {
            std::cout << __FILE__ << " htoi <hex value without '0x'>" << std::endl;
            return -1;
        }

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
            std::cout 
                << (std::stoul(base, nullptr, 16) << std::stoul(shift, nullptr, 16)) 
                << " | " << base << " << " << shift 
                << std::endl;
        } else if (direction == "right") {
            std::cout 
                << (std::stoul(base, nullptr, 16) >> std::stoul(shift, nullptr, 16)) 
                << " | " << base << " >> " << shift 
                << std::endl;
        } else if (direction == "and") {
            std::cout 
                << (std::stoul(base, nullptr, 16) & std::stoul(shift, nullptr, 16)) 
                << " | " << base << " & " << shift 
                << std::endl;
        } else if (direction == "or") {
            std::cout 
                << (std::stoul(base, nullptr, 16) | std::stoul(shift, nullptr, 16)) 
                << " | " << base << " | " << shift 
                << std::endl;
        } else {
            std::cout << "invalid direction: '" << direction << "'" << std::endl; 
            return -1;
        }
        
        return 0;
    } else if (opt == "h" || opt == "help") {
        std::cout 
            << " === Help ===\n"
            << "   - htoi <hex value without '0x'>\t\t\t\t\t\t->\tConvertes hex to int\n"
            << "   - sh <direction: [left | right | and | or]> <value to shift> <shift value>\t->\tShifts one hex number into *direction* using the other hex number\n"
            << "   - help\t\t\t\t\t\t\t\t\t->\tshows this\n"
            << " === Help ===" 
            << std::endl;
        return 0;
    } else {
        std::cout << "Invalid option: '" << opt << "'" << std::endl;
        return -1;
    }



    return 0;
}

