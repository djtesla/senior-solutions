package meetingrooms;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryMeetingRoomsRepository implements MeetingRoomsRepository{


    private List<MeetingRoom> meetingRooms = new ArrayList<>();

    @Override
    public void save(MeetingRoom meetingRoom) {
        meetingRooms.add(meetingRoom);
    }

    @Override
    public List<MeetingRoom> getMeetingRoomsList() {
        return meetingRooms;
    }

    @Override
    public void deleteAll() {
        meetingRooms = new ArrayList<>();
        System.out.println(meetingRooms);
    }

    @Override
    public List<MeetingRoom> getRoomPerName(String name) {
        return meetingRooms.stream()
                .filter(meetingRoom -> meetingRoom.getName().equals(name))
                .collect(Collectors.toList());
    }

    @Override
    public List<MeetingRoom> getRoomPerNamePart(String namePart) {
        return meetingRooms.stream()
                .filter(meetingRoom -> meetingRoom.getName().toLowerCase()
                .contains(namePart.toLowerCase()))
                .collect(Collectors.toList());
    }
}
