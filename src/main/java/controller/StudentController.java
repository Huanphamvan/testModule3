package controller;

import model.Classroom;
import model.Student;
import service.ClassroomService;
import service.StudentService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "StudentController", value = "/student")
public class StudentController extends HttpServlet {
    StudentService studentService = new StudentService();
    ClassroomService classroomService = new ClassroomService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action){
            case "home":
                showHome(request,response);
                break;
            case "create":
                showFormCreate(request,response);
                break;
            case "delete":
                delete(request,response);
                break;
            case "edit":
                showFormEdit(request,response);
                break;
            case "search":
                searchStudent(request,response);
                break;


        }

    }
    private void searchStudent(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String s = request.getParameter("search");
        List<Student>studentList = studentService.findStudentByName(s);
        request.setAttribute("student",studentList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/student/search.jsp");
        dispatcher.forward(request,response);

//        response.sendRedirect("/product?action=home");
    }
    private void showFormEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("id",id);
        Student studentEdit = studentService.findStudentById(id);

        request.setAttribute("student",studentEdit);
        List<Classroom>classroomList= classroomService.findAll();
        request.setAttribute("classroomList",classroomList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/student/edit.jsp");
        dispatcher.forward(request,response);
    }
    private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        studentService.delete(id);
        response.sendRedirect("/student?action=home");
    }
    private void showFormCreate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/student/create.jsp");
        List<Classroom>classroomList = classroomService.findAll();
        request.setAttribute("classroomList",classroomList);
        dispatcher.forward(request,response);
    }
    private void showHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/student/home.jsp");
        List<Student> studentList = studentService.findAll();
        request.setAttribute("studentList",studentList);
        dispatcher.forward(request,response);

    }




    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action){
            case "create":
                addStudent(request,response);
                break;
            case "edit":
                editStudent(request,response);
                break;


        }
    }
    private void addStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String birthday = request.getParameter("birthday");
        String address= request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");

        int idClassroom = Integer.parseInt(request.getParameter("idClassroom"));
        Classroom classroom = new Classroom(idClassroom);
        Student student = new Student(id,name,email,birthday,address,phoneNumber,classroom);
        studentService.add(student);


        response.sendRedirect("/student?action=home");

    }
    private void editStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String birthday = request.getParameter("birthday");
        String address= request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");
        int idClassroom = Integer.parseInt(request.getParameter("idClassroom"));
        Classroom classroom = new Classroom(idClassroom);
        Student student = new Student(id,name,email,birthday,address,phoneNumber,classroom);
        studentService.edit(id,student);

        response.sendRedirect("/student?action=home");
    }



}