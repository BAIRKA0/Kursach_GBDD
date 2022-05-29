

public class Driver {

    private String license;
    private String first_name;
    private String last_name;

    public Driver(String license, String first_name, String last_name){
        this.license = license;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public String getLicense() {
        return license;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
}
