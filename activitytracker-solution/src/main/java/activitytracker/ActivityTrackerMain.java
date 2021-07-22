package activitytracker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;

public class ActivityTrackerMain {



    public static void main(String[] args) {
       ActivityTrackerMain atm = new ActivityTrackerMain();
        Activity activity = new Activity(LocalDateTime.of(2021, 07, 22, 15, 33), "Túrázás a átrában", Activity.ActivityType.HIKING);
        atm.saveActivity(activity);


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

