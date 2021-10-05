public class KataRomans {
  public static Integer parse(String input) {
    RomanSymbol matchingSymbol = RomanSymbol.valueOf(input);
    return matchingSymbol.getValue();
  }
}
