package textdecorators;

import textdecorators.util.FileProcessor;
import textdecorators.util.InputDetailsI;

public class SpellCheckDecorator extends AbstractTextDecorator {
    private AbstractTextDecorator atd;
    private InputDetailsI id;
    private FileProcessor spellCheckFp;

    public SpellCheckDecorator(AbstractTextDecorator atdIn, InputDetailsI idIn, FileProcessor inSpellCheckFp){
        atd = atdIn;
        id = idIn;
        spellCheckFp = inSpellCheckFp;
    }
    @Override
    public void processInputDetails() {
        // TODO Auto-generated method stub
        if (null != atd) {
            atd.processInputDetails();
        }
    }
    
}