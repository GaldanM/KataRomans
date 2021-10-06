package exception;

public class SymbolDoesNotExistsException extends IllegalArgumentException {
  public SymbolDoesNotExistsException() {
    super("Accepted symbols are: [I V X L C D M]");
  }
}
