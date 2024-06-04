package upm.consola;

import upm.Comando;
import upm.comandos.LogIn;
import upm.excepciones.UnsupportedCommandException;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CommandLineInterface {
    private final View view;
    private static final String EXIT_NAME = "exit";
    private static final String EXIT_HELP = ": termina la ejecución.";
    private static final String HELP_NAME = "help";
    private static final String HELP_HELP = ": muestra la ayuda.";
    private final Map<String, Comando> comandos;


    public CommandLineInterface(View view) {
        this.view = view;
        this.comandos = new HashMap<>();
    }

    public void add(Comando comando) {
        this.comandos.put(comando.name(), comando);
    }

    public boolean runCommands() {
        Scanner scanner = new Scanner(System.in).useDelimiter("[:,\\r\\n]");
        boolean exit = false;
        while (!exit) {
            exit = runCommand(scanner);
        }
        return true;
    }

    private boolean runCommand(Scanner scanner) {
        this.view.showCommand(LogIn.getUsuarioLogueado());
        String commandName = scanner.next();
        boolean exit = false;
        if (HELP_NAME.equals(commandName)) {
            this.help();
        } else if (EXIT_NAME.equals(commandName)) {
            exit = true;
        } else {
            if (this.comandos.containsKey(commandName)) {
                this.comandos.get(commandName).execute(scanner.next().split(";"));
            } else {
                throw new UnsupportedCommandException("Comando '" + commandName + "' no existe.");
            }
        }
        return exit;
    }

    private void help() {
        this.view.show(EXIT_NAME + EXIT_HELP);
        this.view.show(HELP_NAME + HELP_HELP);
        for (Comando command : this.comandos.values()) {
            this.view.show(command.name() + command.help());
        }
    }


}
