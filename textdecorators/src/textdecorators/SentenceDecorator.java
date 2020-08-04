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
          
            id.update("BEGIN_SENTENCE__" + sentence + "__END_SENTENCE.", index);
            index += 1;

        }
        if (null != atd) {
            atd.processInputDetails();
        }
    }

}