package upm.excepciones;

public class IncorrectAtrributeExcepcion extends RuntimeException {
    private static final String DESCRIPCION = "Incorrect Attribute Exception. Atributo incorrecto";

    public IncorrectAtrributeExcepcion(String detail) {
        super(DESCRIPCION + ">>>" + detail);
    }
}
