package za.co.tangentsolutions.myemployeemanager.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class EmployeeModel {
    private UserModel user;
    private PositionModel position;
    private String phone_number;
    private String email;
    private String github_user;
    private String birth_date;
    private String gender;
    private String race;
    private String years_worked;
    private int age;
    private int days_to_birthday;

    private List<ReviewModel>  employee_review;
    private List<NextOfKinModel> employee_next_of_kin;
    private String leave_remaining;
    private String next_review;
    private String id_number;
    private String physical_address;
    private String tax_number;
    private String personal_email;

    public void setModel(JSONObject employeeJson) throws JSONException {
        UserModel userModel = new UserModel();
        userModel.setModel(employeeJson.getJSONObject("user"));
        setUser(userModel);

        PositionModel positionModel = new PositionModel();
        positionModel.setModel(employeeJson.getJSONObject("position"));
        setPosition(positionModel);

        setPhone_number(employeeJson.getString("phone_number"));
        setEmail(employeeJson.getString("email"));
        setGithub_user(employeeJson.getString("github_user"));
        setBirth_date(employeeJson.getString("birth_date"));
        setGender(employeeJson.getString("gender"));
        setRace(employeeJson.getString("race"));
        setYears_worked(employeeJson.getString("years_worked"));
        setAge(employeeJson.getInt("age"));
        setDays_to_birthday(employeeJson.getInt("days_to_birthday"));

        if(employeeJson.has("next_review"))
            setNext_review(employeeJson.getString("next_review"));

        if(employeeJson.has("leave_remaining"))
            setLeave_remaining(employeeJson.getString("leave_remaining"));

        if(employeeJson.has("id_number"))
            setId_number(employeeJson.getString("id_number"));

        if(employeeJson.has("physical_address"))
            setPhysical_address(employeeJson.getString("physical_address"));

        if(employeeJson.has("tax_number"))
            setTax_number(employeeJson.getString("tax_number"));

        if(employeeJson.has("employee_review")){
            ReviewListModel reviewListModelListModel = new ReviewListModel();
            reviewListModelListModel.setModel(employeeJson.getJSONArray("employee_review"));
            setEmployee_review(reviewListModelListModel.getEmployee_review());
        }

        if(employeeJson.has("employee_next_of_kin")) {
            NextOFkinListModel nextOFkinListModel = new NextOFkinListModel();
            nextOFkinListModel.setModel(employeeJson.getJSONArray("employee_next_of_kin"));
            setEmployee_next_of_kin(nextOFkinListModel.getEmployee_next_of_kin());
        }
    }


    public String getId_number() {
        return id_number;
    }

    public void setId_number(String id_number) {
        this.id_number = id_number;
    }

    public String getPhysical_address() {
        return physical_address;
    }

    public void setPhysical_address(String physical_address) {
        this.physical_address = physical_address;
    }

    public String getTax_number() {
        return tax_number;
    }

    public void setTax_number(String tax_number) {
        this.tax_number = tax_number;
    }

    public String getPersonal_email() {
        return personal_email;
    }

    public void setPersonal_email(String personal_email) {
        this.personal_email = personal_email;
    }

    public String getNext_review() {
        return next_review;
    }

    public void setNext_review(String next_review) {
        this.next_review = next_review;
    }

    public List<ReviewModel>  getEmployee_review() {
        return employee_review;
    }

    public void setEmployee_review(List<ReviewModel>  employee_review) {
        this.employee_review = employee_review;
    }

    public List<NextOfKinModel> getEmployee_next_of_kin() {
        return employee_next_of_kin;
    }

    public void setEmployee_next_of_kin(List<NextOfKinModel> employee_next_of_kin) {
        this.employee_next_of_kin = employee_next_of_kin;
    }

    public String getLeave_remaining() {
        return leave_remaining;
    }

    public void setLeave_remaining(String leave_remaining) {
        this.leave_remaining = leave_remaining;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getGithub_user() {
        return github_user;
    }

    public void setGithub_user(String github_user) {
        this.github_user = github_user;
    }

    public String getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(String birth_date) {
        this.birth_date = birth_date;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getYears_worked() {
        return years_worked;
    }

    public void setYears_worked(String years_worked) {
        this.years_worked = years_worked;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getDays_to_birthday() {
        return days_to_birthday;
    }

    public void setDays_to_birthday(int days_to_birthday) {
        this.days_to_birthday = days_to_birthday;
    }

    public PositionModel getPosition() {
        return position;
    }

    public void setPosition(PositionModel position) {
        this.position = position;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}