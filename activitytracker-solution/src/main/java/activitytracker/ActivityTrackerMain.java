package activitytracker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

public class ActivityTrackerMain {



    public static void main(String[] args) {
    //ActivityTrackerMain atm = new ActivityTrackerMain();

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        Activity activity1 = new Activity(LocalDateTime.of(2021, 07, 22, 15, 33), "Túrázás a Mátrában", Activity.ActivityType.HIKING);
        entityManager.persist(activity1);
        activity1.setDescription("Túrázás a Bükkben");
        entityManager.getTransaction().commit();
        long id = activity1.getId();
        Activity another = entityManager.find(Activity.class, id);;
        System.out.println(another);

        Activity activity2 = new Activity(LocalDateTime.of(2021, 07, 23, 11, 10), "Futás a MArgitszigeten", Activity.ActivityType.RUNNING);
        entityManager.getTransaction().begin();
        entityManager.persist(activity2);
        entityManager.getTransaction().commit();
        List<Activity> activities = entityManager.createQuery("select a from Activity a", Activity.class).getResultList();
        System.out.println(activities);

        entityManager.getTransaction().begin();
        entityManager.remove(activity2);
        entityManager.getTransaction().commit();
        activities = entityManager.createQuery("select a from Activity a", Activity.class).getResultList();
        System.out.println(activities);

        entityManager.close();
        factory.close();


    }

    public void saveActivity(Activity activity){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(activity);
        entityManager.getTransaction().commit();
        entityManager.close();
        factory.close();


    }

}

