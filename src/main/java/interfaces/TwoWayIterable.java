package interfaces;

/**
 * Created by alexandermiheev on 10.05.16.
 */
public interface TwoWayIterable<Item> {
    TwoWayIterator<Item> getTwoWayIterator();
}
