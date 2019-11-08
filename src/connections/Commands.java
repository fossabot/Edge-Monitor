package connections;

public class Commands {

    public String getLinuxCommand(String command) {
        switch(command) {
            case "get-cpu-value":
                return "";
            default:
                return null;
        }
    }

    public String getWindowsCommand(String command) {
        switch(command) {
            case "get-cpu-value":
                return "";
            default:
                return null;
        }
    }

}
