# Database Demo

## To Do List
- Can be more than one user with same user name... how to solve
- In current data model... we are creating education before resume...
- Try creating education at the same time resume is created... 
- Service layer to delegate the business logic to another class method...
- Add experience?
- delete api
- logging
- exception handling
- application.yml - hardcoded password?
- authentication/authorization?
- no junits are there... add junits
- asynchronous calls
- reactive... 
- using virtual threads

## Prerequisites and Setup Instructions

### For MacOS

1. **Install Homebrew** (if not already installed)   ```bash
   /bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"   ```

2. **Install/Update Java**   ```bash
   brew install openjdk@17   ```
   After installation, link it:   ```bash
   sudo ln -sfn $(brew --prefix)/opt/openjdk@17/libexec/openjdk.jdk /Library/Java/JavaVirtualMachines/openjdk-17.jdk   ```

3. **Install MySQL**   ```bash
   brew install mysql   ```
   Start MySQL service:   ```bash
   brew services start mysql   ```

4. **Install Maven**   ```bash
   brew install maven   ```

### Verify Installations

```bash
# Verify Java version
java -version # Should show Java 17
# Verify Maven
mvn -version
# Verify MySQL
mysql --version
```

### Database Setup
```bash
# Create database
mysql -u root -p
# When prompted for password, just press Enter if you haven't set one yet

# In mysql prompt
CREATE DATABASE mydb;
exit;
```

### Running the application
Make sure the `application.yml` password matches your mysql password. Eg. if you don't have a password, can comment out the `password:`  line.

```bash
# Run using Maven wrapper
chmod +x mvnw
./mvnw spring-boot:run
# Or using Maven directly
mvn spring-boot:run
```

## Testing application with sample data
### 1. First, create some Education entries
POST http://localhost:8080/education
```json
{
    "id": 1,
    "collegeName": "MIT",
    "graduationYear": 2022,
    "gpa": 3.8
}
```
POST http://localhost:8080/education
```json
{
    "id": 2,
    "collegeName": "Stanford University",
    "graduationYear": 2023,
    "gpa": 3.9
}
```
POST http://localhost:8080/education
```json
{
    "id": 3,
    "collegeName": "Harvard University",
    "graduationYear": 2021,
    "gpa": 3.7
}
```
### 2. Then create Resumes that reference these Education entries
POST http://localhost:8080/resume
```json
{
    "userId": "user123",
    "name": "John Doe",
    "email": "john.doe@example.com",
    "contactNumber": 1234567890,
    "education": [
        {
            "id": 1
        },
        {
            "id": 2
        }
    ]
}
```
POST http://localhost:8080/resume
```json
{
    "userId": "user456",
    "name": "Jane Smith",
    "email": "jane.smith@example.com",
    "contactNumber": 9876543210,
    "education": [
        {
            "id": 3
        }
    ]
}
```

### 3. Verify the data with GET requests
Get all resumes:
`GET http://localhost:8080/resume`

Get all education entries:
`GET http://localhost:8080/education`

Get a specific resume:
`GET http://localhost:8080/resume/user123`

Get education entries for a specific resume:
`GET http://localhost:8080/resume/user123/education`
