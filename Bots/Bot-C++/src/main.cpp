#include <filesystem>
#include <TlHelp32.h>
#include <Windows.h>


DWORD FindProcessId(const std::wstring& processName)
{
    PROCESSENTRY32 processInfo;
    processInfo.dwSize = sizeof(processInfo);

    HANDLE processesSnapshot = CreateToolhelp32Snapshot(TH32CS_SNAPPROCESS, NULL);
    if (processesSnapshot == INVALID_HANDLE_VALUE) {
        return 0;
    }

    Process32First(processesSnapshot, &processInfo);
    if (!processName.compare(processInfo.szExeFile))
    {
        CloseHandle(processesSnapshot);
        return processInfo.th32ProcessID;
    }

    while (Process32Next(processesSnapshot, &processInfo))
    {
        if (!processName.compare(processInfo.szExeFile))
        {
            CloseHandle(processesSnapshot);
            return processInfo.th32ProcessID;
        }
    }

    CloseHandle(processesSnapshot);
    return 0;
}


HANDLE GetProcessByImageName(const char* image_name)
{
    auto processes = GetProcessesByImageName(image_name, 1);
    return processes.size() > 0 ? processes[0] : NULL;
}




int CALLBACK WinMain(HINSTANCE hInstance, HINSTANCE hPrevInstance, LPSTR lpCmdLine, int nCmdShow)
{
    HANDLE process;

    do
    {
        Sleep(100);
        process = GetProcessByName("main.exe");
    }
    while (!process);

    printf("Found Process...\n");
    printf("Attaching...\n");



    CloseHandle(process);

    printf("\nSuccess! The injector will close in 3 seconds...\n");

    Sleep(3000);

    return 0;
}

