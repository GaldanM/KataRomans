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

  @Test
  void L_is_50() {
    testRomanSymbolsParsing("L", 50);
  }

  @Test
  void C_is_100() {
    testRomanSymbolsParsing("C", 100);
  }

  @Test
  void D_is_500() {
    testRomanSymbolsParsing("D", 500);
  }

  private void testRomanSymbolsParsing(String romanSymbols, Integer expectedInteger) {
    assertThat(KataRomans.parse(romanSymbols)).isEqualTo(expectedInteger);
  }
}