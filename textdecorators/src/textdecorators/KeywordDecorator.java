package textdecorators;

import textdecorators.util.InputDetailsI;

public class KeywordDecorator extends AbstractTextDecorator {
    private AbstractTextDecorator atd;
    private InputDetailsI id;

    public KeywordDecorator(AbstractTextDecorator atdIn, InputDetailsI idIn){
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