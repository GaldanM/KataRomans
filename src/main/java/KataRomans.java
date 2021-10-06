import java.util.Arrays;

public class KataRomans {
  public static Integer parse(String input) {
    Integer parsedInteger = doParse(input, 0);

    if (parsedInteger.equals(0)) {
      throw new IllegalArgumentException("Zero does not exists");
    }

    return parsedInteger;
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
