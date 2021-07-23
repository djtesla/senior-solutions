package activitytracker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.time.LocalDateTime;
import java.util.List;

public class ActivityDao {

    private EntityManagerFactory factory;

    public ActivityDao(EntityManagerFactory factory) {
        this.factory = factory;
    }


    public void saveActivity(Activity activity) {
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        activity.setCreatedAt(LocalDateTime.now());
        entityManager.persist(activity);
        entityManager.getTransaction().commit();
        entityManager.close();

    }

    public List<Activity> listActivities() {
        EntityManager entityManager = factory.createEntityManager();
        List<Activity> activities = entityManager.createQuery("select a from Activity a", Activity.class).getResultList();
        entityManager.close();
        return activities;
    }

    public Activity findActivityById(long id) {
        EntityManager entityManager = factory.createEntityManager();
        Activity activity = entityManager.find(Activity.class, id);
        entityManager.close();
        return activity;
    }

    public void updateActivity(long id, String description) {
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        Activity activity = entityManager.find(Activity.class, id);
        activity.setDescription(description);
        activity.setUpdatedAt((LocalDateTime.now()));
        entityManager.getTransaction().commit();
        entityManager.close();

    }

    public void updateActivityById(long id, String description) {
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        Activity activity = entityManager.find(Activity.class, id);
        activity.setDescription(description);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void updateActivityById(long id, String description, LocalDateTime startTime) {
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        Activity activity = entityManager.find(Activity.class, id);
        activity.setDescription(description);
        activity.setStartTime(startTime);
        entityManager.getTransaction().commit();
        entityManager.close();

    }

    public void updateActivityById(long id, String description, LocalDateTime startTime, Activity.ActivityType type) {
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        Activity activity = entityManager.find(Activity.class, id);
        activity.setDescription(description);
        activity.setStartTime(startTime);
        activity.setType(type);
        entityManager.getTransaction().commit();
        entityManager.close();
    }


    public void deleteActivity(long id) {
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        Activity activityToBeRemoved = entityManager.find(Activity.class, id);
        entityManager.remove(activityToBeRemoved);
        entityManager.getTransaction().commit();
        entityManager.close();
    }


}
