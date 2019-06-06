package mooc.vandy.java4android.diamonds.logic;

import android.util.Log;

import mooc.vandy.java4android.diamonds.ui.OutputInterface;

/**
 * This is where the logic of this App is centralized for this assignment.
 * <p>
 * The assignments are designed this way to simplify your early
 * Android interactions.  Designing the assignments this way allows
 * you to first learn key 'Java' features without having to beforehand
 * learn the complexities of Android.
 */
public class Logic
        implements LogicInterface {
    /**
     * This is a String to be used in Logging (if/when you decide you
     * need it for debugging).
     */
    public static final String TAG = Logic.class.getName();

    /**
     * This is the variable that stores our OutputInterface instance.
     * <p>
     * This is how we will interact with the User Interface [MainActivity.java].
     * <p>
     * It is called 'out' because it is where we 'out-put' our
     * results. (It is also the 'in-put' from where we get values
     * from, but it only needs 1 name, and 'out' is good enough).
     */
    private OutputInterface mOut;

    /**
     * This is the constructor of this class.
     * <p>
     * It assigns the passed in [MainActivity] instance (which
     * implements [OutputInterface]) to 'out'.
     */
    public Logic(OutputInterface out) {
        mOut = out;
    }

    /**
     * This is the method that will (eventually) get called when the
     * on-screen button labeled 'Process...' is pressed.
     */
    public void process(int size) {
        printHorizontalCanvasLine(size);
        drawDiamond(size);
        printHorizontalCanvasLine(size);
    }

    public void printHorizontalCanvasLine(int size) {
        int totalSize = size * 2;
        mOut.print("+");
        printCharacters('-', totalSize);
        mOut.println("+");
    }

    public void drawDiamond(int size) {
        int totalVerticalSize = (size * 2) - 1;

        for (int currentLine = 1; currentLine <= totalVerticalSize; currentLine++) {
            mOut.print("|");
            printSpacesForLine(size, currentLine);
            drawDiamondLine(size, currentLine);
            printSpacesForLine(size, currentLine);
            mOut.println("|");
        }
    }

    public void drawDiamondLine(int size, int lineNumber) {
        char leftDelimiter, rightDelimiter, filling;
        if (lineNumber == size) {
            leftDelimiter = '<';
            rightDelimiter = '>';
        } else if (lineNumber < size) {
            leftDelimiter = '/';
            rightDelimiter = '\\';
        } else {
            leftDelimiter = '\\';
            rightDelimiter = '/';
        }

        if (lineNumber % 2 == 0) {
            filling = '-';
        } else {
            filling = '=';
        }

        lineNumber = getVirtualLineNumber(size, lineNumber);

        int totalFilling = (lineNumber * 2) - 2;

        printDiamondLine(leftDelimiter, rightDelimiter, filling, totalFilling);
    }

    public void printDiamondLine(char leftDelimiter, char rightDelimiter, char filling, int totalFilling) {
        mOut.print(leftDelimiter);
        printCharacters(filling, totalFilling);
        mOut.print(rightDelimiter);
    }

    public int getVirtualLineNumber(int size, int lineNumber) {
        if (lineNumber > size) {
            lineNumber = size - (lineNumber - size);
        }
        return lineNumber;
    }

    public void printSpacesForLine(int size, int lineNumber) {
        if (lineNumber == size) return;
        lineNumber = getVirtualLineNumber(size, lineNumber);

        int numberOfSpaces = -(lineNumber - size);

        printCharacters(' ', numberOfSpaces);
    }

    public void printCharacters(char character, int amount) {
        for (int i = 1; i <= amount; i++) {
            mOut.print(character);
        }
    }
}
