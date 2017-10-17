package neuralnetworks.util;

import neuralnetworks.picture.text.data.Letter;

/**
 * Created by faiter on 10/17/17.
 */
public class DigitToString {

    /**
     * Forgive me, for i have sinned
     */
    public static Letter fromDigit(int digit) {

        switch (digit) {

            case 0:
                return Letter.Zero;
            case 1:
                return Letter.One;
            case 2:
                return Letter.Two;
            case 3:
                return Letter.Three;
            case 4:
                return Letter.Four;
            case 5:
                return Letter.Five;
            case 6:
                return Letter.Six;
            case 7:
                return Letter.Seven;
            case 8:
                return Letter.Eight;
            case 9:
                return Letter.Nine;

        }
        throw new IllegalArgumentException("Not 0-9");
    }
}
