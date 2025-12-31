package de.havox_design.aoc.utils.java.model.keypad;


import de.havox_design.aoc.utils.java.AoCFunctionality;

import java.util.List;

import static de.havox_design.aoc.utils.java.model.keypad.AoC2016Day02Direction.*;

public class AoC2016Day02BathroomSecurity implements AoCFunctionality {
    private final List<String> input;

    public AoC2016Day02BathroomSecurity(String fileName) {
        input = readData(fileName);
    }

    public static String solvePart1(String fileName) {
        AoC2016Day02BathroomSecurity instance = new AoC2016Day02BathroomSecurity(fileName);

        return instance.solvePart1();
    }

    public static String solvePart2(String fileName) {
        AoC2016Day02BathroomSecurity instance = new AoC2016Day02BathroomSecurity(fileName);

        return instance.solvePart2();
    }

    public String solvePart1() {
        KeyPad keypad = SimpleKeyPad.getInstance();
        Key currentElement = SimpleKeyPad.FIVE;
        StringBuilder bathroomCode = new StringBuilder();

        searchBathroomCode(currentElement, keypad, bathroomCode);

        return bathroomCode.toString();
    }

    public String solvePart2() {
        KeyPad keypad = ComplexKeyPad.getInstance();
        Key currentElement = ComplexKeyPad.FIVE;
        StringBuilder bathroomCode = new StringBuilder();

        searchBathroomCode(currentElement, keypad, bathroomCode);

        return bathroomCode.toString();
    }

    private void searchBathroomCode(Key currentElement, KeyPad keypad, StringBuilder bathroomCode) {
        for(String instruction : input) {
            for(char c : instruction.toCharArray()) {
                Key nextElement = switch (c) {
                    case 'U' -> UP.next(currentElement);
                    case 'D' -> DOWN.next(currentElement);
                    case 'L' -> LEFT.next(currentElement);
                    case 'R' -> RIGHT.next(currentElement);
                    default -> throw new IllegalArgumentException("Symbol '" + c + "' on instruction '" + instruction +
                            "' is no supported operation.");
                };

                if(isOnKeypad(nextElement, keypad)) {
                    currentElement = nextElement;
                }
            }

            bathroomCode.append(keypad.getValueForKey(currentElement));
        }
    }

    private boolean isOnKeypad(Key key, KeyPad keypad) {
        return keypad.getKeypadElements().contains(key);
    }
}
