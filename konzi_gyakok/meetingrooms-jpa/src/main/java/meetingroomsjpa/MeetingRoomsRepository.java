package meetingroomsjpa;

import java.util.List;

public interface MeetingRoomsRepository {

    MeetingRoom save(MeetingRoom meetingRoom);
    List<String> getMeetingRoomsOrderedByName();
    List<String> getEverySecondMeetingRoom();
    List<MeetingRoom> getMeetingRooms();
    List<MeetingRoom> getExactMeetingRoomByName(String name);
    List<MeetingRoom> getMeetingRoomsByPrefix(String nameOrPrefix);
    void deleteAll();
}
