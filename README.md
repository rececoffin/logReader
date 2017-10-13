# logReader
# Rece Coffin
# https://github.com/rececoffin/logReader.git
Requirements:
    -f [filename flag]  X
    -l [level] flag
        only prints levels >= specified X
        only accepts valid level types  X
    output correctly formatted  X
    Recognizes invalid input    X
    deletes consecutive repeated logs X
    -d flag

Included is a .jar executable file to run the program. Execute with the command:
java -jar logReader.jar -f [filename] -l [level] (optional)
Source files can also be compiled and ran

I did not use any external libraries for this solution. I experimented with a couple JSON parsing libraries
but eventually I preferred to write my own implementation. Manipulating the strings to get the desired information
turned out to be the same amount of work as reading and implementing more intricate parsing libraries.

I wrote a few unit tests to test the string manipulation of my logMessage class, otherwise I tested with a variety
of different input files, trying to apply as different cases that could break by solution. I also wrote a very simple
bash script to run the program repeatedly with a number of different arguments. Ensuring that it handled every case correctly.
I am very confident that my solution works as intended, it handles invalid arguments and input logs that do not parse.