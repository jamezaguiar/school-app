package server.controller;

import com.google.gson.Gson;
import server.exception.StudentAlreadyExistsException;
import server.exception.TeacherAlreadyExistsException;
import server.model.Information;
import server.model.Student;
import server.model.Teacher;

public class SkeletonServant {
    Information information;
    Student student;
    Teacher teacher;
    private Servant servant = new Servant();

    private String packJson(String reply) {
        information = new Information(reply);
        String json = new Gson().toJson(information);
        return json;
    }

    public String createStudent(String args) {
        information = new Gson().fromJson(args, Information.class);
        try {
            student = servant.createStudent(information.getName(), information.getPassword(), information.getMatriculationOrSiape());
        } catch (StudentAlreadyExistsException e) {
            System.out.println(e.getMessage());
            return packJson(e.getMessage());
        }
        return packJson(student.toString());
    }

    public String createTeacher(String args) {
        information = new Gson().fromJson(args, Information.class);
        try {
            teacher = servant.createTeacher(information.getName(), information.getPassword(), information.getMatriculationOrSiape());
        } catch (TeacherAlreadyExistsException e) {
            return packJson(e.getMessage());
        }
        return packJson(teacher.toString());
    }



}
