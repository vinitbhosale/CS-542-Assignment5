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
            String[] word = sentence.split("\\s");
            for (int i = 0; i < word.length; i++) {
                for (String keyWrd : keyWords) {
                    int indexOfKeyWrd = word[i].toLowerCase().indexOf(keyWrd);

                    if (indexOfKeyWrd == 0 && word[i].toLowerCase().equals(keyWrd)) {
                        word[i] = "KEYWORD_" + word[i] + "_KEYWORD";

                    } else if ((indexOfKeyWrd = word[i].toLowerCase().indexOf("_" + keyWrd + "_")) > 0) {
                        word[i] = "KEYWORD_" + word[i] + "_KEYWORD";
                    }
                }
            }
            id.update(String.join(" ", word), index);
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
            keyWords.add(strData);
            strData = KeyWordFp.poll();
        }
    }
}