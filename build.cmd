@echo off
echo ####################
echo Build
echo ####################
echo.
title build
./gradlew build -DsocksProxyHost=localhost -DsocksProxyPort=2080