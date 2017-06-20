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
echo "ЫбЫїЃК" %cd%

for /d  %%i in  (,*) do (
	
	if %%i NEQ "target" if %%i NEQ ".java" if  %%i NEQ ".xml" (

		call:findDir %%i
		
		for /f "delims=" %%a in ('dir /b/s/a-d wu1g*.*') do (
			set "str=%%~nxa"
			ren "%%a" "vr!str:~4!"
		)
		
		set m=%%i
		set has_key=!m:wu1g=vr!

		if !m! NEQ !has_key! (
			echo "old->ЃК" %cur_dir%
			echo "new->ЃК" %cd%
			echo жиУќУћЃК!m! -ЁЗ !m:wu1g=vr!

			ren !m! !m:wu1g=vr!
		)
		
	)

)


if %cur_dir% NEQ %pa% cd ..\


ENDLOCAL
goto:eof