package za.co.tangentsolutions.myemployeemanager.providers;

public enum RestServiceProvider {

    authenticate("api-token-auth/"),
    userDetails("api/user/me/"),
    employee("api/employee/"),
    fullEmployee("employee/me/");

    private String path;

    RestServiceProvider(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}