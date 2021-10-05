public class KataRomans {
  public static Integer parse(String input) {g
    RomanSymbol matchingSymbol = RomanSymbol.valueOf(input);
    return matchingSymbol.getValue();
  }
}
