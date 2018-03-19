package za.co.tangentsolutions.myemployeemanager.providers;

import java.util.ArrayList;
import java.util.List;
import za.co.tangentsolutions.myemployeemanager.R;
import za.co.tangentsolutions.myemployeemanager.activities.BaseActivity;
import za.co.tangentsolutions.myemployeemanager.fragments.EmployeeFilterFragment;
import za.co.tangentsolutions.myemployeemanager.models.EmployeeFilterModel;

public class BasicEmployeeFiltersProviders {

    private BaseActivity activity;

    public BasicEmployeeFiltersProviders(BaseActivity activity) {
        this.activity = activity;
    }

    public List<EmployeeFilterModel> getBasicFilters(){
        EmployeeFilterModel femaleOnlyFilter = new EmployeeFilterModel();
        femaleOnlyFilter.setDisplayText("Show only women");
        femaleOnlyFilter.setValue("F");
        femaleOnlyFilter.setDisplayText(activity.getString(R.string.women_only_custom_filter_display));
        femaleOnlyFilter.setIcon(R.drawable.female_icon);
        femaleOnlyFilter.setKey(EmployeeFilterProvider.gender.getFilterKey());

        EmployeeFilterModel maleOnlyFilter = new EmployeeFilterModel();
        maleOnlyFilter.setDisplayText("Show only men");
        maleOnlyFilter.setValue("M");
        maleOnlyFilter.setDisplayText(activity.getString(R.string.men_only_custom_filter_display));
        maleOnlyFilter.setIcon(R.drawable.male_icon);
        maleOnlyFilter.setKey(EmployeeFilterProvider.gender.getFilterKey());

        EmployeeFilterModel birthdayMonthFilter = new EmployeeFilterModel();
        birthdayMonthFilter.setDisplayText("Show birth days this month");
        birthdayMonthFilter.setValue("1");
        maleOnlyFilter.setDisplayText(activity.getString(R.string.birth_days_custom_filter_display));
        birthdayMonthFilter.setIcon(EmployeeFilterProvider.birthDateRange.getIcon());
        birthdayMonthFilter.setKey(EmployeeFilterProvider.birthDateRange.getFilterKey());

        EmployeeFilterModel startedThisMonthFilter = new EmployeeFilterModel();
        startedThisMonthFilter.setDisplayText("Show people who started this month");
        startedThisMonthFilter.setValue("1");
        maleOnlyFilter.setDisplayText(activity.getString(R.string.this_month_custom_filter_display));
        startedThisMonthFilter.setIcon(EmployeeFilterProvider.startDate.getIcon());
        startedThisMonthFilter.setKey(EmployeeFilterProvider.startDate.getFilterKey());

        EmployeeFilterModel avancedFilter = new EmployeeFilterModel();
        avancedFilter.setDisplayText("Custom filter");
        avancedFilter.setDisplayText(activity.getString(R.string.custom_filter_display));
        avancedFilter.setIcon(R.drawable.advanced_icon);

        EmployeeFilterModel noFilter = new EmployeeFilterModel();
        avancedFilter.setDisplayText("Show all");
        avancedFilter.setDisplayText(activity.getString(R.string.showing_all));
        avancedFilter.setIcon(R.drawable.all_icon);

        List<EmployeeFilterModel> basicFilters = new ArrayList<>();
        basicFilters.add(femaleOnlyFilter);
        basicFilters.add(maleOnlyFilter);
        basicFilters.add(birthdayMonthFilter);
        basicFilters.add(startedThisMonthFilter);
        basicFilters.add(avancedFilter);
        basicFilters.add(noFilter);

        return basicFilters;
    }

    public List<EmployeeFilterModel> getCustomFilters(EmployeeFilterFragment filterFragment) {
        List<EmployeeFilterModel> customFilters = new ArrayList<>();

        EmployeeFilterModel genderFilter = new EmployeeFilterModel();
        genderFilter.setValue(filterFragment.getGenderFilter());
        genderFilter.setKey(EmployeeFilterProvider.gender.getFilterKey());

        EmployeeFilterModel raceFilter = new EmployeeFilterModel();
        raceFilter.setValue(filterFragment.getRaceFilter());
        raceFilter.setKey(EmployeeFilterProvider.race.getFilterKey());

        EmployeeFilterModel positionFilter = new EmployeeFilterModel();
        positionFilter.setValue(filterFragment.getPositionFilter());
        positionFilter.setKey(EmployeeFilterProvider.position.getFilterKey());

        EmployeeFilterModel startDateFilter = new EmployeeFilterModel();
        startDateFilter.setValue(filterFragment.getStartDateRangeFilter());
        startDateFilter.setKey(EmployeeFilterProvider.startDate.getFilterKey());

        EmployeeFilterModel userFilter = new EmployeeFilterModel();
        userFilter.setValue(filterFragment.getUserFilter());
        userFilter.setKey(EmployeeFilterProvider.user.getFilterKey());

        EmployeeFilterModel birthDateRangeFilter = new EmployeeFilterModel();
        birthDateRangeFilter.setValue(filterFragment.getBirthDateRangeFilter());
        birthDateRangeFilter.setKey(EmployeeFilterProvider.birthDateRange.getFilterKey());

        EmployeeFilterModel emailContainsFilter = new EmployeeFilterModel();
        emailContainsFilter.setValue(filterFragment.emailContainsFilter());
        emailContainsFilter.setKey(EmployeeFilterProvider.emailContains.getFilterKey());

        if(!genderFilter.getValue().isEmpty())
            customFilters.add(genderFilter);

        if(!raceFilter.getValue().isEmpty())
            customFilters.add(raceFilter);

        if(!positionFilter.getValue().isEmpty())
            customFilters.add(positionFilter);

        if(!startDateFilter.getValue().isEmpty())
            customFilters.add(startDateFilter);

        if(!userFilter.getValue().isEmpty())
            customFilters.add(userFilter);

        if(!birthDateRangeFilter.getValue().isEmpty())
            customFilters.add(birthDateRangeFilter);

        if(!emailContainsFilter.getValue().isEmpty())
            customFilters.add(emailContainsFilter);

        return customFilters;
    }
}