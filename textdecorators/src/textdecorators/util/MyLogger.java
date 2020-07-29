package textdecorators.util;

public class MyLogger {
    /**
     * MyLogger class
     * 
     * DebugValue = 1 (For DRIVER class) 
     * DebugValue = 2 (For CONSTRUCTOR) 
     * DebugValue = 3 (For FILEPROCESSOR class) 
     * DebugValue = 4 ()
     * DebugValue = 5 () 
     * DebugValue = 6 () 
     * DebugValue = 7 () 
     * DebugValue = 8 () 
     * DebugValue = 9 (For RESULTS class)
     */
    private static MyLogger uniqueInstance = new MyLogger();

    private MyLogger() {
    }

    public static MyLogger getInstnace() {
        return uniqueInstance;

    }

    public static enum DebugLevel {
        DRIVER, CONSTRUCTOR, FILEPROCESSOR, COMMONINTSVISITOR, MISSINGINTSVISITOR, POPULATEMYARRAYVISITOR, MYARRAY,
        MYARRAYLIST, RESULTS, NONE
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
                debugLevel = DebugLevel.COMMONINTSVISITOR;
                break;
            case 5:
                debugLevel = DebugLevel.MISSINGINTSVISITOR;
                break;
            case 6:
                debugLevel = DebugLevel.POPULATEMYARRAYVISITOR;
                break;
            case 7:
                debugLevel = DebugLevel.MYARRAY;
                break;
            case 8:
                debugLevel = DebugLevel.MYARRAYLIST;
                break;
            case 9:
                debugLevel = DebugLevel.RESULTS;
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