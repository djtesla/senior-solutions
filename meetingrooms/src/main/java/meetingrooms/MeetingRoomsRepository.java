package meetingrooms;

import java.util.List;

public interface MeetingRoomsRepository {

    void save(MeetingRoom meetingRoom);

    List<MeetingRoom> getMeetingRoomsList();

    void deleteAll();

    List<MeetingRoom> getRoomPerName(String name);


    List<MeetingRoom> getRoomPerNamePart(String namePart);


}