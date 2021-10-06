import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class KataRomans {
  public static Integer parse(String input) {
    checkInput(input);

    Integer parsedInteger = doParse(input, 0);

    if (parsedInteger.equals(0)) {
      throw new IllegalArgumentException("Zero does not exists");
    }

    return parsedInteger;
  }

  private static void checkInput(String input) {
    String[] repeatableUpToThreeSymbols = new String[] { "I", "X", "C", "M" };
    boolean hasSymbolRepeatingMoreThanThreeTimes = Arrays.stream(repeatableUpToThreeSymbols)
        .anyMatch(symbol -> input.contains(symbol.repeat(4)));

    if (hasSymbolRepeatingMoreThanThreeTimes) {
      throw new IllegalArgumentException("I, X, C and M cannot be repeated more than three times successively");
    }

    boolean hasRepeatingNonRepeatableSymbols = Arrays.stream(input.split(""))
        .filter(symbol -> symbol.matches("[VLD]"))
        .collect(Collectors.groupingBy(
            Function.identity(),
            Collectors.counting()
        ))
        .entrySet().stream()
        .anyMatch(entry -> entry.getValue() > 1);

    if (hasRepeatingNonRepeatableSymbols) {
      throw new IllegalArgumentException("V, L and D cannot be repeated");
    }
  }

  private static Integer doParse(String input, Integer subtotal) {
    if (input.isEmpty()) {
      return subtotal;
    }

    RomanSymbol matchingSymbol = Arrays.stream(RomanSymbol.values())
        .filter(romanSymbol -> input.startsWith(romanSymbol.name()))
        .findFirst()
        .orElseThrow();

    String nextInput = input.substring(matchingSymbol.name().length());

    return doParse(nextInput, subtotal + matchingSymbol.getValue());
  }
}
