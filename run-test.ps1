# Run BugTest with .env configuration
# This script compiles and runs the BugTest class using environment variables from .env file

Write-Host "================================" -ForegroundColor Cyan
Write-Host "Running BugTest with .env config" -ForegroundColor Cyan
Write-Host "================================" -ForegroundColor Cyan

# Clean, compile, and run
mvn clean compile exec:java -Dexec.mainClass="com.restassured.tests.BugTest"

if ($LASTEXITCODE -eq 0) {
    Write-Host "`n✓ Test completed successfully!" -ForegroundColor Green
} else {
    Write-Host "`n✗ Test failed with exit code $LASTEXITCODE" -ForegroundColor Red
}
