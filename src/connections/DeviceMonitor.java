package connections;

public class DeviceMonitor extends Thread {

    private String ip;
    private String deviceType;
    private String username;
    private String password;
    private int timeout;
    private boolean reachable = false;

    private int cpuValue;
    private double ramValue;
    private double diskValue;

    private int networkIssues;
    private int updateIssues;
    private int securityIssues;

    public DeviceMonitor(String ip, String deviceType, String username, String password, String timeout) {
        this.ip = ip;
        this.deviceType = deviceType;
        this.timeout = Integer.parseInt(timeout);
        this.username = username;
        this.password = password;


        if(this.deviceType.equalsIgnoreCase("Windows"))
            this.deviceType = "Windows";

        else if(this.deviceType.equalsIgnoreCase("Linux"))
            this.deviceType = "Windows";

        else {
            System.out.println("[ERROR] Unsupported device type: " + this.deviceType + ". Exiting...");
            System.exit(0);
        }
    }

    public String getIP() { return this.ip; }

    public String getDeviceType() {
        return this.deviceType;
    }

    public String getUsername() { return this.username; }

    public void setUsername(String username) { this.username = username; }

    public void setPassword(String password) { this.password = password; }

    public boolean isReachable() {
        return this.reachable;
    }

    @Override
    public void run() {



        try {
            Thread.sleep(this.timeout);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }


}
