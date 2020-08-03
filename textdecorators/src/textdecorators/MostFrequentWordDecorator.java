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
        key = mostFrequentWord();

        for (String sentence : id.getInputLineList()) {
            String[] wrd = sentence.split("\\s");
            for (int i = 0; i < wrd.length; i++) {
                if (key.equals(wrd[i].toLowerCase())) {
                    wrd[i] = "MOST_FREQUENT_" + wrd[i] + "_MOST_FREQUENT";
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
            for (String word : sentance.toLowerCase().split("\\s")) {
                if (freqWordsMap.containsKey(word)) {
                    oldValue = freqWordsMap.get(word);
                    freqWordsMap.replace(word, oldValue, oldValue + 1);
                } else {
                    freqWordsMap.put(word, counter);
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