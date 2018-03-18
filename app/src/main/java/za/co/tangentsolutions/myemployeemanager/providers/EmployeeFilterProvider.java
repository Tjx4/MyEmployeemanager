package za.co.tangentsolutions.myemployeemanager.providers;


import za.co.tangentsolutions.myemployeemanager.R;

public enum EmployeeFilterProvider {
    race("race", R.drawable.race_icon),
    position("position", R.drawable.position_icon),
    startDate("start_date_range", R.drawable.start_date_icon),
    user("user", R.drawable.dark_user_icon),
    gender("gender", R.drawable.gender_icon),
    birthDateRange("birth_date_range", R.drawable.dob_icon),
    emailContains("email__contains", R.drawable.email_icon);

    private String filterKey;
    private int icon;

    EmployeeFilterProvider(String filterKey, int icon ) {
        this.filterKey = filterKey;
        this.icon = icon;
    }

    public String getFilterKey() {
        return filterKey;
    }

    public int getIcon() {
        return icon;
    }

}
