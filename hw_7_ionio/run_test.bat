call cd ..\csv_converter
call mvn clean install
call cd ..\hw_7_ionio
call mvn clean test -Ptest