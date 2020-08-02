package textdecorators.util;

import java.util.List;

/**
 * InputDetailsI interface that initialize methods used by Results file.
 */
public interface InputDetailsI {
    public void update(String word);
    public void print();
    public List<String> getInputLineList();
}