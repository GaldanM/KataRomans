package exception;

public class SymbolRepeatedTooMuchException extends IllegalArgumentException {
  public SymbolRepeatedTooMuchException() {
    super("I, X, C and M cannot be repeated more than three times successively");
  }
}
