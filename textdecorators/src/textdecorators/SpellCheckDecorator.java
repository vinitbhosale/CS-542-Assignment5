package textdecorators;

import textdecorators.util.InputDetailsI;

public class SpellCheckDecorator extends AbstractTextDecorator {
    private AbstractTextDecorator atd;
    private InputDetailsI id;

    public SpellCheckDecorator(AbstractTextDecorator atdIn, InputDetailsI idIn){
        atd = atdIn;
        id = idIn;
    }
    @Override
    public void processInputDetails() {
        // TODO Auto-generated method stub

    }
    
}