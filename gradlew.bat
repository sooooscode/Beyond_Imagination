@echo off
set DEFAULT_JVM_OPTS=-Xmx512m
set GRADLE_HOME=%~dp0\gradle
set GRADLE_USER_HOME=%USERPROFILE%\.gradle
set JAVA_HOME=C:\Program Files\Java\jdk-17
set PATH=%JAVA_HOME%\bin;%GRADLE_HOME%\bin;%PATH%
set CLASSPATH=%GRADLE_HOME%\lib\gradle-launcher-7.4.2.jar

if not exist "%GRADLE_HOME%\gradle-wrapper.jar" (
    echo.
    echo ERROR: Cannot find gradle-wrapper.jar. Please ensure you have initialized the project correctly.
    exit /b 1
)

java %DEFAULT_JVM_OPTS% -classpath %CLASSPATH% org.gradle.wrapper.GradleWrapperMain %*
