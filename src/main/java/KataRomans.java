import exception.SymbolDoesNotExistsException;
import exception.SymbolRepeatedException;
import exception.SymbolRepeatedTooMuchException;
import exception.ZeroDoesNotExistsException;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

public class KataRomans {
  public static Integer romanSymbolsToArabic(String romanSymbols) {
    checkInput(romanSymbols);

    Integer accumulator = 0;
    Integer parsedInteger = convertIfValid(romanSymbols, accumulator);

    if (parsedInteger.equals(0)) {
      throw new ZeroDoesNotExistsException();
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
      throw new SymbolRepeatedTooMuchException();
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
      throw new SymbolRepeatedException();
    }
  }

  private static Integer convertIfValid(String romanSymbols, Integer currentTotal) {
    if (romanSymbols.isEmpty()) {
      return currentTotal;
    }

    RomanSymbol matchingSymbol = Arrays.stream(RomanSymbol.values())
        .filter(romanSymbol -> romanSymbols.startsWith(romanSymbol.name()))
        .findFirst()
        .orElseThrow(SymbolDoesNotExistsException::new);

    String remainingSymbols = romanSymbols.substring(matchingSymbol.name().length());

    return convertIfValid(remainingSymbols, currentTotal + matchingSymbol.getValue());
  }
}
