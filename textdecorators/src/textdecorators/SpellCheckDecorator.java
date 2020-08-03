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
    private int index=0;

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
            String[] word = sentence.split(" ");
            for (int i = 0; i < word.length; i++) {
                for (String spellCheckWrd : spellCheckWords) {
                    int indexOfKeyWrd = word[i].trim().toLowerCase().indexOf(spellCheckWrd);

                    if (indexOfKeyWrd == 0 && word[i].trim().toLowerCase().equals(spellCheckWrd)) {
                        word[i] = "SPELLCHECK_" + word[i] + "_SPELLCHECK";

                    } else if ((indexOfKeyWrd = word[i].trim().toLowerCase().indexOf("_" + spellCheckWrd + "_")) > 0) {
                        word[i] = "SPELLCHECK_" + word[i] + "_SPELLCHECK";
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