package za.co.tangentsolutions.myemployeemanager.providers;

import java.util.ArrayList;
import java.util.List;
import za.co.geartronix.proto_app.Models.EmployeeFilterModel;
import za.co.geartronix.proto_app.R;

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
}