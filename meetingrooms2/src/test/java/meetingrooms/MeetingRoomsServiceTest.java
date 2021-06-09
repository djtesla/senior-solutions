package meetingrooms;

import meetingrooms.MariadbMeetingRoomsRepository;
import meetingrooms.MeetingRoom;
import meetingrooms.MeetingRoomsService;
import org.junit.jupiter.api.Test;

class MeetingRoomsServiceTest {

    @Test
    public void testDeleteAll() {
        MeetingRoomsService meetingRoomsService = new MeetingRoomsService(new MariadbMeetingRoomsRepository());
        meetingRoomsService.deleteAll();

        meetingRoomsService.save(new MeetingRoom("Bábolna", 10, 2));
        meetingRoomsService.save(new MeetingRoom("Balaton", 10, 2));
        meetingRoomsService.save(new MeetingRoom("Sun", 10, 2));
        meetingRoomsService.save(new MeetingRoom("Jakarta", 2, 8));
        meetingRoomsService.save(new MeetingRoom("Kuala", 7, 3));
        meetingRoomsService.save(new MeetingRoom("Wien", 5, 3));
        meetingRoomsService.save(new MeetingRoom("Budapest", 12, 4));

        //System.out.println(meetingRoomsService.getMeetingRoomsList());

        System.out.println(meetingRoomsService.alphabetic());


    }

}