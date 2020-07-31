package textdecorators.util;

import java.io.IOException;

public class InputDetails implements InputDetailsI, StdoutDisplayInterface, FileDisplayInterface {
    private String inputFile;
    private String outputFile;

    public InputDetails(String inInputFile, String inOutputFile){
        inputFile = inInputFile;
        outputFile = inOutputFile;
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
    
}