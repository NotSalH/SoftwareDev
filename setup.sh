
echo $1

if [ $1 = "all" ] || [ $1 = "first" ]
then
    npm install --global gulp
    npm install --global gulp-cli
fi

if [ $1 = "all" ] || [ $1 = "packages" ]
then
    npm install
fi

if [ $1 = "all" ] || [ $1 = "db" ]
then
    gulp setupDatabase
fi

if [ $1 = "password" ]
then
    gulp hashPassword --password $2
fi

read -n 1 -s -r -p "Press any key to continue..."
