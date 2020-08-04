package textdecorators;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import textdecorators.util.FileProcessor;
import textdecorators.util.InputDetailsI;

public class KeywordDecorator extends AbstractTextDecorator {
    private AbstractTextDecorator atd;
    private InputDetailsI id;
    private FileProcessor keyWordFp;
    private String strData;
    private List<String> keyWords = new ArrayList<>();
    private int index = 0;

    public KeywordDecorator(AbstractTextDecorator atdIn, InputDetailsI idIn, FileProcessor inKeyWordFp)
            throws IOException {
        atd = atdIn;
        id = idIn;
        keyWordFp = inKeyWordFp;
        keywordFileProcessor(keyWordFp);
    }

    @Override
    public void processInputDetails() {

        for (String sentence : id.getInputLineList()) {
            String[] wordsArr = sentence.split(" ");
            for (int i = 0; i < wordsArr.length; i++) {
                String[] tempWrd = wordsArr[i].split("[^a-zA-Z0-9]");
                for (String wrd : tempWrd) {
                    for (String keyWrd : keyWords) {
                        if (wrd.toLowerCase().trim().equals(keyWrd.trim())) {

                            String mostFreq = "MOST_FREQUENT_";
                            int mostFreqWrdIndex = wordsArr[i].indexOf(mostFreq);

                            int wordIndex = wordsArr[i].toLowerCase().indexOf(keyWrd);

                            String temp = "";
                            if (mostFreqWrdIndex > -1) {
                                temp = wordsArr[i].substring(0, mostFreqWrdIndex) + "KEYWORD_"
                                        + wordsArr[i].substring(mostFreqWrdIndex, mostFreqWrdIndex + mostFreq.length())
                                        + wordsArr[i].substring(wordIndex, wordIndex + keyWrd.length())
                                        + wordsArr[i].substring(wordIndex + keyWrd.length(),
                                                wordIndex + keyWrd.length() + mostFreq.length())
                                        + "_KEYWORD";

                                temp = wordIndex + keyWrd.length() + mostFreq.length() == wordsArr[i].length() ? temp
                                        : temp + wordsArr[i].substring(wordIndex + keyWrd.length() + mostFreq.length());
                            } else {
                                temp = wordsArr[i].substring(0, wordIndex) + "KEYWORD_"
                                        + wordsArr[i].substring(wordIndex, wordIndex + keyWrd.length()) + "_KEYWORD";
                                temp = wordIndex + keyWrd.length() == wordsArr[i].length() ? temp
                                        : temp + wordsArr[i].substring(wordIndex + keyWrd.length());
                            }

                            wordsArr[i] = temp;
                            break;
                        }
                    }

                }

            }
            id.update(String.join(" ", wordsArr), index);
            index += 1;
        }

        if (null != atd) {
             atd.processInputDetails();
        }
    }

    public void keywordFileProcessor(FileProcessor KeyWordFp) throws IOException {
        strData = KeyWordFp.poll();
        // Condition to check Empty input file.
        if (null == strData) {
            // throw new EmptyInputFileException("Empty Input File!");
        }
        while (null != strData) {
            keyWords.add(strData.trim());
            strData = KeyWordFp.poll();
        }
    }
}