package server.controller;

import com.google.gson.Gson;
import server.exception.StudentAlreadyExistsException;
import server.model.Information;
import server.model.Student;

public class SkeletonServant {
    Information information;
    Student student;
    private Servant servant = new Servant();

    private String packJson(String reply) {
        information = new Information(reply);
        String json = new Gson().toJson(reply);
        json = new Gson().toJson(information);
        return json;
    }

    public String createStudent(String args) throws StudentAlreadyExistsException {
        information = new Gson().fromJson(args, Information.class);
        student = servant.createStudent(information.getName(), information.getPassword(),information.getMatriculationOrSiape());
        System.out.println(student);
        return packJson(student.toString());
    }

}
