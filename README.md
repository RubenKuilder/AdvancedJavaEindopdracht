# Stenden Dashboard
This is the stenden dashboard an API created for the use within NHL Stenden.

## Installing the Stenden Dashboard

## 1. Server Configuration
First we need to set up everything in the hosting environment. We recommend you use linux. In the example we are using 'DebianGNU/Linux 10(buster)' running in a virtual machine.

## 1.1 Updates

Before starting anything run a check for any updates.
```shell
sudo apt update
```

## 1.2 Tomcat
Tomcat is a webserver for running WAR files. (Web Application Runtimes).

### 1.2.1 Installing Java
So first we check if Java is installed. Run this command:
```shell
sudo apt install default-jdk
```

### 1.2.2 Check for Tomcat in Repository
Check for the Tomcat server package in the repository. This will show you all the latest packages available for download.
```shell
sudo apt-cache search tomcat
```

### 1.2.3 Download Tomcat
Download the tomcat9 package and the tomcat9 admin package and its dependencies with the following terminal command.
```shell
sudo apt install tomcat9 tomcat9-admin
```

### 1.2.4 Verify the install
When the download is finished, it will install the Apache Tomcat Server, which will start up automatically.
For verification, type the following ss command, which will show you the 8080 open port number, the default open port reserved for Apache Tomcat Server.
```shell
ss -ltn
```
![Port.png](additional_resources/imgCheck.png)

### 1.2.5 Test Tomcat Server
Now, you may test your Tomcat server. When Tomcat starts running, you can test the program in a web browser. 
Using the system’s loopback address, you can connect to the Tomcat server by specifying the port with the address in the URL search bar, as follows:
```shell
http://127.0.0.1:8080
```
Tomcat is up and running if you see this page, "it works!".

![it_works.png](additional_resources/imgItWorks.png)

### 1.2.6 Create User
You should create a user for the Web Application Manager in the Tomcat Server. A new user account must be set up to use the Apache Tomcat Web Application Manager.

Open the tomcat-users.xml file in the Tomcat directory with your favorite text editor.
```shell
sudo nano /etc/tomcat9/tomcat-users.xml
```

### 1.2.7 Add Tagged Lines
When the file is opened, add these three tagged lines in the file above the “<” tag. Here, new user with the name “tomcat” has been created with a password set as “pass.” Add your own values as substitution for the username and password.
```shell
<role rolename="admin-gui"/>
<role rolename="manager-gui"/>
<user username="tomcat" password="password" roles="admin-gui,manager-gui"/>
```
![img.png](additional_resources/imgUsersXml.png)
Save the file and close it. Now, we can move on to accessing the manager application area.

### 1.2.8 Restart Tomcat
Restart the Tomcat Server with the following command. This won't output anything, but it should have restarted.

```shell
sudo systemctl restart tomcat9
```

### 1.2.9 Access Tomcat Application Manager

In order to access the Tomcat server web application manager, enter the URL:  http://127.0.0.1:8080/manager/html
into the web browser URL search bar and press enter. Next, enter the credentials that you gave to the new user in Tomcat Server. 
You should now see the Web Application Manager Window.

![tomcat_manager.png](additional_resources/imgTomcat.png)

## 1.3 MySQL

### 1.3.1 Installing MySql

For our database we will be using MySQL which we can install using the following command.
```shell
sudo apt-get install mariadb-server
```

### 1.3.2 Check MySQL
Once the installation is complete, the MySQL server should be started automatically.
You can quickly check its current status via this command:
```shell
sudo service mysql status
```
![img.png](additional_resources/imgMYSQL.png)

### 1.3.3 Login with root

First time with MySQL you will need to login with ```sudo mysql -u root``` as the root user this should sign you in directly.

![img.png](additional_resources/imgRootLogin.png)
### 1.3.4 Create new user
Create a new user using the following command. (If different credentials are wanted then this has to be changed in the code)
```shell
CREATE USER 'stenden'@'localhost' IDENTIFIED BY 'St3nd3n_D@shboard';
```
![img.png](additional_resources/imgCreateUser.png)

### 1.3.5 Grant new user permissions

At this point stenden has no permissions to do anything with the databases. In fact, even if stenden tries to login (with the password, St3nd3n_D@shboard), they will not be able to reach the MySQL shell.

Therefore, the first thing to do is to provide the user with access to the information they will need.

```shell
GRANT ALL PRIVILEGES ON stenden_dashboard.* TO 'stenden'@'localhost';
```

### 1.3.6 Flush
Once you have finalized the permissions that you want to set up for your new users, always be sure to reload all the privileges.
```shell
FLUSH PRIVILEGES;
```
Your changes will now be in effect.

### 1.3.7 Quit and Log In
To test our new user, log out by typing:
```shell
quit
```
And log back in, using the password from earlier, with this command:
```shell
mysql -u stenden -p
```


### 1.3.8 Create Database
Run the command ```CREATE DATABASE stenden_dashboard;``` to create the stenden_dashboard database.

## 1.4 Deploy application

### 1.4.1 Download the .WAR
First we will download the .WAR file using the following command. Make sure you are not writing within the MySQL/MardiaDB commandline type ```quit``` if you are.

```shell
wget https://github.com/RubenKuilder/AdvancedJavaEindopdracht/raw/main/additional_resources/Stenden_Dashboard.war
```

### 1.4.2 Deploying the application
Go to http://127.0.0.1:8080/manager/html and look under the deploy tab for "WAR file to deploy". 
Click on Choose File and choose the file we just downloaded. Then hit deploy.

![deploy.png](additional_resources/imgDeploy.png)

### 1.4.3 Checking if the application deployed
After the page finishes loading you should see it have true under the running header, it should have started up automatically.
If it did not launch try hitting deploy on Stenden_Dashboard, if it errors you can look under ```/var/lib/tomcat9/logs/catalina.out``` for the logs.

![img.png](additional_resources/imgApplication.png)

test