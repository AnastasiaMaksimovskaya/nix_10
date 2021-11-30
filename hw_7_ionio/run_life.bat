call cd ..\csv_converter
call mvn clean install
call cd ..\hw_7_ionio
call mvn clean install -Plife -DskipTests
call java -jar .\target\hw_7_ionio.jar