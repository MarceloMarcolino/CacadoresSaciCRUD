@echo off
echo ===================================
echo  Caçadores de Saci - Compilar
echo ===================================
echo.

cd /d "%~dp0"

echo Verificando Java...
java -version
if errorlevel 1 (
    echo ERRO: Java nao encontrado!
    echo Instale o JDK Java 8 ou superior
    pause
    exit /b 1
)

echo.
echo Verificando JDK...
javac -version
if errorlevel 1 (
    echo ERRO: JDK nao encontrado!
    echo Apenas JRE detectado. Instale o JDK completo.
    echo.
    echo Download: https://www.oracle.com/java/technologies/downloads/
    pause
    exit /b 1
)

echo.
echo Compilando projeto...
javac -cp . src/main/Main.java src/model/*.java src/dao/*.java src/service/*.java src/view/*.java

if errorlevel 1 (
    echo.
    echo ERRO na compilacao!
    pause
    exit /b 1
) else (
    echo.
    echo ✓ Compilacao realizada com sucesso!
    echo.
    echo Para executar:
    echo   executar.bat
    echo.
    pause
)
