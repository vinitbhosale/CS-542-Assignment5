package textdecorators;

import textdecorators.util.InputDetailsI;

public class SentenceDecorator extends AbstractTextDecorator {
    private AbstractTextDecorator atd;
    private InputDetailsI id;
    private int index = 0;

    public SentenceDecorator(AbstractTextDecorator atdIn, InputDetailsI idIn) {
        atd = atdIn;
        id = idIn;
    }

    @Override
    public void processInputDetails() {
        // TODO Auto-generated method stub
        for (String sentence : id.getInputLineList()) {
            String[] word = sentence.split(" ");

            word[0] = "BEGIN_SENTENCE__" + word[0];
            word[word.length - 1] = word[word.length - 1] + "__END_SENTENCE.";
            id.update(String.join(" ", word), index);
            index += 1;

        }
        if (null != atd) {
            atd.processInputDetails();
        }
    }

}