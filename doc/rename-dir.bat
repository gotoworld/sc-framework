@echo off
setlocal enabledelayedexpansion

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
		
		for /f "delims=" %%a in ('dir /b/s/a-d vr*.*') do (
			set "str=%%~nxa"
			ren "%%a" "hsd!str:~2!"
		)
		
		set m=%%i
		set has_key=!m:vr=hsd!

		if !m! NEQ !has_key! (
			echo "old->£º" %cur_dir%
			echo "new->£º" %cd%
			echo ÖØÃüÃû£º!m! -¡· !m:vr=hsd!

			ren !m! !m:vr=hsd!
		)
		
	)

)


if %cur_dir% NEQ %pa% cd ..\


ENDLOCAL
goto:eof