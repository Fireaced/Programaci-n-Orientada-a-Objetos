package upm.excepciones;

public class NonExistentAtributeException extends RuntimeException {
    private static final String DESCRIPCION = "Non-Existent Argument Exception. Argumento inexistente";

    public NonExistentAtributeException(String detail) {
        super(DESCRIPCION + ">>>" + detail);
    }
}
