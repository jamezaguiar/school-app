package client.model;

public class Message {
    private int messageType;
    private int requestId;
    private String objectReference;
    private String methodId;
    private String arguments;

    public Message(int messageType, int requestId, String objectReference, String methodId, String arguments) {
        this.messageType = messageType;
        this.requestId = requestId;
        this.objectReference = objectReference;
        this.methodId = methodId;
        this.arguments = arguments;
    }

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public String getObjectReference() {
        return objectReference;
    }

    public void setObjectReference(String objectReference) {
        this.objectReference = objectReference;
    }

    public String getMethodId() {
        return methodId;
    }

    public void setMethodId(String methodId) {
        this.methodId = methodId;
    }

    public String getArguments() {
        return arguments;
    }

    public void setArguments(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageType=" + messageType +
                ", requestId=" + requestId +
                ", objectReference='" + objectReference + '\'' +
                ", methodId='" + methodId + '\'' +
                ", arguments='" + arguments + '\'' +
                '}';
    }

}
