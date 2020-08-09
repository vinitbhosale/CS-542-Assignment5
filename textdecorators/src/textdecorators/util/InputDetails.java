package textdecorators.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import textdecorators.userException.EmptyInputFileException;
import textdecorators.userException.SpecialCharException;

/**
 * InputDetails class that implements reading input file and its processing.
 * Also is stores the inpurt file and update the stored input data. InputDetails
 * implements StdoutDisplayInterface and FileDisplayInterface which writes the
 * update results to stdout and output file respectively.
 * 
 * @author - Vinit S Bhosale
 */

public class InputDetails implements InputDetailsI, StdoutDisplayInterface, FileDisplayInterface {
    // Initializing FileProcessor obj.
    private FileProcessor fp;
    private File outputFile;
    private String outputFilePath;
    private String strData;
    // List to store complete senteces.
    private List<String> inputLines = new ArrayList<>();
    private String temp = "";
    // Regex to chechk for special charachters.
    private Pattern pattern = Pattern.compile("[$&+:;=?@#|/'<>^*()%!-]+");

    /**
     * InputDetails constructor
     * 
     * @param inFp         - FileProcessor obj for Input file.
     * @param inOutputFile - output file.
     * 
     * @throws IOException
     * @throws SpecialCharException
     * @throws EmptyInputFileException
     */
    public InputDetails(FileProcessor inFp, String inOutputFile)
            throws IOException, SpecialCharException, EmptyInputFileException {
        MyLogger.getInstnace().writeMessage("InputDetails constructor."+"\n", MyLogger.DebugLevel.CONSTRUCTOR);
        fp = inFp;
        outputFilePath = inOutputFile;
        MyLogger.getInstnace().writeMessage("InputDetails processing input file."+"\n", MyLogger.DebugLevel.CONSTRUCTOR);
        // Call to inputFileProcessor method to process input file.
        inputFileProcessor(fp);
    }

    /**
     * writeToFile method that write results in the output file.
     * 
     * @throws IOException
     */
    @Override
    public void writeToFile() throws IOException {
        // TODO Auto-generated method stub
        MyLogger.getInstnace().writeMessage("Writing output to output file from InputDetails class."+"\n",
                MyLogger.DebugLevel.INPUTDETAILS);
        outputFile = new File(outputFilePath);
        if (!outputFile.exists()) {
            outputFile.createNewFile();
        }

        BufferedWriter outputBufferedWriter = new BufferedWriter(new FileWriter(outputFile));

        for (String string : inputLines) {
            outputBufferedWriter.write(string);
        }

        outputBufferedWriter.close();
    }

    /**
     * writeToStdout method to print result on stdout.
     * 
     * @throws IOException
     */
    @Override
    public void writeToStdout() throws IOException {
        // TODO Auto-generated method stub
        MyLogger.getInstnace().writeMessage("Writing output to stdout from InputDetails class."+"\n",
                MyLogger.DebugLevel.INPUTDETAILS);
        for (String string : inputLines) {
            System.out.print(string);
        }

    }

    /**
     * update method that update the lines in inputLines list.
     * 
     * @param word  - Updated string.
     * @param index - index at which string need to update.
     * @throws IOException
     */
    @Override
    public void update(String word, int index) throws IOException {
        // TODO Auto-generated method stub
        MyLogger.getInstnace().writeMessage("Updating line in inputLines list."+"\n", MyLogger.DebugLevel.INPUTDETAILS);
        inputLines.set(index, word);

    }

    /**
     * getInputLineList return the inputLines list.
     * 
     * @return inputLines
     */
    @Override
    public List<String> getInputLineList() {
        // TODO Auto-generated method stub
        return inputLines;
    }

    /**
     * inputFileProcessor method implements processing of line in Input.txt file.
     * 
     * @param fp - FileProcessor object
     * 
     * @throws IOException
     * @throws SpecialCharException
     * @throws EmptyInputFileException
     */
    public void inputFileProcessor(FileProcessor fp) throws IOException, SpecialCharException, EmptyInputFileException {
        MyLogger.getInstnace().writeMessage("Processing Input file."+"\n", MyLogger.DebugLevel.INPUTDETAILS);
        strData = fp.poll();
        // Condition to check Empty input file.
        if (strData == null) {
            throw new EmptyInputFileException("Empty Input File!");
        }
        MyLogger.getInstnace().writeMessage("Reading Input file line by line."+"\n", MyLogger.DebugLevel.INPUTDETAILS);
        while (null != strData) {
            // COndition to check input line format.
            if (pattern.matcher(strData).find()) {
                throw new SpecialCharException("Input file contain special charachters!");
            }
            MyLogger.getInstnace().writeMessage("Fetching and storing sentence in inputLine list."+"\n",
                    MyLogger.DebugLevel.INPUTDETAILS);

            // Finding complete sentence from input file.
            if (strData.indexOf(".") >= 0) {
                // Splitting line at period and conacate and storing. the complete sentence.
                String[] tempStrings = strData.split("\\.");
                for (int i = 0; i < tempStrings.length - 1; i++) {
                    temp = temp.concat(tempStrings[i]);
                    inputLines.add(temp + ".");
                    temp = "";
                }
                // Storing other sentence after the period.
                temp = tempStrings.length == 0 ? temp : temp.concat(tempStrings[tempStrings.length - 1]);
                if (strData.lastIndexOf(".") == strData.length() - 1) {
                    inputLines.add(temp + ".");
                    temp = "";
                }

            } else {
                temp = temp.concat(strData + "\n");
            }
            // Fetching the next line.
            strData = fp.poll();
        }

    }

    @Override

    public String toString() {
        return "Class: InputDetails, Data members: [fp = " + fp.toString() + "outputFile=" + outputFile.toString()
                + "strData=" + strData.toString() + "inputLines=" + inputLines.toString() + "temp=" + temp.toString()
                + "]";

    }

}