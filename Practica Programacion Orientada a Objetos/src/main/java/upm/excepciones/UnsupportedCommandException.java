package upm.excepciones;

public class UnsupportedCommandException extends RuntimeException {
    private static final String DESCRIPCION = "Invalid Command Exception. Comando fuera de rango";

    public UnsupportedCommandException(String detail) {
        super(DESCRIPCION + ">>>" + detail);
    }
}
