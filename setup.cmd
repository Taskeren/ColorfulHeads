@echo off
echo ####################
echo setupDecompWorkspace
echo ####################
echo.
title setupDecompWorkspace
cmd /c ".\gradlew.bat setupDecompWorkspace -DsocksProxyHost=localhost -DsocksProxyPort=2080"

echo.
echo.
echo.

echo ####################
echo Loading Eclipse
echo ####################
echo.
title eclipse
cmd /c ".\gradlew.bat eclipse -DsocksProxyHost=localhost -DsocksProxyPort=2080"
echo Done &pause>nul