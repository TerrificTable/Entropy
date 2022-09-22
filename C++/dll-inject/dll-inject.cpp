#include <iostream>
#include <windows.h>
#include <string>
#include <thread>
#include <libloaderapi.h>
#include <sys/stat.h>



DWORD get_proc_id(const char* win_name, DWORD &proc_id) {
    return GetWindowThreadProcessId(
        FindWindowA(0, win_name),
        &proc_id
    );
}



void error(const char* error, const char* message) {
    MessageBoxA(
        0, 
        message, 
        error, 
        0
    );
    exit(-1);
}
void error(const char* t_error, const char* message, HANDLE h_proc) {
    CloseHandle(h_proc);
    error(t_error, message);
}
void error(const char* t_error, const char* message, HANDLE h_proc, void* allocated_mem) {
    VirtualFreeEx(h_proc, allocated_mem, 0, MEM_RELEASE);
    CloseHandle(h_proc);
    error(t_error, message);
}



bool file_exists(const char* file_name) {
    // stat (sys/stat.h) -> https://www.ibm.com/docs/en/zos/2.3.0?topic=functions-stat-stat64-get-file-information
    struct stat buffer;
    return (stat(file_name, &buffer) == 0);
}




int main(int argc, char** argv) {
    // for (int i=0; i < argc; i++)
    //     std::cout << argv[i] << " | ";
    // std::cout << std::endl;


    DWORD proc_id = NULL;
    char dll_path[MAX_PATH];
    const char* window_title    = argv[1];
    const char* dll_name        = argv[2];

    std::cout << window_title << std::endl;
    std::cout << dll_name << std::endl;


    // if file doesnt exist; exit
    if (!file_exists(dll_name)) error("file_exist", "File doesn't exist");
    //                                                                                      tbh this is probably the worst thing i could have done, but who cares
    if (!GetFullPathNameA(dll_name, MAX_PATH, dll_path, NULL)) error("GetFullPathNameA", strncpy(const_cast<char*>(dll_name), "Failed to get full DLL path for: ", sizeof(dll_name)));

    // asign process id
    get_proc_id(window_title, proc_id);
    if (proc_id == NULL) error("get_proc_id -> proc-exists", "Failed to get process ID");


    HANDLE h_proc = OpenProcess(PROCESS_ALL_ACCESS, NULL, proc_id);
    if (!h_proc) error("OpenProcess", "Failed to open a handle to process", h_proc);

    // allocate memory for dll path in target process
    void* allocated_mem = VirtualAllocEx(h_proc, nullptr, MAX_PATH, MEM_RESERVE | MEM_COMMIT, PAGE_READWRITE);
    if (!allocated_mem) error("VirtualAllocEx", "Failed to allocate memory", h_proc, allocated_mem);
    
    // attempt to write dll path to target process
    if (!WriteProcessMemory(h_proc, allocated_mem, dll_path, MAX_PATH, nullptr)) error("WriteProcessMemory", "Failed to write process memory", h_proc, allocated_mem);


    HANDLE h_thread = CreateRemoteThread(h_proc, nullptr, NULL, LPTHREAD_START_ROUTINE(LoadLibraryA), allocated_mem, NULL, nullptr);
    if (!h_thread) error("CreateRemoteThread", "Failed to create remot thread", h_proc, allocated_mem);


    CloseHandle(h_proc);
    VirtualFreeEx(h_proc, allocated_mem, 0, MEM_RELEASE);
    // MessageBoxA(0, "Successfully injected", "Success", 0);
    std::cout << "Successfully Injected" << std::endl;


    return 0;
}
