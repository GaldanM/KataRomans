import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class KataRomansTest {
  @Test
  void I_is_1() {
    String oneI = "I";

    assertThat(KataRomans.parse(oneI)).isEqualTo(1);
  }
}