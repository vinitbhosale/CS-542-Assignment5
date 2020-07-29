# CSX42: Assignment 5
**Name:Vint Surendra Bhosale** 

-----------------------------------------------------------------------

Following are the commands and the instructions to run ANT on your project.


Note: build.xml is present in [textdecorators/src](./textdecorators/src/) folder.

## Instruction to clean:

```commandline
ant -buildfile textdecorators/src/build.xml clean
```

Description: It cleans up all the .class files that were generated when you
compiled your code.

## Instructions to compile:

```commandline
ant -buildfile textdecorators/src/build.xml all
```
The above command compiles your code and generates .class files inside the BUILD folder.

## Instructions to run:

```commandline
ant -buildfile textdecorators/src/build.xml run -Dinput="input.txt" -Dmisspelled="misspelled.txt" -Dkeywords="keywords.txt" -Doutput="output.txt" -Ddebug="debugValue"
```
Note: Arguments accept the absolute path of the files.


## Description:
1. Assumption:


2. Data structures:


3. Code working:



## Academic Honesty statement:

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: 