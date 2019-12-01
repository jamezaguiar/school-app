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
import java.net.MalformedURLException;
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

    Student readStudent(String matriculation) throws StudentNotFoundException {
        for (Student s : students) {
            if (s.getMatriculation().equals(matriculation)) {
                return s;
            }
        }
        throw new StudentNotFoundException("Student Not Found!");
    }

    String deleteStudent(String password, String matriculation) throws StudentNotFoundException {
        for (Student s : students) {
            if (s.getPassword().equals(password) && s.getMatriculation().equals(matriculation)) {
                String name= s.getName();
                students.remove(s);
                return "Student " + name + " removed successfully!";
            }
        }
        throw new StudentNotFoundException("Student Not Found!");
    }

    Teacher createTeacher(String name, String password, String siape) throws TeacherAlreadyExistsException {
        for (Teacher t : teachers) {
            if (t.getSiape().equals(siape)) {
                throw new TeacherAlreadyExistsException("Teacher Already Exists!");
            }
        }
        Teacher t = new Teacher(name, password, siape);
        teachers.add(t);
        return t;
    }

    Teacher readTeacher(String siape) throws TeacherNotFoundException {
        for (Teacher t : teachers) {
            if (t.getSiape().equals(siape)) {
                return t;
            }
        }
        throw new TeacherNotFoundException("Teacher Not Found!");
    }

    String deleteTeacher(String password, String siape) throws TeacherNotFoundException {
        for (Teacher t : teachers) {
            if (t.getPassword().equals(password) && t.getSiape().equals(siape)) {
                String name= t.getName();
                students.remove(t);
                return "Teacher " + name + " removed successfully!";
            }
        }
        throw new TeacherNotFoundException("Teacher Not Found!");
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
