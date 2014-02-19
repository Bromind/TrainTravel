TrainTravel
-----------

Here is a small badly coded program to prepare travels in train. 

Tested on java 1.6 with OpenJDK.

How to use ?
------------

1. Compilation

cd /path/to/install/
git clone https://github.com/Bromind/TrainTravel.git
cd TrainTravel/
mkdir bin
javac -classpath bin/ -sourcepath src -d bin src/TrainTravel/TrainTravel.java

2. Launch

if you have moved :
cd /path/to/install/TrainTravel/bin/
java TrainTravel.TrainTravel

3. Arguments

You can pass arguments to the program to modify properties like the filename, the title, etc...
The first argument is the .tex file name, by default "Voyage" (Notice you don't need ".tex").
The second argument is the title of the travel, by default empty.
The third and fourth argument are respectively the family and first author name.

Thus you can replace the last previous command by :
java TrainTravel.TrainTravel FILENAME TRAVELNAME FAMILYNAME FIRSTNAME
