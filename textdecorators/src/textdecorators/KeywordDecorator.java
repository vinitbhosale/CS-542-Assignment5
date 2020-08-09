package textdecorators;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import textdecorators.userException.EmptyInputFileException;
import textdecorators.util.FileProcessor;
import textdecorators.util.InputDetailsI;
import textdecorators.util.MyLogger;

/**
 * KeywordDecorator class extends AbstractTextDecorator abstracr class
 * implements keyword.txt file processing and finds words that matches the
 * keywords for the keyword.txt file and decorate those words with KEYWORD_ and
 * _KEYWORD in the inputFile List.
 * 
 * @author - Vinit S Bhosale.
 */
public class KeywordDecorator extends AbstractTextDecorator {
    // Initializing AbstractTextDecorator obj.
    private AbstractTextDecorator atd;
    // Initializing InputDetailsI obj.
    private InputDetailsI id;
    // Initializing FileProcessor obj to read keyword text file.
    private FileProcessor keyWordFp;
    private String strData;
    // Initializing List to store keywords.
    private List<String> keyWords = new ArrayList<>();
    private int index = 0;

    /**
     * KeywordDecorator constructor.
     * 
     * @param atdIn       - AbstractTextDecorator obj.
     * @param idIn        - InputDetailsI obj.
     * @param inKeyWordFp - FileProcessor obj.
     * 
     * @throws IOException
     * @throws EmptyInputFileException
     */
    public KeywordDecorator(AbstractTextDecorator atdIn, InputDetailsI idIn, FileProcessor inKeyWordFp)
            throws IOException, EmptyInputFileException {
        MyLogger.getInstnace().writeMessage("KeywordDecorator construtor."+"\n", MyLogger.DebugLevel.CONSTRUCTOR);
        atd = atdIn;
        id = idIn;
        keyWordFp = inKeyWordFp;
        MyLogger.getInstnace().writeMessage("KeywordDecorator processing input file."+"\n", MyLogger.DebugLevel.CONSTRUCTOR);
        // Call to keywordFileProcessor method to process keyword text file.
        keywordFileProcessor(keyWordFp);
    }

    /**
     * processInputDetails method that matches the keywords from keyword file with
     * words in the sentence stored in the inputLines list in inputdetails class and
     * decorates those words with KEYWORD_ and _KEYWORD.
     * 
     * @throws IOException
     */
    @Override
    public void processInputDetails() throws IOException {
        // TODO Auto-generated method stub
        MyLogger.getInstnace().writeMessage("KeywordDecorator processInputDetails call."+"\n",
                MyLogger.DebugLevel.KEYWORDDECORATOR);
        MyLogger.getInstnace().writeMessage("KeywordDecorator decorating the word."+"\n",
                MyLogger.DebugLevel.KEYWORDDECORATOR);
        // Looping through each sentence from the list.
        for (String sentence : id.getInputLineList()) {
            // Splitting sentence at spaces.
            String[] wordsArr = sentence.split(" ");
            // Looping through each words from wordsArr.
            for (int i = 0; i < wordsArr.length; i++) {
                // Splitting words with valid format.
                String[] tempWrd = wordsArr[i].split("[^a-zA-Z0-9]");
                // Looping through tempWrd array.
                for (String wrd : tempWrd) {
                    // Looping through keyWords list.
                    for (String keyWrd : keyWords) {
                        // Condition to check word in sentence matches with words in keyword file.
                        if (wrd.toLowerCase().trim().equals(keyWrd.trim())) {
                            String mostFreq = "MOST_FREQUENT_";
                            // Storing index of MOST_FREQUENT_.
                            int mostFreqWrdIndex = wordsArr[i].indexOf(mostFreq);
                            // Storing index of word that matches the keyword.
                            int wordIndex = wordsArr[i].toLowerCase().indexOf(keyWrd);
                            String temp = "";
                            // Cheching if word that matches keyword contains MOST_FREQUENT_.
                            if (mostFreqWrdIndex > -1) {
                                // Concating KEYWORD_ and _KEYWORD befor and after the word.
                                temp = wordsArr[i].substring(0, mostFreqWrdIndex) + "KEYWORD_"
                                        + wordsArr[i].substring(mostFreqWrdIndex, mostFreqWrdIndex + mostFreq.length())
                                        + wordsArr[i].substring(wordIndex, wordIndex + keyWrd.length())
                                        + wordsArr[i].substring(wordIndex + keyWrd.length(),
                                                wordIndex + keyWrd.length() + mostFreq.length())
                                        + "_KEYWORD";
                                //
                                temp = wordIndex + keyWrd.length() + mostFreq.length() == wordsArr[i].length() ? temp
                                        : temp + wordsArr[i].substring(wordIndex + keyWrd.length() + mostFreq.length());
                            } else {
                                // Concating KEYWORD_ and _KEYWORD befor and after the word.
                                temp = wordsArr[i].substring(0, wordIndex) + "KEYWORD_"
                                        + wordsArr[i].substring(wordIndex, wordIndex + keyWrd.length()) + "_KEYWORD";
                                //
                                temp = wordIndex + keyWrd.length() == wordsArr[i].length() ? temp
                                        : temp + wordsArr[i].substring(wordIndex + keyWrd.length());
                            }
                            // Updating the word.
                            wordsArr[i] = temp;
                            break;
                        }
                    }

                }

            }
            MyLogger.getInstnace().writeMessage("KeywordDecorator updating sentence in inputLines list."+"\n",
                    MyLogger.DebugLevel.KEYWORDDECORATOR);
            // Updating the word in the sentence at perticular index of the list stored in
            // inputDetails class.
            id.update(String.join(" ", wordsArr), index);
            index += 1;
        }
        MyLogger.getInstnace().writeMessage("KeywordDecorator calling SpellCheckDecorator."+"\n",
                MyLogger.DebugLevel.KEYWORDDECORATOR);
        // Condition to check atd contains a decorator.
        if (null != atd) {
            // Calling processInputDetails of that decorator.
            atd.processInputDetails();
        }
    }

    /**
     * keywordFileProcessor method that process keyword text file.
     * 
     * @param KeyWordFp - FileProcessor obj.
     * 
     * @throws IOException
     * @throws EmptyInputFileException
     */
    public void keywordFileProcessor(FileProcessor KeyWordFp) throws IOException, EmptyInputFileException {
        strData = KeyWordFp.poll();
        // Condition to check Empty input file.
        if (null == strData) {
            throw new EmptyInputFileException("Empty Input File!");
        }
        // Looping and storing each word from the keyword file.
        while (null != strData) {
            keyWords.add(strData.trim());
            strData = KeyWordFp.poll();
        }
    }

    @Override
    public String toString() {
        return "Class: KeywordDecorator, Data members: [atd=" + atd.toString() + "id=" + id.toString() + "keyWordFp="
                + keyWordFp.toString() + "strData=" + strData.toString() + "keyWords=" + keyWords.toString() + "index="
                + index + "]";

    }

}