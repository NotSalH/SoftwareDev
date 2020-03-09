# SoftwareDev
This repo is for CS461 Software Dev

# Setup

1. Make sure Node.js is installed (or install it).

2. Clone the repository.

> git clone <main repository or fork>

3. Enter newly created repository directory.

> cd SoftwareDev

4. Run `.\setup.cmd` on Windows, else `.\setup.sh` on Mac and Linux.

> .\setup.cmd

> .\setup.sh

5. Run the application.

> npm start

6. If after running, you encounter an error that says something about "sqlite". Resolve by:

> npm install windows-build-tools --global

> npm run rebuild

# Additional commands

> gulp setupPackages

Install required node packages, convenient if new dependencies are added during development. 
Also called during first time setup.

> gulp setupDatabase

Setup a new database from scratch, and insert testing records.
Also called during first time setup.

# Code Highlights

* [Build Script](gulpfile.js)
* [Database Table Specification](MedicalDoctor/database.js)
* [IPC calls for database querying](main.js)
* [Test User Login Information](data/_notes.txt)
