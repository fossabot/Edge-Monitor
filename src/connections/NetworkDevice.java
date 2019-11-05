package connections;

import connections.monitors.LinuxMonitor;
import connections.monitors.WindowsMonitor;

import java.util.ArrayList;

public class NetworkDevice extends Thread {

    private String deviceType;
    private int timeout;

    private int cpuValue;
    private double ramValue;
    private double diskValue;
    private int networkIssues;
    private int updateIssues;
    private int securityIssues;

    private Object monitor; // Are we monitoring a Windows or Linux device?

    public NetworkDevice(String ip, String deviceType, String username, String password, String timeout) {
        this.deviceType = deviceType;
        this.timeout = Integer.parseInt(timeout);

        if(this.deviceType.equalsIgnoreCase("Windows"))
            this.monitor = new WindowsMonitor(ip, username, password);

        else if(this.deviceType.equalsIgnoreCase("Linux"))
            this.monitor = new LinuxMonitor(ip, username, password);

        else {
            System.out.println("[ERROR] Unsupported device type: " + this.deviceType + ". Exiting...");
            System.exit(0);
        }
    }

    @Override
    public void run() {
        ArrayList<String> data;

        try {
            if(monitor instanceof WindowsMonitor)
                data = ((WindowsMonitor) this.monitor).getData();

            else if(monitor instanceof LinuxMonitor)
                data = ((LinuxMonitor) this.monitor).getData();

            Thread.sleep(this.timeout * 1000); // Convert to minutes.
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isReachable() {
        if(monitor instanceof WindowsMonitor)
            return ((WindowsMonitor) this.monitor).isReachable();

        else if(monitor instanceof LinuxMonitor)
            return ((LinuxMonitor) this.monitor).isReachable();

        return false;
    }

    public String getIP() {
        if(monitor instanceof WindowsMonitor)
            return ((WindowsMonitor) this.monitor).getIP();

        else if(monitor instanceof LinuxMonitor)
            return ((LinuxMonitor) this.monitor).getIP();

        return null;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public String getUsername() {
        if(monitor instanceof WindowsMonitor)
            return ((WindowsMonitor) this.monitor).getUsername();

        else if(monitor instanceof LinuxMonitor)
            return ((LinuxMonitor) this.monitor).getUsername();

        return null;
    }

    public void setUsername(String username) {
        if(monitor instanceof WindowsMonitor)
            ((WindowsMonitor) this.monitor).setUsername(username);

        else if(monitor instanceof LinuxMonitor)
            ((LinuxMonitor) this.monitor).setUsername(username);
    }

    public void setPassword(String password) {
        if(monitor instanceof WindowsMonitor)
            ((WindowsMonitor) this.monitor).setPassword(password);

        else if(monitor instanceof LinuxMonitor)
            ((LinuxMonitor) this.monitor).setPassword(password);
    }

}
