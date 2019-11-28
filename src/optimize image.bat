@echo off
setlocal enabledelayedexpansion

echo Starting script in directory: %cd%

echo:

if not exist "fixed" (
	echo Making directory "fixed"
	mkdir fixed
) else (
	echo Updating directory "fixed"
)

if not exist "optimized" (
	echo Making directory "optimized"
	mkdir optimized
) else (
	echo Updating directory "optimized"
)

if not exist "coalesced" (
	echo Making directory "coalesced"
	mkdir coalesced
) else (
	echo Updating directory "coalesced
)

echo:

for %%i in (*) do (
	if "%%~xi"==".gif" (
		echo Optimizing %%i...
		magick "%%i" -layers optimize "%cd%\optimized\%%i"
		echo Coalescing %%i...
		magick convert -coalesce "%%i" "%cd%\coalesced\%%i"
		echo:
	)
)

for %%i in (.\optimized\*) do (
	if exist ".\coalesced\%%~nxi" (
		set "file=.\coalesced\%%~nxi"
		echo Comparing %%~nxi size in directories "optimized" and "coalesced"...
		set /a "optSize=%%~zi/1024"
		echo   Optimized version: !optSize! KB
		for %%a in ("!file!") do set /a "size=%%~za"
		set /a "coalSize=size/1024"
		echo   Coalesced version: !coalSize! KB
		if !size! lss %%~zi (
			copy /v ".\coalesced\%%~nxi" ".\fixed\" 
		) else (
			copy /v ".\optimized\%%~nxi" ".\fixed\"
		)
	) else (
		echo Matching %%i NOT FOUND in directory "coalesced"!
	)
	echo:
)

pause