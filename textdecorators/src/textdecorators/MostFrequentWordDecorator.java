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

    public MostFrequentWordDecorator(AbstractTextDecorator atdIn, InputDetailsI idIn) {
        atd = atdIn;
        id = idIn;
    }

    @Override
    public void processInputDetails() {
        // TODO Auto-generated method stub
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
        for (Map.Entry<String, Integer> word : freqWordsMap.entrySet()) {
            if (word.getValue() > value) {
                value = word.getValue();
                key = word.getKey();
            }
        }
        System.out.println(key);
        id.update(key);

        if (null != atd) {
            atd.processInputDetails();
        }
    }

}