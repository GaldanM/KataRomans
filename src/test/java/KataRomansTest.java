import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

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

  @ParameterizedTest
  @CsvSource({"IV,4", "IX,9", "XL,40", "XC,90", "CD,400", "CM,900" })
  void double_char_symbols_have_a_value(String romanSymbolDoubleChar, String romanSymbolValue) {
    testRomanSymbolsParsing(romanSymbolDoubleChar, Integer.parseInt(romanSymbolValue));
  }

  @Test
  void zero_does_not_exists() {
    testRomanSymbolsParsingThrows("", "Zero does not exists");
  }

  @ParameterizedTest
  @ValueSource(strings = { "I", "X", "C", "M" })
  void symbols_that_cannot_be_repeated_more_than_three_times_successively(String romanSymbol) {
    testRomanSymbolsParsingThrows(romanSymbol.repeat(4), "I, X, C and M cannot be repeated more than three times successively");
  }

  @ParameterizedTest
  @ValueSource(strings = { "V", "L", "D" })
  void symbols_that_cannot_be_repeated(String romanSymbol) {
    testRomanSymbolsParsingThrows(romanSymbol.repeat(4), "V, L and D cannot be repeated");
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