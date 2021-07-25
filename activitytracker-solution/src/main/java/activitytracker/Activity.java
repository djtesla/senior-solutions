package activitytracker;

import com.sun.istack.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "activities")
@NamedQuery(name ="findCoordinates", query="select new activitytracker.CoordinateDto(t.lat, t.lon) from TrackPoint t where t.activity.startTime > :afterThis order by t.lat")
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

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name ="updated_at")
    private LocalDateTime updatedAt;

    @ElementCollection
    @CollectionTable(joinColumns = @JoinColumn(name = "activity_id"))
    private List<String> labels;


    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "activity")
    @OrderBy("time")
    List<TrackPoint> trackPoints;

    public Activity(LocalDateTime startTime, String description, ActivityType type) {
        this.startTime = startTime;
        this.description = description;
        this.type = type;
    }

    public Activity(LocalDateTime startTime, String description, ActivityType type, List<String> labels) {
        this.startTime = startTime;
        this.description = description;
        this.type = type;
        this.labels = labels;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public List<TrackPoint> getTrackPoints() {
        return trackPoints;
    }

    public void setTrackPoints(List<TrackPoint> trackPoints) {
        this.trackPoints = trackPoints;
    }

    public void addTrackPoint(TrackPoint trackPoint) {
        if (trackPoints == null) {
            trackPoints = new ArrayList<>();
        }
        trackPoints.add(trackPoint);
        trackPoint.setActivity(this);
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
