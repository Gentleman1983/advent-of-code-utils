package de.havox_design.aoc.utils.java.model.keypad;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

public abstract class KeyPad {
    public Set<Key> getKeypadElements() {
        return getKeyMap().keySet();
    }

    public String getValueForKey(Key key) {
        Map<Key, String> keyMap = getKeyMap();

        if(keyMap.containsKey(key)) {
            return keyMap.get(key);
        }
        else {
            throw new NoSuchElementException("This is no Keypad element.");
        }
    }

    protected abstract Map<Key,String> getKeyMap();
}
