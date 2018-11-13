@echo off
if exist "./Central.jar" goto programaEncontrado
echo Nao foi possivel encontrar o programa especificado
pause
goto fim

:programaEncontrado

java -jar ./Central.jar

:fim
