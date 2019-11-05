import connections.ConnectionManager;
import utils.ProgramSettings;
import web.WebThread;

public class Launcher {

    public static void main(String args[]) {
        try {
            int port = Integer.parseInt(args[0]);
            boolean verbose = Boolean.parseBoolean(args[1]);
            boolean obscureWebServer = Boolean.parseBoolean(args[2]);

            System.out.print("[INFO] Configuring program settings...");
            ProgramSettings programSettings = new ProgramSettings();
            programSettings.setVerbose(verbose);
            programSettings.setPort(port);
            programSettings.obscureWebServer(obscureWebServer);
            System.out.println("Done!");

            System.out.print("[INFO] Configuring monitoring connections...");
            ConnectionManager connectionManager = new ConnectionManager();
            connectionManager.importPingChecks();
            connectionManager.importDeviceMonitors();
            connectionManager.start();
            System.out.println("Done!");

            System.out.print("[INFO] Starting web interface...");
            WebThread webInterface = new WebThread();
            webInterface.start();
            System.out.println("Done!");
        } catch (ArrayIndexOutOfBoundsException a) {
            System.out.println("Command line arguments not specified. [(int) Web server Port] [(boolean) Verbose Logging] [(boolean) Obscure Web server]");
            System.exit(0);
        }
    }
}
