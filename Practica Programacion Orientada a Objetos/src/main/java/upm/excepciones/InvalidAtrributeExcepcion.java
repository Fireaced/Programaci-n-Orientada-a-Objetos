package upm.excepciones;

public class InvalidAtrributeExcepcion extends RuntimeException {
    private static final String DESCRIPCION = "Invalid Attribute Exception. Atributo fuera de rango";

    public InvalidAtrributeExcepcion(String detail) {
        super(DESCRIPCION + ">>>" + detail);
    }
}
