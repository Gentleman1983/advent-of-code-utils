package de.havox_design.aoc.utils.java.model.keypad;

import java.util.HashMap;
import java.util.Map;

public class ComplexKeyPad extends KeyPad {
    public static final Key ONE = new Key(2, 0);
    public static final Key TWO = new Key(1, 1);
    public static final Key THREE = new Key(2, 1);
    public static final Key FOUR = new Key(3, 1);
    public static final Key FIVE = new Key(0, 2);
    public static final Key SIX = new Key(1, 2);
    public static final Key SEVEN = new Key(2, 2);
    public static final Key EIGHT = new Key(3, 2);
    public static final Key NINE = new Key(4, 2);
    public static final Key A = new Key(1, 3);
    public static final Key B = new Key(2, 3);
    public static final Key C = new Key(3, 3);
    public static final Key D = new Key(2, 4);

    private static KeyPad instance;
    private static final Map<Key, String> KEYPAD = new HashMap<>();
    static {
        KEYPAD.put(ONE, "1");
        KEYPAD.put(TWO, "2");
        KEYPAD.put(THREE, "3");
        KEYPAD.put(FOUR, "4");
        KEYPAD.put(FIVE, "5");
        KEYPAD.put(SIX, "6");
        KEYPAD.put(SEVEN, "7");
        KEYPAD.put(EIGHT, "8");
        KEYPAD.put(NINE, "9");
        KEYPAD.put(A, "A");
        KEYPAD.put(B, "B");
        KEYPAD.put(C, "C");
        KEYPAD.put(D, "D");
    }

    private ComplexKeyPad() {
        super();
    }

    public static KeyPad getInstance() {
        if (null == instance) {
            instance = new ComplexKeyPad();
        }

        return instance;
    }

    @Override
    protected Map<Key, String> getKeyMap() {
        return KEYPAD;
    }
}