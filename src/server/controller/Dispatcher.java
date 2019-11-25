package server.controller;

import server.model.Message;

import java.lang.reflect.Method;

public class Dispatcher {
    SkeletonServant skeleton = new SkeletonServant();

    public String invoke(Message request) throws NoSuchMethodException {

        Method method = null;
        String reply = null;

        try {

            Class objRef = Class.forName("server.controller.Skeleton" + request.getObjectReference());
            String methodName = request.getMethodId();
            method = objRef.getMethod(methodName, String.class);
            System.out.println((request.getArguments()));
            reply = (String) (method.invoke(objRef.newInstance(), request.getArguments()));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return reply;
    }

}
