@echo off
REM Run BugTest with .env configuration
REM This script compiles and runs the BugTest class using environment variables from .env file

echo.
echo ================================
echo Running BugTest with .env config
echo ================================
echo.

REM Clean, compile, and run
mvn clean compile exec:java -Dexec.mainClass="com.restassured.tests.BugTest"

if %ERRORLEVEL% equ 0 (
    echo.
    echo Test completed successfully!
) else (
    echo.
    echo Test failed with exit code %ERRORLEVEL%
)

pause
