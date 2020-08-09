package textdecorators.driver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.InvalidPathException;

import textdecorators.AbstractTextDecorator;
import textdecorators.KeywordDecorator;
import textdecorators.MostFrequentWordDecorator;
import textdecorators.SentenceDecorator;
import textdecorators.SpellCheckDecorator;
import textdecorators.userException.EmptyInputFileException;
import textdecorators.userException.SameFileNameException;
import textdecorators.userException.SpecialCharException;
import textdecorators.util.FileProcessor;
import textdecorators.util.InputDetails;
import textdecorators.util.InputDetailsI;
import textdecorators.util.MyLogger;
import textdecorators.util.StdoutDisplayInterface;
import textdecorators.util.FileDisplayInterface;

/**
 * Driver class start point.
 * 
 * @author Vint S Bhosale
 */
public class Driver {
    public static void main(String[] args) throws InvalidPathException, FileNotFoundException, IOException,
            SameFileNameException, SpecialCharException, EmptyInputFileException {
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
            MyLogger.getInstnace().writeMessage("Setting debug level to " + args[4]+"\n", MyLogger.DebugLevel.DRIVER);

            // Initializing FileProcessor objects for input,keyword and misspelled file.
            FileProcessor fp = new FileProcessor(args[0]);
            FileProcessor keyWordFp = new FileProcessor(args[2]);
            FileProcessor spellCheckFp = new FileProcessor(args[1]);

            // Intializing InputDetails object for decorator.
            InputDetailsI inputD = new InputDetails(fp, args[3]);

            // Initializing Decorator objects.
            AbstractTextDecorator sentenceDecorator = new SentenceDecorator(null, inputD);
            AbstractTextDecorator spellCheckDecorator = new SpellCheckDecorator(sentenceDecorator, inputD,
                    spellCheckFp);
            AbstractTextDecorator keywordDecorator = new KeywordDecorator(spellCheckDecorator, inputD, keyWordFp);
            AbstractTextDecorator mostFreqWordDecorator = new MostFrequentWordDecorator(keywordDecorator, inputD);

            // Calling processInputDetails of mostFreqWordDecorator.
            MyLogger.getInstnace().writeMessage("Calling mostFreqWordDecorator processInputDetails method."+"\n",
                    MyLogger.DebugLevel.DRIVER);
            mostFreqWordDecorator.processInputDetails();
            // Calling writeToStdout to display out on stdout.
            MyLogger.getInstnace().writeMessage("Displaying output to stdout."+"\n", MyLogger.DebugLevel.DRIVER);
            ((StdoutDisplayInterface) inputD).writeToStdout();
            // Calling writeToFile to write output in output file.
            MyLogger.getInstnace().writeMessage("Writing output to output.txt file."+"\n", MyLogger.DebugLevel.DRIVER);
            ((FileDisplayInterface) inputD).writeToFile();
            MyLogger.getInstnace().closeFile();
        } catch (InvalidPathException | IOException | SameFileNameException e) {
            System.err.println(e.getMessage());
        }

    }
}