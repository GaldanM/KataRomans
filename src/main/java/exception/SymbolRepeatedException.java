package exception;

public class SymbolRepeatedException extends IllegalArgumentException {
  public SymbolRepeatedException() {
    super("V, L and D cannot be repeated");
  }
}
