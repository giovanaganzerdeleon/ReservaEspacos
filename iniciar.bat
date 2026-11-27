
@echo off
setlocal
title ReservaCampus

cd /d "%~dp0"

echo =====================================
echo       RESERVACAMPUS
echo =====================================

echo.
echo Iniciando Backend...

start "ReservaCampus - Backend" cmd /k "cd /d "%~dp0backend" && call mvnw.cmd spring-boot:run"

echo.
echo Iniciando Frontend...

start "ReservaCampus - Frontend" cmd /k "cd /d "%~dp0frontend" && py -m http.server 5500 --bind 127.0.0.1"

echo.
echo Aguarde o backend iniciar.
echo Frontend: http://127.0.0.1:5500
echo Backend:  http://127.0.0.1:8080

pause
