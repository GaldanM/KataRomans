public enum RomanSymbol {
  I(1),
  V(5),
  X(10);

  private final Integer value;

  RomanSymbol(Integer value) {
    this.value = value;
  }

  public Integer getValue() {
    return value;
  }
}