package meetingroomsjpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.stream.Collectors;

public class JPAMeetingRoomsRepository implements MeetingRoomsRepository {

    private EntityManagerFactory entityManagerFactory;

    public JPAMeetingRoomsRepository(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public MeetingRoom save(MeetingRoom meetingRoom) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(meetingRoom);
        entityManager.getTransaction().commit();
        entityManager.close();
        return null;
    }

    @Override
    public List<String> getMeetingRoomsOrderedByName() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<String> roomNames = entityManager.createQuery("select m.name from MeetingRoom m order by m.name").getResultList();
        entityManager.close();
        return roomNames;
    }

    @Override//SELECT m FROM MeetingRoom m WHERE 0 = MOD(m.id,2)
    public List<String> getEverySecondMeetingRoom() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        //List<String> roomNames = entityManager.createQuery("select m.name from MeetingRoom m order by m.name where MOD(INDEX(m),2) = 0", MeetingRoom.class).getResultStream().map(MeetingRoom::getName).collect(Collectors.toList());
        entityManager.close();
        //return roomNames;
        return null;
    }

    @Override
    public List<MeetingRoom> getMeetingRooms() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<MeetingRoom> meetingRooms = entityManager.createQuery("select m from MeetingRoom  m",  MeetingRoom.class).getResultList();
        entityManager.close();
        return meetingRooms;
    }

    @Override
    public List<MeetingRoom> getExactMeetingRoomByName(String name) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<MeetingRoom> meetingRooms = entityManager.createQuery("select m from MeetingRoom  m where m.name=:name", MeetingRoom.class)
                .setParameter("name", name).getResultList();
        entityManager.close();
        return meetingRooms;
    }

    @Override
    public List<MeetingRoom> getMeetingRoomsByPrefix(String nameOrPrefix) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<MeetingRoom> meetingRooms = entityManager.createQuery("select m from MeetingRoom m where m.name like :name", MeetingRoom.class)
                .setParameter("name", nameOrPrefix).getResultList();
        entityManager.close();
        return meetingRooms;
    }

    @Override
    public void deleteAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE from MeetingRoom m").executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();

    }
}
