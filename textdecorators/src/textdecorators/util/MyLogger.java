package textdecorators.util;

public class MyLogger {
    /**
     * MyLogger class
     * 
     * DebugValue = 1 (For DRIVER class) 
     * DebugValue = 2 (For CONSTRUCTOR) 
     * DebugValue = 3 (For FILEPROCESSOR class) 
     * DebugValue = 4 (For INPUTDETAILS class)
     * DebugValue = 5 (For MOSTFREQUENTWORDDECORATOR class) 
     * DebugValue = 6 (For KEYWORDDECORATOR class) 
     * DebugValue = 7 (For SPELLCHECKDECORATOR class) 
     * DebugValue = 8 (For SENTENCEDECORATOR class) 
     */
    private static MyLogger uniqueInstance = new MyLogger();

    private MyLogger() {
    }

    public static MyLogger getInstnace() {
        return uniqueInstance;

    }

    public static enum DebugLevel {
        DRIVER, CONSTRUCTOR, FILEPROCESSOR, INPUTDETAILS, MOSTFREQUENTWORDDECORATOR, KEYWORDDECORATOR, SPELLCHECKDECORATOR,
        SENTENCEDECORATOR, NONE
    };

    private static DebugLevel debugLevel;

    public void setDebugValue(int levelIn) {
        switch (levelIn) {
            case 1:
                debugLevel = DebugLevel.DRIVER;
                break;
            case 2:
                debugLevel = DebugLevel.CONSTRUCTOR;
                break;
            case 3:
                debugLevel = DebugLevel.FILEPROCESSOR;
                break;
            case 4:
                debugLevel = DebugLevel.INPUTDETAILS;
                break;
            case 5:
                debugLevel = DebugLevel.MOSTFREQUENTWORDDECORATOR;
                break;
            case 6:
                debugLevel = DebugLevel.KEYWORDDECORATOR;
                break;
            case 7:
                debugLevel = DebugLevel.SPELLCHECKDECORATOR;
                break;
            case 8:
                debugLevel = DebugLevel.SENTENCEDECORATOR;
                break;
            default:
                debugLevel = DebugLevel.NONE;
                break;
        }
    }

    public void setDebugValue(DebugLevel indebugValue) {
        MyLogger.debugLevel = indebugValue;
    }

    public void writeMessage(String message, DebugLevel levelIn) {
        if (levelIn == debugLevel)
            System.out.println(message);
    }

    public String toString() {
        return "The debug level has been set to the following " + debugLevel;
    }
}