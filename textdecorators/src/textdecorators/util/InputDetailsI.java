package textdecorators.util;

import java.io.IOException;
import java.util.List;

/**
 * InputDetailsI interface that initialize methods used by Results file.
 */
public interface InputDetailsI {
    public void update(String word, int index) throws IOException;
    public List<String> getInputLineList();
}