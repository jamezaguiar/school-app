package server.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import server.connection.HTTPHandler;
import server.model.Student;
import server.model.Teacher;

import java.io.IOException;

public class Servant {

    Student createStudent(String name, String password, String matriculation) throws IOException {
        JsonObject studentPost = new JsonObject();
        studentPost.addProperty("name", name);
        studentPost.addProperty("password", password);
        studentPost.addProperty("matriculation", matriculation);

        HTTPHandler http = new HTTPHandler();
        String response = http.POSTHandler(studentPost.toString(), "students");
        return new Gson().fromJson(response, Student.class);
    }

    Student readStudent(String matriculation) throws IOException {
        HTTPHandler http = new HTTPHandler();
        String response = http.GETHandler("students/" + matriculation);
        return new Gson().fromJson(response, Student.class);
    }

    Student[] listStudents() throws IOException {
        HTTPHandler http = new HTTPHandler();
        String response = http.GETHandler("students");
        return new Gson().fromJson(response, Student[].class);
    }


    String deleteStudent(String password, String matriculation) throws IOException {
        HTTPHandler http = new HTTPHandler();
        JsonObject studentDelete = new JsonObject();
        studentDelete.addProperty("password", password);
        studentDelete.addProperty("matriculation", matriculation);
        return http.DELETEHandler(studentDelete.toString(), "students");
    }

    Teacher createTeacher(String name, String password, String siape) throws IOException {
        JsonObject teacherPost = new JsonObject();
        teacherPost.addProperty("name", name);
        teacherPost.addProperty("password", password);
        teacherPost.addProperty("siape", siape);

        HTTPHandler http = new HTTPHandler();
        String response = http.POSTHandler(teacherPost.toString(), "teachers");

        return new Gson().fromJson(response, Teacher.class);
    }

    Teacher readTeacher(String siape) throws IOException {
        HTTPHandler http = new HTTPHandler();
        String response = http.GETHandler("teachers/" + siape);
        return new Gson().fromJson(response, Teacher.class);
    }

    Teacher[] listTeachers() throws IOException {
        HTTPHandler http = new HTTPHandler();
        String response = http.GETHandler("teachers");
        return new Gson().fromJson(response, Teacher[].class);
    }

    String deleteTeacher(String password, String siape) throws IOException {
        HTTPHandler http = new HTTPHandler();
        JsonObject teacherDelete = new JsonObject();
        teacherDelete.addProperty("password", password);
        teacherDelete.addProperty("siape", siape);
        return http.DELETEHandler(teacherDelete.toString(), "teachers");
    }


}
