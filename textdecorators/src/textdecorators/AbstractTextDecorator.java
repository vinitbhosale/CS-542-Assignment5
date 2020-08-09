package textdecorators;

import java.io.IOException;

/**
 * AbstractTextDecorator abstract class that intialize method used by 
 * Ddecorators.
 */
public abstract class AbstractTextDecorator {
    public abstract void processInputDetails() throws IOException;
}