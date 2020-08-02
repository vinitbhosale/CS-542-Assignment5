package textdecorators;

import textdecorators.util.InputDetailsI;

public class SentenceDecorator extends AbstractTextDecorator {
    private AbstractTextDecorator atd;
    private InputDetailsI id;

    public SentenceDecorator(AbstractTextDecorator atdIn, InputDetailsI idIn) {
        atd = atdIn;
        id = idIn;
    }

    @Override
    public void processInputDetails() {
        // TODO Auto-generated method stub
        if (null != atd) {
            atd.processInputDetails();
        }
    }

}