package textdecorators.util;

import java.util.List;

/**
 * InputDetailsI interface that initialize methods used by Results file.
 */
public interface InputDetailsI {
    public void update(String word, int index);
    public List<String> getInputLineList();
}