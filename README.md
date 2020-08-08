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
- Input file format: valid characters in the input file are [a-zA-Z0-9\.,\s] where
  a-z - Lowercase alphabets.
  A-Z - Uppercase alphabets.
  \. - Period character.
  , - Comma.
  \s - Any whitespace character. Matches [\r\n\t\f\v].
- Misspelled file format: misspelled words file containing words, one per line, that 
  are misspelled.
- Keywords fie format: keywords file containing keywords, one per line.
- Order of input arguments to run command is input.txt, misspelled.txt, keywords.txt,
  output.txt and debugValue.
- input, misspelled and/or keywords can not have the same name.

2. Data structures:
- Array list is used to store the input lines. List is used as a whole sentence is 
  stored at each index so fetching that senetence and then spliting the sentence and 
  then searching the word becomes easy than storing one word at index of list.
  Due to this the time compexity achieved in O(n^2).
- Array list is also used for storing keywords and missplelled words.
- HashMap is used to store each word as key and their frequency count as value for
  finding most frequent word.

3. Code working:
- Input file is send to InputDetails where it get processed and one complete 
  sentence is stored in array list. After processing is done all decorators 
  objects are created with appropriate parameters.
- Then we call the processInputDetails method of mostFreqWordDecorator which fetch
  the stored sentence in the InputDetails class and finds the most frequent word.
  After finding the most frequent word is search that word in each sentence stored in
  list and decorate that word and update the sentence in the inputDetails class.
- Once every word that matches the most frequent word is decorated it checks if 
  another decorate is present as a parameter of the mostFreqWordDecorator and it
  invokes the processInputDetails method of that decorator.
- From mostFreqWordDecorator it goes to keywordDecorator class where is fetch the 
  updated list of sentence and read keywords from keyword.txt file and search those
  words in the sentence and decorate those words and update the sentence in the 
  inputDetails class.
- Once keywordDecorator done decorating word it checks if another decorate is present 
  as a parameter of the keywordDecorator and it invokes the processInputDetails 
  method of that decorator.
- From keywordDecorator program flows to spellCheckDecorator where it matches misspelled
  words in mispelled.txt file with the updates sentence and decorate those words and
  update the sentence in inputDetails class.
- Once done decorating spellCheckDecorator check for decoratoe as a parameter and invokes
  the processInputDetails method of that decorator.
- At last sentenceDecorator processInputDetails is called and it decorates the updated
  and update the sentence in inoutDetails class.
- Once all the decorators process the sentence it display the output on stdout and in 
  output file.

4. External Materials:
- Used regex to find special charachter in the sentence.

5. Note:
- Used my all 3 remaining slack days. 

## Academic Honesty statement:

"I have done this assignment completely on my own. I have not copied
it, nor have I given my soUsed regex to find special charachter in the sentence.Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: 08/8/2020