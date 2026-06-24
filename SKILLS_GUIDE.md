# RestAssured Framework - Skills & Setup Guide

## 📋 Project Overview
This is a **RestAssured Testing Framework** for API automation using Java, Maven, and REST Assured library.

---

## 📁 Project Structure

```
restassured_rs-main/
├── src/
│   ├── (default package)/          # Default package for classes
│   │   ├── Basics.java              # POST, PUT, GET API calls
│   │   ├── ComplexJsonParsing.java  # JSON parsing from array responses
│   │   ├── Payload.java             # Reusable test data/payloads
│   │   ├── ReUsableMethods.java     # Helper utility methods
│   │   └── files/                   # Additional files
│   └── main/java/
│       └── com/basics/
│           └── Basics.java          # Standard package structure
├── target/                          # Compiled bytecode (auto-generated)
├── pom.xml                          # Maven configuration & dependencies
└── .idea/                           # IntelliJ settings
```

---

## ⚙️ Key Dependencies (pom.xml)

| Dependency | Version | Purpose |
|-----------|---------|---------|
| **Rest Assured** | 6.0.0 | API testing library |
| **TestNG** | 7.10.2 | Test framework |
| **Hamcrest** | 3.0 | Assertion matcher library |
| **JUnit** | 4.13.2 | Unit testing |

---

## 🔧 Important Configuration

### Java Version
- **Source:** Java 21
- **Target:** Java 21
- **Encoding:** UTF-8

### Maven Settings (pom.xml)
```xml
<maven.compiler.source>21</maven.compiler.source>
<maven.compiler.target>21</maven.compiler.target>
```

---

## 📚 Core Classes Explained

### 1️⃣ **Basics.java** (src/(default package)/)
**Purpose:** Demonstrates API CRUD operations

**Methods:**
- POST API: Add new place using `given().post()`
- PUT API: Update place using `given().put()`
- GET API: Retrieve place using `given().get()`
- Extract response using `.extract().asString()`
- Parse JSON using `JsonPath`

**Key Methods:**
```java
JsonPath jsonPath = new JsonPath(response);
String placeId = jsonPath.getString("place_id");
```

---

### 2️⃣ **ComplexJsonParsing.java**
**Purpose:** Handle complex JSON array parsing

**Problem it solves:**
- Parsing arrays in JSON responses
- Iterating through array elements
- Extracting nested data

**Key Pattern:**
```java
List<Map<String, Object>> courses = jsonPath.getList("courses");
String title = jsonPath.getString("courses[0].title");
int price = jsonPath.getInt("courses[0].price");
```

---

### 3️⃣ **Payload.java**
**Purpose:** Store reusable request payloads

**Usage:**
```java
String payload = Payload.addPlacePayload();
given().body(payload).when().post(endpoint)
```

---

### 4️⃣ **ReUsableMethods.java**
**Purpose:** Utility helper methods for common operations

**Example:**
```java
JsonPath jsonPath = ReUsableMethods.rawStringToJsonPath(response);
String value = jsonPath.getString("key");
```

---

## 🚀 How to Run Code

### Option 1: Run in IntelliJ IDE
1. **Click the Play button** (▶️) next to the class name
2. Main class should be set to `com.basics.Basics` or `ComplexJsonParsing`
3. Check **Run → Edit Configurations** if it fails

### Option 2: Run via Maven
```bash
mvn clean compile      # Compile the code
mvn exec:java -Dexec.mainClass="com.basics.Basics"  # Run specific class
```

### Option 3: Run from Command Line
```bash
cd d:\automation projects\rest assured rs\restassured_rs-main
mvn clean compile
java -cp target/classes:path/to/dependencies com.basics.Basics
```

---

## 🔍 Common Issues & Fixes

| Issue | Cause | Solution |
|-------|-------|----------|
| **ClassNotFoundException: Basics** | Main class not with package name | Change to `com.basics.Basics` in run config |
| **cannot find symbol variable Payload** | Payload.java missing or not compiled | Create Payload.java in same package |
| **NumberFormatException in JSON parsing** | Trying to get array as int | Use `getList()` instead of `getInt()` |
| **Build failed with 1 error** | Missing dependencies or syntax error | Run `mvn clean compile` to see full error |
| **Project compiles but run configuration fails** | Stale run config | Delete run config and recreate it |

---

## 📝 How to Add New Test Class

### Step 1: Create Java File
```bash
src/(default package)/MyTest.java
```

### Step 2: Add Package Declaration (if not in default package)
```java
package com.basics;
```

### Step 3: Add Imports
```java
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.given;
```

### Step 4: Create Main Method
```java
public class MyTest {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://api.example.com";
        
        given()
            .header("Content-Type", "application/json")
            .body("{}")
        .when()
            .post("/endpoint")
        .then()
            .statusCode(200);
    }
}
```

### Step 5: Rebuild & Run
```bash
mvn clean compile
# Then click Play button in IntelliJ
```

---

## 🎯 REST Assured API Cheat Sheet

### Basic Request Structure
```java
given()                              // Setup
    .header("key", "value")
    .queryParam("param", "value")
    .body(payload)
.when()                             // Execute
    .post("/endpoint")
    .get("/endpoint")
    .put("/endpoint")
    .delete("/endpoint")
.then()                             // Assert
    .statusCode(200)
    .body("key", equalTo("value"));
```

### Response Extraction
```java
String response = given()...then().extract().response().asString();
JsonPath json = given()...then().extract().jsonPath();
String value = given()...then().extract().path("key");
```

### JSON Parsing
```java
JsonPath json = new JsonPath(responseString);
json.getString("path.to.value");
json.getInt("path.to.int");
json.getList("arrayPath");
json.getMap("objectPath");
```

---

## ✅ Best Practices

1. **Use Payloads Class** - Store all request bodies in `Payload.java`
2. **Use Helper Methods** - Create reusable methods in `ReUsableMethods.java`
3. **Consistent Assertions** - Use Hamcrest matchers: `equalTo()`, `containsString()`, etc.
4. **Logging** - Use `.log().all()` for debugging API requests
5. **Extract Properly** - Use `.extract()` to get response data for further validation
6. **Handle Responses** - Always check response status before parsing body

---

## 🛠️ Troubleshooting Checklist

Before reporting an issue:

- [ ] Run `mvn clean compile` successfully?
- [ ] All imports are correct?
- [ ] Main class name matches run configuration?
- [ ] Dependencies are downloaded (check `~/.m2/repository/`)?
- [ ] Check if parent class/utility exists?
- [ ] JSON path query is correct?
- [ ] API endpoint is reachable?

---

## 📖 Useful Links

- [RestAssured Documentation](http://rest-assured.io/)
- [JsonPath Syntax](https://github.com/jayway/JsonPath)
- [Hamcrest Matchers](https://hamcrest.org/JavaHamcrest/javadoc/)
- [Maven Documentation](https://maven.apache.org/)

---

**Last Updated:** 2026-06-24
**Framework Version:** RestAssured 6.0.0 | Java 21 | Maven 3.x
