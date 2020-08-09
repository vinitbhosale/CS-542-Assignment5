package textdecorators;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import textdecorators.util.FileProcessor;
import textdecorators.util.InputDetailsI;
import textdecorators.util.MyLogger;

/**
 * SpellCheckDecorator class extends AbstractTextDecorator abstracr class
 * implemnets mispelled.txt file processing and finds words that matches the
 * mispelled words and decorate those words with SPELLCHECK_ and _SPELLCHECK in
 * the inputFile List.
 * 
 * @author - Vinit S Bhosale.
 */
public class SpellCheckDecorator extends AbstractTextDecorator {
    // Initializing AbstractTextDecorator obj.
    private AbstractTextDecorator atd;
    // Initializing InputDetailsI obj.
    private InputDetailsI id;
    // Initializing FileProcessor obj to read misspelled text file.
    private FileProcessor spellCheckFp;
    private String strData;
    // Initializing List to store misspelled words.
    private List<String> spellCheckWords = new ArrayList<>();
    private int index = 0;

    /**
     * SpellCheckDecorator constructor.
     * 
     * @param atdIn          - AbstractTextDecorator obj.
     * @param idIn           - InputDetailsI obj.
     * @param inSpellCheckFp - FileProcessor obj.
     * 
     * @throws IOException
     */
    public SpellCheckDecorator(AbstractTextDecorator atdIn, InputDetailsI idIn, FileProcessor inSpellCheckFp)
            throws IOException {
        MyLogger.getInstnace().writeMessage("SpellCheckDecorator construtor.\n", MyLogger.DebugLevel.CONSTRUCTOR);
        atd = atdIn;
        id = idIn;
        spellCheckFp = inSpellCheckFp;
        MyLogger.getInstnace().writeMessage("SpellCheckDecorator processing misspelled file.\n",
                MyLogger.DebugLevel.CONSTRUCTOR);
        spellCheckFileProcessor(spellCheckFp);
    }
    
    /**
     * processInputDetails method that matches the words from misspelled file with
     * words in the sentence stored in the inputLines list in inputdetails class and
     * decorates those words with SPELLCHECK_ and _SPELLCHECK.
     * 
     * @throws IOException
     */
    @Override
    public void processInputDetails() throws IOException {
        // TODO Auto-generated method stub
        MyLogger.getInstnace().writeMessage("SpellCheckDecorator processInputDetails call.\n",
                MyLogger.DebugLevel.SPELLCHECKDECORATOR);
        MyLogger.getInstnace().writeMessage("SpellCheckDecorator decorating the word.\n",
                MyLogger.DebugLevel.SPELLCHECKDECORATOR);
        // Looping through each sentence from the list.
        for (String sentence : id.getInputLineList()) {
            // Splitting sentence at spaces.
            String[] wordArr = sentence.split(" ");
            // Looping through each words from wordsArr.
            for (int i = 0; i < wordArr.length; i++) {
                // Splitting words with valid format.
                String[] tempWrd = wordArr[i].split("[^a-zA-Z0-9]");
                // Looping through tempWrd array.
                for (String wrd : tempWrd) {
                    // Looping through tempWrd list.
                    for (String spellCheckWrd : spellCheckWords) {
                        // Condition to check word in sentence matches with words in misspelled file.
                        if (wrd.toLowerCase().trim().equals(spellCheckWrd.trim())) {
                            String keyWrd = "KEYWORD_";
                            String mostFreq = "MOST_FREQUENT_";
                            // Storing index of KEYWORD_.
                            int keyWrdIndex = wordArr[i].indexOf(keyWrd);
                            // Storing index of MOST_FREQUENT_.
                            int mostFreqWrdIndex = wordArr[i].indexOf(mostFreq);
                            // Storing index of word that matches the keyword.
                            int wordIndex = wordArr[i].toLowerCase().indexOf(spellCheckWrd);

                            String temp = "";
                            // Cheching if word that matches keyword contains KEYWORD_ and MOST_FREQUENT_ or
                            // just KEYWORD_ or just MOST_FREQUENT_ or nothing.
                            if (keyWrdIndex > -1 && mostFreqWrdIndex > -1) {
                                // Concating SPELLCHECK_ and _SPELLCHECK befor and after the word.
                                temp = wordArr[i].substring(0, keyWrdIndex) + "SPELLCHECK_"
                                        + wordArr[i].substring(keyWrdIndex, keyWrdIndex + keyWrd.length())
                                        + wordArr[i].substring(mostFreqWrdIndex, mostFreqWrdIndex + mostFreq.length())
                                        + wordArr[i].substring(wordIndex, wordIndex + spellCheckWrd.length())
                                        + wordArr[i].substring(wordIndex + spellCheckWrd.length(), wordIndex
                                                + spellCheckWrd.length() + mostFreq.length() + keyWrd.length())
                                        + "_SPELLCHECK";
                                // For leftover part after _SPELLCHECK
                                temp = wordIndex + spellCheckWrd.length() + keyWrd.length()
                                        + mostFreq.length() == wordArr[i].length() ? temp
                                                : temp + wordArr[i].substring(wordIndex + spellCheckWrd.length()
                                                        + keyWrd.length() + mostFreq.length());
                            } else if (mostFreqWrdIndex > -1) {
                                // Concating SPELLCHECK_ and _SPELLCHECK befor and after the word.
                                temp = wordArr[i].substring(0, mostFreqWrdIndex) + "SPELLCHECK_"
                                        + wordArr[i].substring(mostFreqWrdIndex, mostFreqWrdIndex + mostFreq.length())
                                        + wordArr[i].substring(wordIndex, wordIndex + spellCheckWrd.length())
                                        + wordArr[i].substring(wordIndex + spellCheckWrd.length(),
                                                wordIndex + spellCheckWrd.length() + mostFreq.length())
                                        + "_SPELLCHECK";
                                // For leftover part after _SPELLCHECK
                                temp = wordIndex + spellCheckWrd.length() + mostFreq.length() == wordArr[i].length()
                                        ? temp
                                        : temp + wordArr[i]
                                                .substring(wordIndex + spellCheckWrd.length() + mostFreq.length());
                            } else if (keyWrdIndex > -1) {
                                // Concating SPELLCHECK_ and _SPELLCHECK befor and after the word.
                                temp = wordArr[i].substring(0, keyWrdIndex) + "SPELLCHECK_"
                                        + wordArr[i].substring(keyWrdIndex, keyWrdIndex + keyWrd.length())
                                        + wordArr[i].substring(wordIndex, wordIndex + spellCheckWrd.length())
                                        + wordArr[i].substring(wordIndex + spellCheckWrd.length(),
                                                wordIndex + spellCheckWrd.length() + keyWrd.length())
                                        + "_SPELLCHECK";
                                // For leftover part after _SPELLCHECK
                                temp = wordIndex + spellCheckWrd.length() + keyWrd.length() == wordArr[i].length()
                                        ? temp
                                        : temp + wordArr[i]
                                                .substring(wordIndex + spellCheckWrd.length() + keyWrd.length());

                            } else {
                                // Concating SPELLCHECK_ and _SPELLCHECK befor and after the word.
                                temp = wordArr[i].substring(0, wordIndex) + "SPELLCHECK_"
                                        + wordArr[i].substring(wordIndex, wordIndex + spellCheckWrd.length())
                                        + "_SPELLCHECK";
                                // For leftover part after _SPELLCHECK
                                temp = wordIndex + spellCheckWrd.length() == wordArr[i].length() ? temp
                                        : temp + wordArr[i].substring(wordIndex + spellCheckWrd.length());

                            }
                            // Updating the word.
                            wordArr[i] = temp;
                            break;
                        }

                    }
                }

            }
            MyLogger.getInstnace().writeMessage("SpellCheckDecorator updating sentence in inputLines list.\n",
                    MyLogger.DebugLevel.SPELLCHECKDECORATOR);
            // Updating the word in the sentence at perticular index of the list stored in
            // inputDetails class.
            id.update(String.join(" ", wordArr), index);
            index += 1;
        }
        MyLogger.getInstnace().writeMessage("SpellCheckDecorator calling SentenceDecorator.\n",
                MyLogger.DebugLevel.SPELLCHECKDECORATOR);
        // Condition to check atd contains a decorator.
        if (null != atd) {
            // Calling processInputDetails of that decorator.
            atd.processInputDetails();
        }
    }

    /**
     * spellCheckFileProcessor method that process misspelled text file.
     * 
     * @param spellCheckFp - FileProcessor obj.
     * 
     * @throws IOException
     */
    public void spellCheckFileProcessor(FileProcessor spellCheckFp) throws IOException {
        strData = spellCheckFp.poll();
        // Condition to check Empty input file.
        if (null == strData) {
            // throw new EmptyInputFileException("Empty Input File!");
        }
        // Looping and storing each word from the misspelled file.
        while (null != strData) {
            spellCheckWords.add(strData.trim());
            strData = spellCheckFp.poll();
        }
    }
    @Override
    public String toString() {
        return "Class: SpellCheckDecorator, Data members: [atd=" + atd.toString() + "id=" + id.toString()
                + "spellCheckFp=" + spellCheckFp.toString() + "strData=" + strData.toString() + "spellCheckWords=" + spellCheckWords.toString() + "index=" + index + "]";

    }

}