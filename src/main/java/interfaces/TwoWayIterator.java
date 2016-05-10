package interfaces;

import java.util.Iterator;

/**
 * Created by alexandermiheev on 10.05.16.
 */
public interface TwoWayIterator<Item> extends Iterator<Item> {
    boolean hasPrevious();
    Item previous();
}
