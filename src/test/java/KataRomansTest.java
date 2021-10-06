import exception.SymbolDoesNotExistsException;
import exception.SymbolRepeatedException;
import exception.SymbolRepeatedTooMuchException;
import exception.ZeroDoesNotExistsException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
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
    testRomanSymbolsParsingThrows("", ZeroDoesNotExistsException.class);
  }

  @ParameterizedTest
  @ValueSource(strings = { "I", "X", "C", "M" })
  void symbols_that_cannot_be_repeated_more_than_three_times_successively(String romanSymbol) {
    testRomanSymbolsParsingThrows(romanSymbol.repeat(4), SymbolRepeatedTooMuchException.class);
  }

  @ParameterizedTest
  @ValueSource(strings = { "V", "L", "D" })
  void symbols_that_cannot_be_repeated(String romanSymbol) {
    testRomanSymbolsParsingThrows(romanSymbol.repeat(4), SymbolRepeatedException.class);
  }

  @Test
  void should_throw_on_non_recognized_symbol() {
    testRomanSymbolsParsingThrows("K", SymbolDoesNotExistsException.class);
  }

  private void testRomanSymbolsParsing(String romanSymbols, Integer expectedInteger) {
    assertThat(KataRomans.romanSymbolsToArabic(romanSymbols)).isEqualTo(expectedInteger);
  }

  private void testRomanSymbolsParsingThrows(String romanSymbols, Class<?> exceptionClass) {
    assertThatThrownBy(() -> KataRomans.romanSymbolsToArabic(romanSymbols))
        .isInstanceOf(exceptionClass);
  }
}