package activitytracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ActivityDaoTest {

    private ActivityDao activityDao;

    @BeforeEach
    void init(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("pu");
        activityDao = new ActivityDao(entityManagerFactory);
    }




    @Test
    void testSaveThenFindActivity() {
        Activity activity = new Activity(LocalDateTime.of(2021, 07, 22, 15, 33),
                "Túrázás a Mátrában", Activity.ActivityType.HIKING);
        activityDao.saveActivity(activity);
        Activity another = activityDao.findActivityById(activity.getId());
        assertEquals("Túrázás a Mátrában", another.getDescription());
    }

    @Test
    void testListActivities() {
        Activity activity1 = new Activity(LocalDateTime.of(2021, 07, 22, 15, 33),
                "Túrázás a Mátrában", Activity.ActivityType.HIKING);
        Activity activity2 = new Activity(LocalDateTime.of(2021, 07, 23, 11, 10), "Futás a Margitszigeten", Activity.ActivityType.RUNNING);
        activityDao.saveActivity(activity1);
        activityDao.saveActivity(activity2);
        List<Activity> activities = activityDao.listActivities();
        assertEquals(2, activities.size());
    }


    @Test
    void testDeleteActivity() {
        Activity activity = new Activity(LocalDateTime.of(2021, 07, 22, 15, 33),
                "Túrázás a Mátrában", Activity.ActivityType.HIKING);
        activityDao.saveActivity(activity);
        activityDao.deleteActivity(activity.getId());
        List<Activity> activities = activityDao.listActivities();
        assertTrue(activities.isEmpty());
    }

    @Test
    void testUpdateActivity(){
        Activity activity = new Activity(LocalDateTime.of(2021, 07, 22, 15, 33),
                "Túrázás a Mátrában", Activity.ActivityType.HIKING);
        activityDao.saveActivity(activity);
        activityDao.updateActivity(activity.getId(),"Túrázás a Bükkben");
        Activity another = activityDao.findActivityById(activity.getId());
        assertEquals("Túrázás a Bükkben", another.getDescription());
    }


    @Test
    void testSaveThenFindActivityByIdWithLabels () {
        Activity activity = new Activity(LocalDateTime.of(2021, 07, 22, 15, 33),
                "Túrázás a Mátrában", Activity.ActivityType.HIKING, List.of("Jó idő volt", "Kevés kaját vittünk"));
        activityDao.saveActivity(activity);
        Activity another = activityDao.findActivityByIdWithLabels(activity.getId());
        assertEquals(2, another.getLabels().size());
    }


    @Test
    void testFindActivityByIdWithTrackPoints1() {
        Activity activity = new Activity(LocalDateTime.of(2021, 07, 22, 15, 33),
                "Túrázás a Mátrában", Activity.ActivityType.HIKING, List.of("Jó idő volt", "Kevés kaját vittünk"));
        activity.addTrackPoint(new TrackPoint(LocalDate.of(2021, 7, 22), 100, 100));
        activity.addTrackPoint(new TrackPoint(LocalDate.of(2021, 8, 23), 100, 105));
        activityDao.saveActivity(activity);
        long id = activity.getId();
        Activity another = activityDao.findActivityByIdWithTrackPoints(id);
        assertEquals(2, another.getTrackPoints().size());
    }


    @Test
    void testFindActivityByIdWithTrackPoints2() {
        Activity activity = new Activity(LocalDateTime.of(2021, 07, 22, 15, 33),
                "Túrázás a Mátrában", Activity.ActivityType.HIKING, List.of("Jó idő volt", "Kevés kaját vittünk"));
        activityDao.saveActivity(activity);
        long id = activity.getId();
        TrackPoint trackPoint1 = new TrackPoint(LocalDate.of(2021, 7, 22), 100, 100);
        TrackPoint trackPoint2 = new TrackPoint(LocalDate.of(2021, 8, 23), 100, 105);
        activityDao.addTrackPoint(id, trackPoint1);
        activityDao.addTrackPoint(id, trackPoint2);
        Activity another = activityDao.findActivityByIdWithTrackPoints(id);
        assertEquals(2, another.getTrackPoints().size());
    }


}