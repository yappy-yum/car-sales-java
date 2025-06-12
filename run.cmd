@echo off
echo === Setting Up Environment ===

:: !!! Below file path may differ !!!
set "JAVA_HOME=C:\Program Files\Java\jdk-22"
set "JAVAC=%JAVA_HOME%\bin\javac.exe"
set "JAVA=%JAVA_HOME%\bin\java.exe"

:: Set lib paths
set "JFX_LIB=lib\javafx-sdk-24.0.1\lib"
set "ARGON_LIBS=lib\argon2\argon2-jvm-2.8.jar;lib\argon2\jna-5.17.0.jar;lib\argon2\jna-platform-5.17.0.jar"
set "DOTENV_LIB=lib\dotenv\dotenv-java-3.2.0.jar"
set "JUNIT_JAR=lib\junit\junit-platform-console-standalone-1.13.0-RC1.jar"

:: Enable delayed expansion
setlocal enabledelayedexpansion

:: Build JavaFX classpath
set "JFX_CP="
for %%j in (%JFX_LIB%\*.jar) do (
    set "JFX_CP=!JFX_CP!;%%j"
)

:: Ask user for choice
echo.
echo What would you like to do?
echo [1] Run App (deploy)
echo [2] Run Tests
set /p choice=Enter your choice (1 or 2): 

echo.  

:: Create bin directory
echo Creating bin directory ...
if not exist bin mkdir bin

if "%choice%"=="1" (
    echo Compiling and Running App ...
    "%JAVAC%" -d bin -cp "lib\argon2\*;%DOTENV_LIB%;src" --module-path "%JFX_LIB%" --add-modules javafx.controls,javafx.fxml,javafx.media,javafx.swing src\App.java
    if %errorlevel% neq 0 (
        echo [ERROR] Compilation failed
        pause
        exit /b %errorlevel%
    )
    "%JAVA%" --module-path "%JFX_LIB%" --add-modules javafx.controls,javafx.fxml,javafx.media,javafx.swing -cp "bin;%ARGON_LIBS%;%DOTENV_LIB%" App
)

if "%choice%"=="2" (
    echo Compiling Source Files ...
    for /R src %%f in (*.java) do (
        echo Compiling %%f
        "%JAVAC%" -d bin --module-path "%JFX_LIB%" --add-modules javafx.controls,javafx.fxml,javafx.media,javafx.swing -cp "src;%ARGON_LIBS%;%DOTENV_LIB%;%JUNIT_JAR%" "%%f"
        if !errorlevel! neq 0 (
            echo [ERROR] Compilation failed at %%f
            pause
            exit /b !errorlevel!
        )
    )

    for %%h in (test\framework\*.java) do (
        echo Compiling %%h
        "%JAVAC%" -d bin --module-path "%JFX_LIB%" --add-modules javafx.controls,javafx.fxml,javafx.media,javafx.swing -cp "bin;%ARGON_LIBS%;%DOTENV_LIB%;%JUNIT_JAR%" "%%h"
        if !errorlevel! neq 0 (
            echo [ERROR] Compilation failed at %%h
            pause
            exit /b !errorlevel!
        )
    )

    "%JAVAC%" -d bin --module-path "%JFX_LIB%" --add-modules javafx.controls,javafx.fxml,javafx.media,javafx.swing -cp "bin;%ARGON_LIBS%;%DOTENV_LIB%;%JUNIT_JAR%" test\loginTest.java test\AgeIncTest.java
    if !errorlevel! neq 0 (
        echo [ERROR] Test compilation failed
        pause
        exit /b !errorlevel!
    )

    echo Running Tests ...
    "%JAVA%" --module-path "%JFX_LIB%" --add-modules javafx.controls,javafx.fxml,javafx.media,javafx.swing -jar %JUNIT_JAR% --class-path "bin;%ARGON_LIBS%;%DOTENV_LIB%;%JUNIT_JAR%" --scan-classpath
)

echo.
pause
