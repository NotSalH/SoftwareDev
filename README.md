# SoftwareDev
CS460 Software Development: MedicalDoctor.

## Running & Testing Instructions

1. Install Netbeans IDE and the [Gradle Plugin For Netbeans](http://plugins.netbeans.org/plugin/44510/gradle-support).
    1. Download the plugin file, latest version should work.
    2. In Netbeans, go "Tools" -> "Plugins" -> "Downloaded" -> "Add Plugins"
    3. Choose the download file, and accept whatever pops up.
    4. Click "Install".

2. Download this repository.

3. Install MySQL with the following configuration:
* Running locally with TCP/IP port `3306`.
* Root login with password `hydrogiraffe`.
* A new schema named `MedicalDoctor`.
* Run the following query in the `MedicalDoctor` database:

> ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'hydrogiraffe';

* Note: if changes to this configuration are needed, update [this configuration file](/src/main/resources/hibernate.cfg.xml).

4. Run `medicaldoctor.setup.InitTestDatabase`. This will create all the necessary database tables, 
and insert example data for testing.

5. Run `medicaldoctor.Program` to run the application.

6. Login information for test users are included [here](logins.md).

## Additional Details & Development Instructions

### Requirements

* [Netbeans (11.2)](https://netbeans.apache.org/download/nb112/nb112.html).

* [Gradle Plugin For Netbeans](http://plugins.netbeans.org/plugin/44510/gradle-support).
    1. Download the plugin file, latest version should work.
    2. In Netbeans, go "Tools" -> "Plugins" -> "Downloaded" -> "Add Plugins"
    3. Choose the download file, and accept whatever pops up.
    4. Click "Install".

* [JavaFX SceneBuilder](https://www.oracle.com/java/technologies/javafxscenebuilder-1x-archive-downloads.html).
You will need an oracle account.

* [MySQL](https://dev.mysql.com/downloads/). If you choose "MySQL Installer for Windows", the steps are below, else
make sure you follow the key configuration changes on any other setup.
    1. Download and run the installer.
    2. Choose "Developer Default", this ensures you have everything you need. You can instead choose 
    a custom installation. The most important ones are "MySQL Server" and "MySQL WorkBench". The rest of these
    steps assume the "Default". Next.
    3. Under "Check Requirements", click "Execute" and agree and install to whatever pops up. Next.
    4. Under "Installation", click "Execute". Next.
    5. Under "High Availability", use default "Standalone MySQL server". Next.
    6. Under "Type and Networking", use defaults, should be TCP/IP Port `3306`. Next.
    7. Under "Authentication Method", use default "Strong Password Encryption". Next.
    8. Under "Accounts and Roles", use root password `hydrogiraffe`. It should't matter that it is a weak password, 
    this is just for class, not production. Next.
    9. Under "Windows Service", use default `MySQL80` service name, but uncheck "Start the MySql Server at System Startup", so
    it is not using resources outside of this class's development. Next.
    10. Under "Apply Configuration", click "Execute". 
    11. Click "Finish".
    12. Under "MySQL Router Configuation", leave defaults and click "finish". Next.
    13. Under "Connect To Server", enter the root password. Next.
    14. Under "Apply Configuration", click "Execute".
    15. Click "Finish".
    16. After installation is complete, open the MySQL Workbench.
    17. After "MySQL Connections" click the "(+)"
    18. Enter `MedicalDoctor` as the connection name.
    19. Click password "Store in Vault" and enter the password and click "OK".
    20. Click "Create a new schema in the connected server", enter "MedicalDoctor" as the name, and click "Apply".
    21. Connect to the connection, and run the following query: 
    
> ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'hydrogiraffe';   

### Notes

* [Logins](logins.md)
* [Git Help](devnotes.md)
* [JavaFX Help](javafx.md)
* On Windows, start and stop the server using `.\startdb.cmd` and `.\stopdb.cmd`. You must run these as administrator.
