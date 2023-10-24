package Tools;
import java.text.DecimalFormat;

public class TextTools {

    // Colours

    final public static String RESET = "\033[0m";
    final public static String RED = createForegroundColour(255, 20, 20);
    final public static String ORANGE = createForegroundColour(255, 200, 20);
    final public static String BLUE = createForegroundColour(0, 0, 255);

    final public static String BFTT = createForegroundColour(255, 0, 0);
    final public static String BFTD = createForegroundColour(122, 0, 0);

    final public static String AFTT = createForegroundColour(0, 221, 255);
    final public static String AFTD = createForegroundColour(0, 148, 153);

    public static String createForegroundColour(int r, int g, int b) {
        return String.format("\033[38;2;%s;%s;%sm", r, g, b);
    } 

    // Formatting

    final public static DecimalFormat DF = new DecimalFormat("0.#########");

}
