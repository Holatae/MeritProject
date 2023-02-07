package info.coolchatserver.merit;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        new File("students").mkdirs();
        Cli cli = new Cli();
        cli.Run();

    }
}
