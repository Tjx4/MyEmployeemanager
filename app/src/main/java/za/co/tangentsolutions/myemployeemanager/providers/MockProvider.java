package za.co.tangentsolutions.myemployeemanager.providers;

import java.util.ArrayList;
import java.util.List;
import za.co.tangentsolutions.myemployeemanager.models.EmployeeModel;
import za.co.tangentsolutions.myemployeemanager.models.PositionModel;
import za.co.tangentsolutions.myemployeemanager.models.UserModel;

public class MockProvider {

    public EmployeeModel getMockEmployee(){
        EmployeeModel employeeModel = new EmployeeModel();
        return employeeModel;
    }

    public EmployeeModel getMockMaleEmployee(){
        EmployeeModel employeeModel = getMockEmployee();
        employeeModel.setGender("M");
        return employeeModel;
    }

    public EmployeeModel getMockFemaleEmployee(){
        EmployeeModel employeeModel = getMockEmployee();
        employeeModel.setGender("F");
        return employeeModel;
    }

    public EmployeeModel getMockFemaleEmployeeWithBirthdayThisMonth(){
        EmployeeModel employeeModel = getMockFemaleEmployee();
        employeeModel.setDays_to_birthday(5);
        return employeeModel;
    }

    public EmployeeModel getMockFemaleEmployeeWithBirthdayNotThisMonth(){
        EmployeeModel employeeModel = getMockFemaleEmployee();
        employeeModel.setDays_to_birthday(15);
        return employeeModel;
    }

    public EmployeeModel getMockMaleEmployeeWithBirthdayThisMonth(){
        EmployeeModel employeeModel = getMockMaleEmployee();
        employeeModel.setDays_to_birthday(5);
        return employeeModel;
    }

    public EmployeeModel getMockMaleEmployeeWithBirthdayNotThisMonth(){
        EmployeeModel employeeModel = getMockMaleEmployee();
        employeeModel.setDays_to_birthday(15);
        return employeeModel;
    }

    public int getMockRemailningDaysInMonth(){
        return 10;
    }

    public List<EmployeeModel> getMockEmployees(){
        UserModel user1 = new UserModel();
        user1.setUsername("pravin.gordhan");
        user1.setFirst_name("Pravin");
        user1.setLast_name("Gordan");
        user1.setIs_active(true);
        user1.setIs_staff(true);
        user1.setIs_superUser(true);

        UserModel user2 = new UserModel();
        user2.setUsername("tshepo.mahlaula");
        user2.setFirst_name("Tshepo");
        user2.setLast_name("Mahlaula");
        user2.setIs_active(true);
        user2.setIs_staff(true);
        user2.setIs_superUser(false);

        PositionModel position1 = new PositionModel();
        position1.setName("Finance");

        PositionModel position2 = new PositionModel();
        position2.setName("Developer");

        EmployeeModel employee1 = new EmployeeModel();
        employee1.setUser(user1);
        employee1.setEmail("pravin@axedmps.com");
        employee1.setPosition(position1);

        EmployeeModel employee2 = new EmployeeModel();
        employee2.setUser(user2);
        employee2.setEmail("tshepo@domain.tld");
        employee2.setPosition(position2);

        List<EmployeeModel> mockEmployees = new ArrayList<>();
        mockEmployees.add(employee1);
        mockEmployees.add(employee2);

        return mockEmployees;
    }
}