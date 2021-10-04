package com.example.online_examination_system.Student_Operation;
import android.util.Log;
import com.example.online_examination_system.ConnectionProvider.ConnectionP;
import com.example.online_examination_system.Student_Bean.Student_Profile_Bean;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
public class Student_Profile_Operation
{
    public Student_Profile_Bean getData(String id)
    {
        Student_Profile_Bean student_profile_bean=new Student_Profile_Bean();
        try
        {
            Connection connection= ConnectionP.getCon();
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("select * from student where Id='"+id+"'");
            while (resultSet.next())
            {
                student_profile_bean.setSid(resultSet.getString("Id"));
                student_profile_bean.setFname(resultSet.getString("FirstName"));
                student_profile_bean.setMname(resultSet.getString("MiddleName"));
                student_profile_bean.setLname(resultSet.getString("LastName"));
                student_profile_bean.setFathername(resultSet.getString("FatherName"));
                student_profile_bean.setFoccopation(resultSet.getString("OccuptionF"));
                student_profile_bean.setFathernumber(resultSet.getString("FatherNo"));
                student_profile_bean.setMotherName(resultSet.getString("MotherName"));
                student_profile_bean.setMoccopation(resultSet.getString("OccuptionM"));
                student_profile_bean.setGender(resultSet.getString("Gender"));
                student_profile_bean.setDob(resultSet.getString("DOB"));
                student_profile_bean.setPhoneNo(resultSet.getString("PhoneNO"));
                student_profile_bean.setEmail(resultSet.getString("Email"));
                student_profile_bean.setSession1(resultSet.getString("session1"));
                student_profile_bean.setBoardx(resultSet.getString("BoardX"));
                student_profile_bean.setYearofpassingx(resultSet.getString("YearOfPassingX"));
                student_profile_bean.setPercentagex(resultSet.getString("PercentageX"));
                student_profile_bean.setBoardxi(resultSet.getString("BoardXII"));
                student_profile_bean.setYearofpassingxi(resultSet.getString("YearOfPassingXII"));
                student_profile_bean.setPercentagexi(resultSet.getString("PercentageXII"));
                student_profile_bean.setCast(resultSet.getString("Cast"));
                student_profile_bean.setPcity(resultSet.getString("CityP"));
                student_profile_bean.setPdistrict(resultSet.getString("DistrictP"));
                student_profile_bean.setPcode(resultSet.getString("PinCodeP"));
                student_profile_bean.setParea(resultSet.getString("AreaP"));
                student_profile_bean.setPlocality(resultSet.getString("LocalityP"));
                student_profile_bean.setPcountry(resultSet.getString("CountryP"));
                student_profile_bean.setPhouseno(resultSet.getString("HouseNoP"));
                student_profile_bean.setAadhaarno(resultSet.getString("AddharNo"));
                student_profile_bean.setCcity(resultSet.getString("CityL"));
                student_profile_bean.setCdistrict(resultSet.getString("DistrictL"));
                student_profile_bean.setCcode(resultSet.getString("PinCodeL"));
                student_profile_bean.setCarea(resultSet.getString("AreaL"));
                student_profile_bean.setClocality(resultSet.getString("LocalityL"));
                student_profile_bean.setCcountry(resultSet.getString("CountryL"));
                student_profile_bean.setChouseno(resultSet.getString("HouseNoL"));
            }
        }
        catch (Exception e)
        {
            Log.d("myapp",String.valueOf(e));
        }



        return student_profile_bean;
    }
}
