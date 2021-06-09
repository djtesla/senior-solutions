package meetingrooms;

import java.text.Collator;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class MeetingRoomsService {

    private MeetingRoomsRepository meetingRoomsRepository;

    public MeetingRoomsService() {
    }

    public MeetingRoomsService(MeetingRoomsRepository meetingRoomsRepository) {
        this.meetingRoomsRepository = meetingRoomsRepository;
    }

    public void setMeetingRoomsRepository(MeetingRoomsRepository meetingRoomsRepository) {
        this.meetingRoomsRepository = meetingRoomsRepository;
    }


    public void save(MeetingRoom meetingRoom) {
        meetingRoomsRepository.save(meetingRoom);
    }

    public void deleteAll() {
        meetingRoomsRepository.deleteAll();
    }


    public List<MeetingRoom> getMeetingRoomsList() {
        return meetingRoomsRepository.getMeetingRoomsList();
    }

    public List<MeetingRoom> alphabetic(){
        List<MeetingRoom> meetingRooms = meetingRoomsRepository.getMeetingRoomsList();
        List<MeetingRoom> meetingRoomsAlphabetic =  meetingRooms.stream()
                .sorted(Comparator.comparing(MeetingRoom::getName, Collator.getInstance(new Locale("hu", "HU"))))
                .collect(Collectors.toList());
        System.out.println(meetingRoomsAlphabetic);
        return meetingRoomsAlphabetic;
    }

    public List<MeetingRoom> alphabeticReversed(){
        List<MeetingRoom> meetingRooms = meetingRoomsRepository.getMeetingRoomsList();
        List<MeetingRoom> meetingRoomsAlphabetic =  meetingRooms.stream()
                .sorted(Comparator.comparing(MeetingRoom::getName, Collator.getInstance(new Locale("hu", "HU"))).reversed())
                .collect(Collectors.toList());
        System.out.println(meetingRoomsAlphabetic);
        return meetingRoomsAlphabetic;
    }

    public List<MeetingRoom> listEverySecond(){
        int[] counter = new int[]{0};
        List<MeetingRoom> meetingRooms = meetingRoomsRepository.getMeetingRoomsList();
        List<MeetingRoom> meetingRoomsEverySecond =  meetingRooms.stream().filter(l -> counter[0]++ % 2 == 0)
                .collect(Collectors.toList());
        System.out.println(meetingRoomsEverySecond);
        return meetingRoomsEverySecond;
    }

    public Map<String, Integer> getAreaMap() {
        List<MeetingRoom> meetingRooms = meetingRoomsRepository.getMeetingRoomsList();
        Map<String, Integer> areaMap = meetingRooms.stream()
                .collect(Collectors.toMap(meetingRoom -> meetingRoom.getName(), meetingRoom -> meetingRoom.getLength() * meetingRoom.getWidth()));
        System.out.println(areaMap);
        return areaMap;
    }


        public List<MeetingRoom> getRoomPerName(String name){
            List<MeetingRoom> meetingRooms = meetingRoomsRepository.getRoomPerName(name);
            System.out.println(meetingRooms);
            return  meetingRooms;
    }

    public List<MeetingRoom> getRoomPerNamePart (String namePart) {
        List<MeetingRoom> meetingRooms = meetingRoomsRepository.getRoomPerNamePart(namePart);
        System.out.println(meetingRooms);
        return meetingRooms;
    }


        public List<MeetingRoom> getRoomPerArea(int area) {
            List<MeetingRoom> meetingRooms = meetingRoomsRepository.getMeetingRoomsList().stream()
                    .filter(meetingRoom -> meetingRoom.getArea() == area)
                    .collect(Collectors.toList());
            System.out.println(meetingRooms);
            return  meetingRooms;

    }





}

