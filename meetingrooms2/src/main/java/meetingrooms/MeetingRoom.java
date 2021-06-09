package meetingrooms;

public class MeetingRoom {

    public long id;
    private String name;
    private int length;
    private int width;


    public MeetingRoom() {

    }

    public MeetingRoom(String name, int length, int width) {
        this.name = name;
        this.length = length;
        this.width = width;
    }

    public MeetingRoom(long id, String name, int length, int width) {
        this.id = id;
        this.name = name;
        this.length = length;
        this.width = width;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public int getArea () {
        return length * width;
    }

    @Override
    public String toString() {
        return
                "id=" + id + ", name='" + name + ", length=" + length + ", width=" + width;

    }
}
