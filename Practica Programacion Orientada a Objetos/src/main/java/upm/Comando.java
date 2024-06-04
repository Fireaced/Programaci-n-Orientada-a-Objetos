package upm;

public interface Comando {
    void execute(String[] values);

    String name();

    String help();
}

