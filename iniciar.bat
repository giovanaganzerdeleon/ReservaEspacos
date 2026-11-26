@echo off
setlocal
title ReservaCampus - Back-end

pushd "%~dp0backend"
if errorlevel 1 (
    echo Nao foi possivel acessar a pasta backend do projeto.
    pause
    exit /b 1
)

if not exist "mvnw.cmd" (
    echo O Maven Wrapper nao foi encontrado em backend.
    popd
    pause
    exit /b 1git add iniciar.bat
)

echo Iniciando o ReservaCampus...
echo Mantenha esta janela aberta enquanto estiver usando a aplicacao.
echo Aguarde a mensagem "ReservaCampus esta pronto" antes de abrir o navegador.
echo.
call mvnw.cmd spring-boot:run
set "APP_EXIT_CODE=%ERRORLEVEL%"

popd
if not "%APP_EXIT_CODE%"=="0" (
    echo.
    echo A aplicacao foi encerrada com erro. Copie a mensagem acima para diagnostico.
    pause
)

exit /b %APP_EXIT_CODE%
