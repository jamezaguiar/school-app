package server.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import server.connection.HTTPHandler;
import server.exception.StudentAlreadyExistsException;
import server.exception.StudentNotFoundException;
import server.exception.TeacherAlreadyExistsException;
import server.exception.TeacherNotFoundException;
import server.model.Student;
import server.model.Teacher;

import java.io.IOException;
import java.util.ArrayList;

public class Servant {

    private static ArrayList<Student> students = new ArrayList();
    private static ArrayList<Teacher> teachers = new ArrayList();

    Student createStudent(String name, String password, String matriculation) throws StudentAlreadyExistsException, IOException {
        JsonObject studentPost = new JsonObject();
        studentPost.addProperty("name", name);
        studentPost.addProperty("password", password);
        studentPost.addProperty("matriculation", matriculation);

        HTTPHandler http = new HTTPHandler();
        String response = http.POSTHandler(studentPost.toString(), "students");

        return new Gson().fromJson(response, Student.class);
    }

    Student readStudent(String matriculation) throws StudentNotFoundException, IOException {
        HTTPHandler http = new HTTPHandler();
        String response = http.GETHandler("students/" + matriculation);
        return new Gson().fromJson(response, Student.class);
    }

    Student[] listStudents() throws IOException {
        HTTPHandler http = new HTTPHandler();
        String response = http.GETHandler("students");
        return new Gson().fromJson(response, Student[].class);
    }


    String deleteStudent(String password, String matriculation) throws StudentNotFoundException, IOException {
        HTTPHandler http = new HTTPHandler();
        JsonObject studentDelete = new JsonObject();
        studentDelete.addProperty("password", password);
        studentDelete.addProperty("matriculation", matriculation);
        return http.DELETEHandler(studentDelete.toString(), "students");
    }

    Teacher createTeacher(String name, String password, String siape) throws TeacherAlreadyExistsException, IOException {
        JsonObject teacherPost = new JsonObject();
        teacherPost.addProperty("name", name);
        teacherPost.addProperty("password", password);
        teacherPost.addProperty("siape", siape);

        HTTPHandler http = new HTTPHandler();
        String response = http.POSTHandler(teacherPost.toString(), "teachers");

        return new Gson().fromJson(response, Teacher.class);
    }

    Teacher readTeacher(String siape) throws TeacherNotFoundException, IOException {
        HTTPHandler http = new HTTPHandler();
        String response = http.GETHandler("teachers/" + siape);
        return new Gson().fromJson(response, Teacher.class);
    }

    Teacher[] listTeachers() throws IOException {
        HTTPHandler http = new HTTPHandler();
        String response = http.GETHandler("teachers");
        return new Gson().fromJson(response, Teacher[].class);
    }

    String deleteTeacher(String password, String siape) throws TeacherNotFoundException, IOException {
        HTTPHandler http = new HTTPHandler();
        JsonObject teacherDelete = new JsonObject();
        teacherDelete.addProperty("password", password);
        teacherDelete.addProperty("siape", siape);
        return http.DELETEHandler(teacherDelete.toString(), "teachers");
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        Servant.students = students;
    }

    public ArrayList<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(ArrayList<Teacher> teachers) {
        Servant.teachers = teachers;
    }
}
