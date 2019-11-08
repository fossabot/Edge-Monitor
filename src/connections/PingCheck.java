package connections;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class PingCheck extends Thread {

    private String host;
    private int timeout;
    private int sleep;
    private boolean reachable;

    public PingCheck(String host, int timeout, int sleep) {
        this.host = host;
        this.timeout = timeout;
        this.sleep = sleep;
    }

    public boolean isReachable() { return this.reachable; }

    public String getHost() { return this.host; }

    @Override
    public void run() {
        try {
            this.sendPingRequest(this.host);
            Thread.sleep(this.sleep);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendPingRequest(String ipAddress) {
        try {
            InetAddress device = InetAddress.getByName(ipAddress);
            this.reachable = device.isReachable(this.timeout);
        } catch (UnknownHostException u) {
            System.out.println("[ERROR] Device: " + this.host + " could not be found, unknown host.");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

}
