import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

public class KataRomans {
  public static Integer romanSymbolsToArabic(String romanSymbols) {
    checkInput(romanSymbols);

    Integer accumulator = 0;
    Integer parsedInteger = convertIfValid(romanSymbols, accumulator);

    if (parsedInteger.equals(0)) {
      throw new IllegalArgumentException("Zero does not exists");
    }

    return parsedInteger;
  }

  private static void checkInput(String input) {
    checkForSymbolsRepeatingMoreThanThreeTimes(input);
    checkForNonRepeatableSymbols(input);
  }

  private static void checkForSymbolsRepeatingMoreThanThreeTimes(String input) {
    String[] repeatableUpToThreeSymbols = new String[] { "I", "X", "C", "M" };
    boolean hasSymbolRepeatingMoreThanThreeTimes = Arrays.stream(repeatableUpToThreeSymbols)
        .anyMatch(symbol -> input.contains(symbol.repeat(4)));

    if (hasSymbolRepeatingMoreThanThreeTimes) {
      throw new IllegalArgumentException("I, X, C and M cannot be repeated more than three times successively");
    }
  }

  private static void checkForNonRepeatableSymbols(String input) {
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

  private static Integer convertIfValid(String romanSymbols, Integer currentTotal) {
    if (romanSymbols.isEmpty()) {
      return currentTotal;
    }

    RomanSymbol matchingSymbol = Arrays.stream(RomanSymbol.values())
        .filter(romanSymbol -> romanSymbols.startsWith(romanSymbol.name()))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("Accepted symbols are: [I V X L C D M]"));

    String remainingSymbols = romanSymbols.substring(matchingSymbol.name().length());

    return convertIfValid(remainingSymbols, currentTotal + matchingSymbol.getValue());
  }
}
