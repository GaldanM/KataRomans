package exception;

public class ZeroDoesNotExistsException extends IllegalArgumentException {
  public ZeroDoesNotExistsException() {
    super("Zero does not exists");
  }
}
