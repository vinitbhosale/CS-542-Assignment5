package textdecorators;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import textdecorators.util.FileProcessor;
import textdecorators.util.InputDetailsI;

public class SpellCheckDecorator extends AbstractTextDecorator {
    private AbstractTextDecorator atd;
    private InputDetailsI id;
    private FileProcessor spellCheckFp;
    private String strData;
    private List<String> spellCheckWords = new ArrayList<>();
    private int index = 0;

    public SpellCheckDecorator(AbstractTextDecorator atdIn, InputDetailsI idIn, FileProcessor inSpellCheckFp)
            throws IOException {
        atd = atdIn;
        id = idIn;
        spellCheckFp = inSpellCheckFp;
        spellCheckFileProcessor(spellCheckFp);
    }

    @Override
    public void processInputDetails() {
        // TODO Auto-generated method stub
        for (String sentence : id.getInputLineList()) {
            String[] wordArr = sentence.split(" ");
            for (int i = 0; i < wordArr.length; i++) {
                String[] tempWrd = wordArr[i].split("[^a-zA-Z0-9]");
                for (String wrd : tempWrd) {
                    for (String spellCheckWrd : spellCheckWords) {
                        if (wrd.toLowerCase().trim().equals(spellCheckWrd.trim())) {
                            String keyWrd = "KEYWORD_";
                            String mostFreq = "MOST_FREQUENT_";
                            int keyWrdIndex = wordArr[i].indexOf(keyWrd);
                            int mostFreqWrdIndex = wordArr[i].indexOf(mostFreq);
                            int wordIndex = wordArr[i].toLowerCase().indexOf(spellCheckWrd);

                            String temp = "";
                            if (keyWrdIndex > -1 && mostFreqWrdIndex > -1) {

                                temp = wordArr[i].substring(0, keyWrdIndex) + "SPELLCHECK_"
                                        + wordArr[i].substring(keyWrdIndex, keyWrdIndex + keyWrd.length())
                                        + wordArr[i].substring(mostFreqWrdIndex, mostFreqWrdIndex + mostFreq.length())
                                        + wordArr[i].substring(wordIndex, wordIndex + spellCheckWrd.length())
                                        + wordArr[i].substring(wordIndex + spellCheckWrd.length(), wordIndex
                                                + spellCheckWrd.length() + mostFreq.length() + keyWrd.length())
                                        + "_SPELLCHECK";
                                temp = wordIndex + spellCheckWrd.length() + keyWrd.length()
                                        + mostFreq.length() == wordArr[i].length() ? temp
                                                : temp + wordArr[i].substring(wordIndex + spellCheckWrd.length()
                                                        + keyWrd.length() + mostFreq.length());
                            } else if (mostFreqWrdIndex > -1) {
                                temp = wordArr[i].substring(0, mostFreqWrdIndex) + "SPELLCHECK_"
                                        + wordArr[i].substring(mostFreqWrdIndex, mostFreqWrdIndex + mostFreq.length())
                                        + wordArr[i].substring(wordIndex, wordIndex + spellCheckWrd.length())
                                        + wordArr[i].substring(wordIndex + spellCheckWrd.length(),
                                                wordIndex + spellCheckWrd.length() + mostFreq.length())
                                        + "_SPELLCHECK";
                                temp = wordIndex + spellCheckWrd.length() + mostFreq.length() == wordArr[i].length() ? temp
                                        : temp + wordArr[i].substring(wordIndex + spellCheckWrd.length() + mostFreq.length());
                            } else if (keyWrdIndex > -1) {
                                temp = wordArr[i].substring(0, keyWrdIndex) + "SPELLCHECK_"
                                        + wordArr[i].substring(keyWrdIndex, keyWrdIndex + keyWrd.length())
                                        + wordArr[i].substring(wordIndex, wordIndex + spellCheckWrd.length())
                                        + wordArr[i].substring(wordIndex + spellCheckWrd.length(),
                                                wordIndex + spellCheckWrd.length() + keyWrd.length())
                                        + "_SPELLCHECK";

                                temp = wordIndex + spellCheckWrd.length() + keyWrd.length() == wordArr[i].length()
                                        ? temp
                                        : temp + wordArr[i]
                                                .substring(wordIndex + spellCheckWrd.length() + keyWrd.length());

                            } else {

                                temp = wordArr[i].substring(0, wordIndex) + "SPELLCHECK_"
                                        + wordArr[i].substring(wordIndex, wordIndex + spellCheckWrd.length())
                                        + "_SPELLCHECK";
                                temp = wordIndex + spellCheckWrd.length() == wordArr[i].length() ? temp
                                        : temp + wordArr[i].substring(wordIndex + spellCheckWrd.length());

                            }

                            wordArr[i] = temp;
                            break;
                        }

                    }
                }

            }
            id.update(String.join(" ", wordArr), index);
            index += 1;
        }
        if (null != atd)

        {
            atd.processInputDetails();
        }
    }

    public void spellCheckFileProcessor(FileProcessor spellCheckFp) throws IOException {
        strData = spellCheckFp.poll();
        // Condition to check Empty input file.
        if (null == strData) {
            // throw new EmptyInputFileException("Empty Input File!");
        }
        while (null != strData) {
            spellCheckWords.add(strData.trim());
            strData = spellCheckFp.poll();
        }
    }

}