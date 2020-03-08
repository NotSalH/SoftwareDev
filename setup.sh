
echo $1

if [ $1 = "first" ]
then
    npm install --global gulp
    npm install --global gulp-cli
fi

if [ $1 = "packages" ] || [ $1 = "first" ]
then
    npm install
fi

if [ $1 = "db" ] || [ $1 = "first" ]
then
    gulp setupDatabase
fi

if [ $1 = "fix" ]
then
    npm run rebuild
fi

if [ $1 = "password" ]
then
    gulp hashPassword --password $2
fi

read -n 1 -s -r -p "Press any key to continue..."
