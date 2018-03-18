package za.co.tangentsolutions.myemployeemanager.providers;

import java.util.ArrayList;
import java.util.List;
import za.co.tangentsolutions.myemployeemanager.R;
import za.co.tangentsolutions.myemployeemanager.fragments.EmployeeFilterFragment;
import za.co.tangentsolutions.myemployeemanager.models.EmployeeFilterModel;

public class BasicEmployeeFiltersProviders {

    public List<EmployeeFilterModel> getBasicFilters(){
        EmployeeFilterModel femaleOnlyFilter = new EmployeeFilterModel();
        femaleOnlyFilter.setDisplayText("Show only women");
        femaleOnlyFilter.setValue("F");
        femaleOnlyFilter.setIcon(R.drawable.female_icon);
        femaleOnlyFilter.setKey(EmployeeFilterProvider.gender.getFilterKey());

        EmployeeFilterModel maleOnlyFilter = new EmployeeFilterModel();
        maleOnlyFilter.setDisplayText("Show only men");
        maleOnlyFilter.setValue("M");
        maleOnlyFilter.setIcon(R.drawable.male_icon);
        maleOnlyFilter.setKey(EmployeeFilterProvider.gender.getFilterKey());

        EmployeeFilterModel birthdayMonthFilter = new EmployeeFilterModel();
        birthdayMonthFilter.setDisplayText("Show birth days this month");
        birthdayMonthFilter.setValue("1");
        birthdayMonthFilter.setIcon(EmployeeFilterProvider.birthDateRange.getIcon());
        birthdayMonthFilter.setKey(EmployeeFilterProvider.birthDateRange.getFilterKey());

        EmployeeFilterModel startedThisMonthFilter = new EmployeeFilterModel();
        startedThisMonthFilter.setDisplayText("Show people who started this month");
        startedThisMonthFilter.setValue("1");
        startedThisMonthFilter.setIcon(EmployeeFilterProvider.startDate.getIcon());
        startedThisMonthFilter.setKey(EmployeeFilterProvider.startDate.getFilterKey());

        EmployeeFilterModel avancedFilter = new EmployeeFilterModel();
        avancedFilter.setDisplayText("Advanced filter");
        avancedFilter.setIcon(R.drawable.advanced_icon);

        List<EmployeeFilterModel> basicFilters = new ArrayList<>();
        basicFilters.add(femaleOnlyFilter);
        basicFilters.add(maleOnlyFilter);
        basicFilters.add(birthdayMonthFilter);
        basicFilters.add(startedThisMonthFilter);
        basicFilters.add(avancedFilter);

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

        customFilters.add(genderFilter);
        customFilters.add(raceFilter);
        customFilters.add(positionFilter);
        customFilters.add(startDateFilter);
        customFilters.add(userFilter);
        customFilters.add(birthDateRangeFilter);
        customFilters.add(emailContainsFilter);

        return customFilters;
    }
}