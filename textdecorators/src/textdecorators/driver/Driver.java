package textdecorators.driver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.InvalidPathException;

import textdecorators.AbstractTextDecorator;
import textdecorators.KeywordDecorator;
import textdecorators.MostFrequentWordDecorator;
import textdecorators.SentenceDecorator;
import textdecorators.SpellCheckDecorator;
import textdecorators.userException.SameFileNameException;
import textdecorators.util.InputDetails;
import textdecorators.util.InputDetailsI;
import textdecorators.util.MyLogger;

public class Driver {
    public static void main(String[] args)
            throws InvalidPathException, FileNotFoundException, IOException, SameFileNameException {
        try {
            /*
             * As the build.xml specifies the arguments as input, misspelled,keywords,
             * output and debug value, in case the argument value is not given java takes
             * the default value specified in build.xml. To avoid that, below condition is
             * used
             */
            if ((args.length != 5) || (args[0].equals("${input}")) || (args[1].equals("${misspelled}"))
                    || (args[2].equals("${keywords}")) || (args[3].equals("${output}"))
                    || (args[4].equals("${debug}"))) {
                System.err.println("Error: Incorrect number of arguments. Program accepts 5 arguments.");
                System.exit(0);
            }
            System.out.println("Hello World! Lets get started with the assignment");
            // Condition for missing input files
            if (args[0].isEmpty()) {
                throw new FileNotFoundException("Missing Input file parameter!");

            }
            // Condition for missing misspelled word file
            if (args[1].isEmpty()) {
                throw new FileNotFoundException("Missing misspelled word file parameter!");

            }
            // Condition for missing keywords file
            if (args[2].isEmpty()) {
                throw new FileNotFoundException("Missing keywords file parameter!");

            }
            // Condition to check same name of input and output files.
            if (args[0].equals(args[1]) || args[0].equals(args[2])) {
                throw new SameFileNameException("No two files can have same name!");
            }

            // Setting debug value in MyLogger.
            MyLogger.getInstnace().setDebugValue(Integer.parseInt(args[4]));
            MyLogger.getInstnace().writeMessage("Setting debug level to " + args[4], MyLogger.DebugLevel.DRIVER);

            InputDetailsI inputD = new InputDetails(args[0], args[3]);
            AbstractTextDecorator sentenceDecorator = new SentenceDecorator(null, inputD);
            AbstractTextDecorator spellCheckDecorator = new SpellCheckDecorator(sentenceDecorator, inputD);
            AbstractTextDecorator keywordDecorator = new KeywordDecorator(spellCheckDecorator, inputD);
            AbstractTextDecorator mostFreqWordDecorator = new MostFrequentWordDecorator(keywordDecorator, inputD);

        } catch (InvalidPathException | IOException | SameFileNameException e) {
            System.err.println(e.getMessage());
        }

    }
}