package textdecorators.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class InputDetails implements InputDetailsI, StdoutDisplayInterface, FileDisplayInterface {
    private FileProcessor fp;
    private File outputFile;
    private String outputFilePath;
    private String strData;
    private List<String> inputLines = new ArrayList<>();
    private String temp = "";
    private Pattern pattern = Pattern.compile("[$&+:;=?@#|/'<>^*()%!-]+");

    public InputDetails(FileProcessor inFp, String inOutputFile) throws IOException {
        fp = inFp;
        outputFilePath = inOutputFile;
        inputFileProcessor(fp);
    }

    @Override
    public void writeToFile() throws IOException {
        // TODO Auto-generated method stub
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

    @Override
    public void writeToStdout() {
        // TODO Auto-generated method stub
        for (String string : inputLines) {
            System.out.print(string);
        }

    }

    @Override
    public void update(String word, int index) {
        // TODO Auto-generated method stub

        inputLines.set(index, word);

    }

    @Override
    public List<String> getInputLineList() {
        // TODO Auto-generated method stub
        return inputLines;
    }

    public void inputFileProcessor(FileProcessor fp) throws IOException {
        int index = 0;
        strData = fp.poll();
        // Condition to check Empty input file.
        if (strData == null) {
            // empty
        }

        while (null != strData) {

            if (pattern.matcher(strData).find()) {
                System.out.println("Pattern");
                // throw new SpecialCharException("Input file contain special charachters!");
            }

            if (strData.indexOf(".") >= 0) {
                String[] tempStrings = strData.split("\\.");
                for (int i = 0; i < tempStrings.length - 1; i++) {

                    temp = temp.concat(tempStrings[i]);
                    inputLines.add(temp);
                    temp = "";
                }
                temp = tempStrings.length == 0 ? temp : temp.concat(tempStrings[tempStrings.length - 1]);
                if (strData.lastIndexOf(".") == strData.length() - 1) {
                    inputLines.add(temp);
                    temp = "";
                }

            } else {
                temp = temp.concat(strData + "\n");
            }
            strData = fp.poll();
        }

        // for (String string : inputLines) {
        // System.out.print(string);
        // }
    }

}