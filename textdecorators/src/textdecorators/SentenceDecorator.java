package textdecorators;

import textdecorators.util.InputDetailsI;
import textdecorators.util.MyLogger;

/**
 * SentenceDecorator class extends AbstractTextDecorator abstracr class that
 * decorate each sentence wirh BEGIN_SENTENCE__ and __END_SENTENCE.
 * 
 * @author - Vinit S Bhosale.
 */
public class SentenceDecorator extends AbstractTextDecorator {
    // Initializing AbstractTextDecorator obj.
    private AbstractTextDecorator atd;
    // Initializing InputDetailsI obj.
    private InputDetailsI id;
    private int index = 0;

    /**
     * SentenceDecorator constructor
     * 
     * @param atdIn - AbstractTextDecorator obj.
     * @param idIn  - InputDetailsI obj.
     */
    public SentenceDecorator(AbstractTextDecorator atdIn, InputDetailsI idIn) {
        MyLogger.getInstnace().writeMessage("SentenceDecorator construtor.", MyLogger.DebugLevel.CONSTRUCTOR);
        atd = atdIn;
        id = idIn;
    }

    /**
     * processInputDetails method that decorates sentence with SPELLCHECK_ and
     * _SPELLCHECK.
     */
    @Override
    public void processInputDetails() {
        // TODO Auto-generated method stub
        MyLogger.getInstnace().writeMessage("SentenceDecorator processInputDetails call.",
                MyLogger.DebugLevel.SENTENCEDECORATOR);
        MyLogger.getInstnace().writeMessage("SentenceDecorator decorating the word.",
                MyLogger.DebugLevel.SENTENCEDECORATOR);
        // Looping through each sentence from the list.
        for (String sentence : id.getInputLineList()) {
            MyLogger.getInstnace().writeMessage("SentenceDecorator updating sentence in inputLines list.",
                    MyLogger.DebugLevel.SENTENCEDECORATOR);
            id.update("BEGIN_SENTENCE__" + sentence.substring(0, sentence.length() - 1) + "__END_SENTENCE.", index);
            index += 1;
        }
        // Condition to check atd contains a decorator.
        if (null != atd) {
            // Calling processInputDetails of that decorator.
            atd.processInputDetails();
        }
    }

    @Override
    public String toString() {
        return "Class: SentenceDecorator, Data members: [atd=" + atd.toString() + "id=" + id.toString() + "index="
                + index + "]";

    }

}