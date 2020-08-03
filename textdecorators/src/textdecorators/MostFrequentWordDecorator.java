package textdecorators;

import java.util.HashMap;
import java.util.Map;

import textdecorators.util.InputDetailsI;

public class MostFrequentWordDecorator extends AbstractTextDecorator {
    private AbstractTextDecorator atd;
    private InputDetailsI id;
    private Map<String, Integer> freqWordsMap = new HashMap<String, Integer>();
    private int counter = 1;
    private int oldValue;
    private int value = 0;
    private String key = "";
    int index = 0;

    public MostFrequentWordDecorator(AbstractTextDecorator atdIn, InputDetailsI idIn) {
        atd = atdIn;
        id = idIn;
    }

    @Override
    public void processInputDetails() {
        // TODO Auto-generated method stub

        freqWordsMap = wordFreqCounter();
        System.out.println(freqWordsMap);
        key = mostFrequentWord();

        for (String sentence : id.getInputLineList()) {
            String[] wrd = sentence.split(" ");
            for (int i = 0; i < wrd.length; i++) {

                if (key.equals(wrd[i].toLowerCase().trim())) {
                    int wordIndex = wrd[i].toLowerCase().indexOf(key);
                    String temp = wrd[i].substring(0, wordIndex) + "MOST_FREQUENT_"
                            + wrd[i].substring(wordIndex, wordIndex + key.length()) + "_MOST_FREQUENT";
                    temp = wordIndex + key.length() == wrd[i].length() ? temp
                            : temp + wrd[i].substring(wordIndex + key.length());

                    wrd[i] = temp;
                }
            }
            id.update(String.join(" ", wrd), index);
            index += 1;
        }

        if (null != atd) {
        atd.processInputDetails();
        }
    }

    public Map<String, Integer> wordFreqCounter() {
        for (String sentance : id.getInputLineList()) {
            String[] word = sentance.toLowerCase().split(" ");
            for (int i = 0; i < word.length; i++) {
                if (freqWordsMap.containsKey(word[i].trim())) {
                    oldValue = freqWordsMap.get(word[i].trim());
                    freqWordsMap.replace(word[i].trim(), oldValue, oldValue + 1);
                } else {
                  
                    String[] temWrdArr = word[i].trim().split("\\W+");
                    freqWordsMap.put(temWrdArr[0].trim(), counter);
                }
            }
        }
        return freqWordsMap;
    }

    public String mostFrequentWord() {
        for (Map.Entry<String, Integer> word : freqWordsMap.entrySet()) {
            if (word.getValue() > value) {
                value = word.getValue();
                key = word.getKey();
            }
        }

        return key;

    }

}