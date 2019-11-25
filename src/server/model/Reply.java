package server.model;

public class Reply {
    private String reply;

    public Reply(String reply) {
        this.reply = reply;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    @Override
    public String toString() {
        return "Reply{" +
                "reply='" + reply + '\'' +
                '}';
    }
}
