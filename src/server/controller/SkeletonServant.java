package server.controller;

import com.google.gson.Gson;
import server.exception.StudentAlreadyExistsException;
import server.exception.StudentNotFoundException;
import server.exception.TeacherAlreadyExistsException;
import server.exception.TeacherNotFoundException;
import server.model.Information;
import server.model.Reply;
import server.model.Student;
import server.model.Teacher;

import java.io.IOException;
import java.util.Arrays;

public class SkeletonServant {
    Information information;
    Reply reply;
    Student student;
    Student[] students;
    Teacher teacher;
    String result;
    private Servant servant = new Servant();

    private String packJson(String reply) {
        this.reply = new Reply(reply);
        return new Gson().toJson(this.reply);
    }

    public String createStudent(String args) {
        information = new Gson().fromJson(args, Information.class);
        try {
            student = servant.createStudent(information.getName(), information.getPassword(), information.getMatriculationOrSiape());
        } catch (StudentAlreadyExistsException | IOException e) {
            System.out.println("Exception: " + e.getMessage());
            return packJson("Exception: " + e.getMessage());
        }
        return packJson(student.toString());
    }

    public String readStudent(String args) {
        information = new Gson().fromJson(args, Information.class);
        try {
            student = servant.readStudent(information.getMatriculationOrSiape());
        } catch (StudentNotFoundException | IOException e) {
            System.out.println("Exception: " + e.getMessage());
            return packJson("Exception: " + e.getMessage());
        }
        return packJson(student.toString());
    }

    public String listStudents(String args) throws IOException {
        information = new Gson().fromJson(args, Information.class);
        try {
            students = servant.listStudents();
        }catch (IOException e){
            System.out.println("Exception: " + e.getMessage());
            return packJson("Exception: " + e.getMessage());
        }

        return packJson(Arrays.toString(students));
    }
    public String deleteStudent(String args) {
        information = new Gson().fromJson(args, Information.class);
        try {
            result = servant.deleteStudent(information.getPassword(),information.getMatriculationOrSiape());
        } catch (StudentNotFoundException e){
            System.out.println("Exception: " + e.getMessage());
            return packJson("Exception: " + e.getMessage());
        }
        return packJson(result);
    }

    public String createTeacher(String args) {
        information = new Gson().fromJson(args, Information.class);
        try {
            teacher = servant.createTeacher(information.getName(), information.getPassword(), information.getMatriculationOrSiape());
        } catch (TeacherAlreadyExistsException | IOException e) {
            System.out.println("Exception: " + e.getMessage());
            return packJson("Exception: " + e.getMessage());
        }
        return packJson(teacher.toString());
    }

    public String readTeacher(String args) {
        information = new Gson().fromJson(args, Information.class);
        try {
            teacher = servant.readTeacher(information.getMatriculationOrSiape());
        } catch (TeacherNotFoundException e) {
            System.out.println("Exception: " + e.getMessage());
            return packJson("Exception: " + e.getMessage());
        }
        return packJson(teacher.toString());
    }

    public String deleteTeacher(String args) {
        information = new Gson().fromJson(args, Information.class);
        try {
            result = servant.deleteTeacher(information.getPassword(),information.getMatriculationOrSiape());
        } catch (TeacherNotFoundException e){
            System.out.println("Exception: " + e.getMessage());
            return packJson("Exception: " + e.getMessage());
        }
        return packJson(result);
    }


}
