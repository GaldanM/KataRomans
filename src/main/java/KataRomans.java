import java.util.Arrays;

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
