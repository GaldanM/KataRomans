import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class KataRomansTest {
  @ParameterizedTest
  @CsvSource({"I,1", "V,5", "X,10", "L,50", "C,100", "D,500", "M,1000"})
  void single_char_symbols_have_a_value(String romanSymbolSingleChar, String romanSymbolValue) {
    testRomanSymbolsParsing(romanSymbolSingleChar, Integer.parseInt(romanSymbolValue));
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
    testRomanSymbolsParsingThrows("", "Zero does not exists");
  }

  @Test
  void I_cannot_be_repeated_more_than_three_times_successively() {
    testRomanSymbolsParsingThrows("IIII", "I, X, C and M cannot be repeated more than three times successively");
  }

  @Test
  void X_cannot_be_repeated_more_than_three_times_successively() {
    testRomanSymbolsParsingThrows("XXXX", "I, X, C and M cannot be repeated more than three times successively");
  }

  @Test
  void C_cannot_be_repeated_more_than_three_times_successively() {
    testRomanSymbolsParsingThrows("CCCC", "I, X, C and M cannot be repeated more than three times successively");
  }

  @Test
  void M_cannot_be_repeated_more_than_three_times_successively() {
    testRomanSymbolsParsingThrows("MMMM", "I, X, C and M cannot be repeated more than three times successively");
  }

  @Test
  void V_cannot_be_repeated() {
    testRomanSymbolsParsingThrows("XVIV", "V, L and D cannot be repeated");
  }

  @Test
  void L_cannot_be_repeated() {
    testRomanSymbolsParsingThrows("CLXL", "V, L and D cannot be repeated");
  }

  @Test
  void D_cannot_be_repeated() {
    testRomanSymbolsParsingThrows("MDCD", "V, L and D cannot be repeated");
  }

  @Test
  void should_throw_on_non_recognized_symbol() {
    testRomanSymbolsParsingThrows("K", "Accepted symbols are: [I V X L C D M]");
  }

  private void testRomanSymbolsParsing(String romanSymbols, Integer expectedInteger) {
    assertThat(KataRomans.parse(romanSymbols)).isEqualTo(expectedInteger);
  }

  private void testRomanSymbolsParsingThrows(String input, String errorMessage) {
    assertThatThrownBy(() -> KataRomans.parse(input))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage(errorMessage);
  }
}