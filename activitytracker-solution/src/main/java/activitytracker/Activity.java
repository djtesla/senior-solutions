package activitytracker;

import com.sun.istack.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "activities")
public class Activity {

    public enum ActivityType {
        BIKING, HIKING, RUNNING, BASKETBALL
    }

    public Activity() {
    }

    @TableGenerator(name = "act_id_gen", pkColumnName = "id_gen", valueColumnName = "id_val")
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(generator = "act_id_gen")
    private Long id;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false, length = 200)
    private String description;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING) // már alapba így csináltam
    private ActivityType type;

    public Activity(LocalDateTime startTime, String description, ActivityType type) {
        this.startTime = startTime;
        this.description = description;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ActivityType getType() {
        return type;
    }

    public void setType(ActivityType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", startTime=" + startTime +
                ", description='" + description + '\'' +
                ", type=" + type +
                '}';
    }
}
