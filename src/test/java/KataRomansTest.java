import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class KataRomansTest {
  @Test
  void I_is_1() {
    testRomanSymbolsParsing("I", 1);
  }

  @Test
  void V_is_5() {
    testRomanSymbolsParsing("V", 5);
  }

  @Test
  void X_is_10() {
    testRomanSymbolsParsing("X", 10);
  }

  private void testRomanSymbolsParsing(String romanSymbols, Integer expectedInteger) {
    assertThat(KataRomans.parse(romanSymbols)).isEqualTo(expectedInteger);
  }
}