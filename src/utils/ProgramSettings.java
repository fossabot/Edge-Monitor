package utils;

public class ProgramSettings {

    private static boolean verbose = false;
    private static int port = 2087;
    private static boolean obscureWebServer;

    public boolean getVerbose() {
        return verbose;
    }

    public void setVerbose(boolean a) {
        verbose = a;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int b) {
        port = b;
    }

    public void obscureWebServer(boolean a) {
        obscureWebServer = a;
    }

    public String getWebServerName() {
        if (obscureWebServer)
            return "nginx";

        return "Edge Route Networks Web Server";
    }

}
