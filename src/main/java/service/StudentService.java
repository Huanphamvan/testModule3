package service;

import model.Classroom;
import model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentService implements IStudentService<Student> {
    private Connection connection = ConnectToMySQL.getConnection();

    @Override
    public void add(Student student) {
        String sql = "insert into student( id,name, email,birthday,address, phoneNumber, idClassroom) values (?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, student.getId());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setString(3, student.getEmail());
            preparedStatement.setString(4, student.getBirthday());
            preparedStatement.setString(5, student.getAddress());
            preparedStatement.setString(6, student.getPhoneNumber());

            preparedStatement.setInt(7, student.getClassroom().getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(int id) {
        String sql = "delete from student where id = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void edit(int id, Student student) {
        String sql = "update student set name = ?,email = ?,birthday = ?,address = ?,phoneNumber = ?,idClassroom = ? where id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getEmail());
            preparedStatement.setString(3, student.getBirthday());
            preparedStatement.setString(4, student.getAddress());
            preparedStatement.setString(5, student.getPhoneNumber());

            preparedStatement.setInt(6, student.getClassroom().getId());
            preparedStatement.setInt(7, student.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Student> findAll() {
        List<Student>studentList = new ArrayList<>();
        String sql = "select s.*,d.name as nameClassroom from student s join classroom d on d.id = s.idClassroom;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String birthday = resultSet.getString("birthday");
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phoneNumber");

                int idClassroom = resultSet.getInt("idClassroom");
                String nameClassroom = resultSet.getString("nameClassroom");
                Classroom classroom = new Classroom(idClassroom,nameClassroom);
                Student student = new Student(id,name,email,birthday,address,phoneNumber,classroom);
                studentList.add(student);


            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return studentList;
    }

    public Student findStudentById(int id) {
        String sql = "select s.*,d.name as nameClassroom from student s join classroom d on d.id = s.idClassroom" +
                " where s.id= ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String birthday = resultSet.getString("birthday");
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phoneNumber");
                int idClassroom = resultSet.getInt("idClassroom");
                String nameClassroom = resultSet.getString("nameClassroom");
                Classroom classroom = new Classroom(idClassroom,nameClassroom);
                Student student = new Student(id,name,email,birthday,address,phoneNumber,classroom);

                return student;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Student> findStudentByName(String s) {
        List<Student> studentList = new ArrayList<>();
        String sql = "select s.*,d.name as nameClassroom from student s join classroom d on d.id = s.idClassroom where s.name like ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, s + "%" );
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String birthday = resultSet.getString("birthday");
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phoneNumber");
                int idClassroom = resultSet.getInt("idClassroom");
                String nameClassroom = resultSet.getString("nameClassroom");
                Classroom classroom = new Classroom(idClassroom,nameClassroom);
                Student student = new Student(id,name,email,birthday,address,phoneNumber,classroom);
                studentList.add(student);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentList;
    }


}
