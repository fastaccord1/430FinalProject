# 430FinalProject
This is for the CS 430 Final Project. This program pulls database username/password from a configuration file. You must include the ojdbc7.jar file in a jar directory

# Compiling the program
__Requirements:__
  * Oracle's JDBC jar in the /jar directory
  * database.conf file in /src/cs430/finalProject/config directory


__Compiling:__

Run `ant` inside the base directory(where the build.xml file is). The jar will be output to out/jar/430FinalProject.jar

# Running the program
Once you have compiled the program you run it with `java -jar 430FinalProject.jar`

__Selecting your Role:__

Once you run your program, you will be prompted to enter your ID. Depending on your role, you will see a window. 

## Setting up Database
Run the CreateDatabase.sql and CreateEntries.sql files to create the tables and add
initial data.

# Database config
The database.conf file must be in the following format:
```
username: [username]
password: [password]
```
This file should be placed in src/cs430/finalProject/config/ in order for the project to work. A blank version of this file is included in the source code.