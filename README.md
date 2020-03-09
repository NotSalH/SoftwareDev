# SoftwareDev
This repo is for CS461 Software Dev

# Setup

1. Make sure Node.js is installed (or install it).

2. Clone the repository.

> git clone <main repository or fork>

3. Enter newly created repository directory.

> cd SoftwareDev

4. Run first time setup (build tools, node packages, database).

> .\setup.sh first

5. Run the application.

> .\run.sh

# Additional commands

> .\setup.sh packages

Install required node packages, convenient if new dependencies are added during development. 
Also called during first time setup.

> .\setup.sh db

Setup a new database from scratch, and insert testing records.
Also called during first time setup.

# Code Highlights

* [Build Script](gulpfile.js)
* [Database Table Specification](MedicalDoctor/database.js)
* [IPC calls for database querying](main.js)
