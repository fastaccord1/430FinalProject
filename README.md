# 430FinalProject
This is for the CS 430 Final Project. This project will run an initial install program on first run. During install
a config file will be created to store an encrypted version of the database password.

# Compiling the program
__Requirements:__
  * Oracle's JDBC jar in the /jar directory
  * database.conf file in /src/cs430/finalProject/config directory


__Compiling__
Run ant inside the base directory(where the build.xml file is). The jar will be output to out/jar/430FinalProject.jar

# Running the program
Once you have compiled the program you run it with `java -jar 430FinalProject.jar`

# Database config
The database.conf file must be in the following format:
```
[username]
[password]
```