package textdecorators.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class InputDetails implements InputDetailsI, StdoutDisplayInterface, FileDisplayInterface {
    private FileProcessor fp;
    private String outputFile;
    private String strData;
    private List<String> inputLines = new ArrayList<>();
    private int index = 0;
    private String temp = "";
    private Pattern pattern = Pattern.compile("[$&+:;=\\\\?@#|/'<>^*()%!-]+");

    public InputDetails(FileProcessor inFp, String inOutputFile) throws IOException {
        fp = inFp;
        outputFile = inOutputFile;
        inputFileProcessor(fp);
    }
    @Override
    public void writeToFile() throws IOException {
        // TODO Auto-generated method stub

    }

    @Override
    public void writeToStdout() {
        // TODO Auto-generated method stub

    }

    @Override
    public void storeResult(String temp) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<String> getInputLineList() {
        // TODO Auto-generated method stub
        return inputLines;
    }

    public void inputFileProcessor(FileProcessor fp) throws IOException {
        strData = fp.poll();
        // Condition to check Empty input file.
        if (null == strData) {
            // throw new EmptyInputFileException("Empty Input File!");
        }

        while (null != strData) {
            // Empty line condition.
            if (strData.length() == 0) {
                // throw new ErroFileException("Empty line in input file!");
            }
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
                temp = temp.concat(tempStrings[tempStrings.length - 1]);
                if (strData.lastIndexOf(".") == strData.length() - 1) {
                    inputLines.add(temp);
                    temp = "";
                }

            } else {
                temp = temp.concat(strData);
            }
            strData = fp.poll();
        }
    }

    public void print() {
        for (String string : inputLines) {
            System.out.println(string);
        }
    }

   

}