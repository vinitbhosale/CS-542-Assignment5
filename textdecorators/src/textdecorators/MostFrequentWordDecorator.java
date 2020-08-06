package textdecorators;

import java.util.HashMap;
import java.util.Map;

import textdecorators.util.InputDetailsI;
import textdecorators.util.MyLogger;

/**
 * MostFrequentWordDecorator class extends AbstractTextDecorator abstracr class
 * implements finding most frequent word and concating MOST_FREQUENT_ and
 * _MOST_FREQUENT to that word and also check if there's a call to next
 * decorator.
 * 
 * @author - Vinit S Bhosale
 */
public class MostFrequentWordDecorator extends AbstractTextDecorator {
    // Initializing AbstractTextDecorator obj.
    private AbstractTextDecorator atd;
    // Initializing InputDetailsI obj.
    private InputDetailsI id;
    // HashMap to store word and its frequency.
    private Map<String, Integer> freqWordsMap = new HashMap<String, Integer>();
    // Default word count for each word.
    private int counter = 1;
    private int oldValue;
    private int value = 0;
    private String mostFreqWrdkey = "";
    int index = 0;

    /**
     * MostFrequentWordDecorator constructor.
     * 
     * @param atdIn - AbstractTextDecorator obj.
     * @param idIn  - InputDetailsI obj.
     */
    public MostFrequentWordDecorator(AbstractTextDecorator atdIn, InputDetailsI idIn) {
        MyLogger.getInstnace().writeMessage("MostFrequentWordDecorator constructor.", MyLogger.DebugLevel.CONSTRUCTOR);
        atd = atdIn;
        id = idIn;
    }

    /**
     * processInputDetails method that implments finding most frequent word from the
     * input file and decorating that word with MOST_FREQUENT_ and _MOST_FREQUENT
     * before and after the word.
     */
    @Override
    public void processInputDetails() {
        // TODO Auto-generated method stub
        MyLogger.getInstnace().writeMessage("MostFrequentWordDecorator processInputDetails call.",
                MyLogger.DebugLevel.MOSTFREQUENTWORDDECORATOR);
        MyLogger.getInstnace().writeMessage("MostFrequentWordDecorator calculating frequency of each word.",
                MyLogger.DebugLevel.MOSTFREQUENTWORDDECORATOR);
        // Calculating wordFreqCounter for each word.
        freqWordsMap = wordFreqCounter();
        MyLogger.getInstnace().writeMessage("MostFrequentWordDecorator fetching the most frequent word.",
                MyLogger.DebugLevel.MOSTFREQUENTWORDDECORATOR);
        // Finding the most frequent word.
        mostFreqWrdkey = mostFrequentWord();

        MyLogger.getInstnace().writeMessage("MostFrequentWordDecorator decorating the word.",
                MyLogger.DebugLevel.MOSTFREQUENTWORDDECORATOR);
        // Looping through each sentence from the list.
        for (String sentence : id.getInputLineList()) {
            // Splitting sentence at spaces.
            String[] wrd = sentence.split(" ");
            // Looping through each words of the sentence.
            for (int i = 0; i < wrd.length; i++) {
                // Cinverting word to lower case.
                String tempWord = wrd[i].trim().toLowerCase();
                // Comparing word to the most frequent word.
                if (mostFreqWrdkey.equals(tempWord)) {
                    // Getting the index of most frequent word in word of the sentence.
                    int wordIndex = wrd[i].toLowerCase().indexOf(mostFreqWrdkey);
                    // Concating MOST_FREQUENT_ and _MOST_FREQUENT befor and after the word.
                    String temp = wrd[i].substring(0, wordIndex) + "MOST_FREQUENT_"
                            + wrd[i].substring(wordIndex, wordIndex + mostFreqWrdkey.length()) + "_MOST_FREQUENT";
                    //
                    temp = wordIndex + mostFreqWrdkey.length() == wrd[i].length() ? temp
                            : temp + wrd[i].substring(wordIndex + mostFreqWrdkey.length());
                    // Updating the word.
                    wrd[i] = temp;
                }
            }
            MyLogger.getInstnace().writeMessage("MostFrequentWordDecorator updating sentence in inputLines list.",
                    MyLogger.DebugLevel.MOSTFREQUENTWORDDECORATOR);
            // Updating the word in the sentence at perticular index of the list stored in
            // inputDetails class.
            id.update(String.join(" ", wrd), index);
            index += 1;
        }
        // Condition to check atd contains a decorator.
        if (null != atd) {
            MyLogger.getInstnace().writeMessage("MostFrequentWordDecorator calling KeyWordDecorator.",
                    MyLogger.DebugLevel.MOSTFREQUENTWORDDECORATOR);
            // Calling processInputDetails of that decorator.
            atd.processInputDetails();
        }
    }

    /**
     * wordFreqCounter method that implements counting of each word frequency.
     * 
     * @return freqWordsMap - Hash map with word as key and frequency as value.
     */
    public Map<String, Integer> wordFreqCounter() {
        // Looping through each sentence from the list.
        for (String sentance : id.getInputLineList()) {
            // Splitting sentence at spaces
            String[] word = sentance.toLowerCase().split(" ");
            // Looping through each words of the sentence.
            for (int i = 0; i < word.length; i++) {
                // Condition to chech whether word is space or not.
                if (!word[i].trim().equals("")) {
                    // Condition to check whether word is already present in HasMap.
                    if (freqWordsMap.containsKey(word[i].trim())) {
                        // Fetch that words old frequency value.
                        oldValue = freqWordsMap.get(word[i].trim());
                        // Replace old frequency value with new.
                        freqWordsMap.replace(word[i].trim(), oldValue, oldValue + 1);
                    } else {
                        // Trim that word at spaces and other special charachters.
                        String[] temWrdArr = word[i].trim().split("\\W+");
                        // Condition to check if its not a period.
                        if (!word[i].equals(".")) {
                            // Store the word in HashMap with its frequency count.
                            freqWordsMap.put(temWrdArr[0].trim(), counter);
                        }

                    }
                }
            }
        }
        return freqWordsMap;
    }

    /**
     * mostFrequentWord method finds the most frequent word from the freqWordsMap
     * HashMap.'
     * 
     * @return mostFreqWrdkey - word.
     */
    public String mostFrequentWord() {
        // Looping through the HashMap.
        for (Map.Entry<String, Integer> word : freqWordsMap.entrySet()) {
            // Condition for finding most frequent word.
            if (word.getValue() > value) {
                value = word.getValue();
                mostFreqWrdkey = word.getKey();
            }
        }
        return mostFreqWrdkey;

    }

}