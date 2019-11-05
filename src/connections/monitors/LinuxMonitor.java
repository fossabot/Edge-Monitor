package connections.monitors;

import connections.CommandRunner;

import java.util.ArrayList;

public class LinuxMonitor {

    private String ip;
    private String username;
    private String password;

    private int cpuValue;
    private double ramValue;
    private double diskValue;
    private int networkIssues;
    private int updateIssues;
    private int securityIssues;

    private CommandRunner commandBroker = new CommandRunner();

    public LinuxMonitor(String ip, String username, String password) {
        this.ip = ip;
        this.username = username;
        this.password = password;
    }

    public String getIP() {
        return this.ip;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isReachable() {
        // TODO Connect to PC through wmi, return true if connected.
        return true;
    }

    public ArrayList<String> getData() {
        ArrayList<String> data = new ArrayList<>();


        data.add(this.getCPUValue());
        data.add(this.getRamValue());

        return data;
    }

    private String getCPUValue() {
        return this.commandBroker.runCommand("uptime");
    }

    private String getRamValue() {
        return this.commandBroker.runCommand("");
    }

}
