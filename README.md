# SoftwareDev
This repo is for CS461 Software Dev

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
    9. Under "Windows Service", use default `MySQL80` service name, but uncheck "Start the MyDql Derver at System Startup", so
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

### Notes

* [Git Help](devnotes.md)
* [JavaFX Help](javafx.md)
* On Windows, start and stop the server using `.\startdb.cmd` and `.\stopdb.cmd`. You must run these as administrator.
