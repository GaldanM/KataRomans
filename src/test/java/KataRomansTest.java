import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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

  @Test
  void M_is_1000() {
    testRomanSymbolsParsing("M", 1000);
  }

  @Test
  void symbols_should_add() {
    testRomanSymbolsParsing("XVI", 16);
  }

  @Test
  void IV_is_4() {
    testRomanSymbolsParsing("IV", 4);
  }

  @Test
  void IX_is_9() {
    testRomanSymbolsParsing("IX", 9);
  }

  @Test
  void XL_is_40() {
    testRomanSymbolsParsing("XL", 40);
  }

  @Test
  void XC_is_90() {
    testRomanSymbolsParsing("XC", 90);
  }

  @Test
  void CD_is_400() {
    testRomanSymbolsParsing("CD", 400);
  }

  @Test
  void CM_is_900() {
    testRomanSymbolsParsing("CM", 900);
  }

  @Test
  void zero_does_not_exists() {
    assertThatThrownBy(() -> KataRomans.parse(""))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("Zero does not exists");
  }

  private void testRomanSymbolsParsing(String romanSymbols, Integer expectedInteger) {
    assertThat(KataRomans.parse(romanSymbols)).isEqualTo(expectedInteger);
  }
}