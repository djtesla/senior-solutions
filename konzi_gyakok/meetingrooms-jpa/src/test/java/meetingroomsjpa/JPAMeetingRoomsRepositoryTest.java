package meetingroomsjpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import static org.junit.jupiter.api.Assertions.*;

class JPAMeetingRoomsRepositoryTest {

    private JPAMeetingRoomsRepository repository;


    @BeforeEach
    void init(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");
        repository = new JPAMeetingRoomsRepository(entityManagerFactory);
    }

    @Test
    void testSave(){
        MeetingRoom meetingRoom = new MeetingRoom("Jakarta", 10, 12);
        repository.save(meetingRoom);
        MeetingRoom another = repository.getExactMeetingRoomByName("Jakarta").get(0);
        assertEquals(12, another.getLength());

        //repository.
    }


    @Test
    void testDeleteAll(){
        MeetingRoom meetingRoom1 = new MeetingRoom("Jakarta", 10, 12);
        MeetingRoom meetingRoom2 = new MeetingRoom("Budapest", 10, 12);
        repository.save(meetingRoom1);
        repository.save(meetingRoom2);
        repository.deleteAll();
        assertTrue(repository.getMeetingRooms().isEmpty());
        //repository.
    }

}