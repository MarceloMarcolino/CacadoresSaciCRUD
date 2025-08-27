@echo off
echo ===================================
echo  Caçadores de Saci - Executar
echo ===================================
echo.

cd /d "%~dp0"

echo Verificando Java...
java -version
if errorlevel 1 (
    echo ERRO: Java nao encontrado!
    pause
    exit /b 1
)

echo.
echo Executando aplicacao...
echo.
java -cp src main.Main

if errorlevel 1 (
    echo.
    echo ERRO na execucao!
    pause
)
