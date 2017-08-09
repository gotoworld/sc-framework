@echo off
setlocal enabledelayedexpansion


set oldDirName=staff
set oldDirLen=5
set newDirName=channel

set pa=%cd%
call:findDir %pa%
echo.&pause&goto:eof
echo on
:findDir
SETLOCAL
set cur_dir=%1
cd %cur_dir% 
echo "ËÑË÷£º" %cd%
for /d  %%i in  (,*) do (
	if %%i NEQ "target" if %%i NEQ ".java" if  %%i NEQ ".xml" (
		call:findDir %%i
		for /f "delims=" %%a in ('dir /b/s/a-d %oldDirName%*.*') do (
			set "str=%%~nxa"
			ren "%%a" "%newDirName%!str:~%oldDirLen%!"
		)
		set m=%%i
		set has_key=!m:%oldDirName%=hsd!
		if !m! NEQ !has_key! (
			echo "old->£º" %cur_dir%
			echo "new->£º" %cd%
			echo ÖØÃüÃû£º!m! -¡· !m:%oldDirName%=%newDirName%!
			ren !m! !m:%oldDirName%=%newDirName%!
		)
	)
)
if %cur_dir% NEQ %pa% cd ..\
ENDLOCAL
goto:eof