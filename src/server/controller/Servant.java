package server.controller;

import server.exception.StudentAlreadyExistsException;
import server.exception.TeacherAlreadyExistsException;
import server.model.Student;
import server.model.Teacher;

import java.util.ArrayList;

public class Servant {
    private static ArrayList<Student> students = new ArrayList();
    private static ArrayList<Teacher> teachers = new ArrayList();

    Student createStudent(String name, String password, String matriculation) throws StudentAlreadyExistsException {
        for (Student s : students) {
            if (s.getMatriculation().equals(matriculation)) {
                throw new StudentAlreadyExistsException("Student Already Exists!");
            }
        }
        Student s = new Student(name, password, matriculation);
        students.add(s);
        return s;
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
