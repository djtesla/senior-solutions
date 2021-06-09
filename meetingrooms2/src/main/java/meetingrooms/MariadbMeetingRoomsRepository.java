package meetingrooms;

import org.mariadb.jdbc.MariaDbDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;
import java.util.List;

public class MariadbMeetingRoomsRepository implements MeetingRoomsRepository {


    private JdbcTemplate jdbcTemplate;


    public MariadbMeetingRoomsRepository() {

        MariaDbDataSource dataSource;
        try {
            dataSource = new MariaDbDataSource();
            dataSource.setUrl("jdbc:mariadb://localhost:3306/employees?useUnicode=true");
            dataSource.setUser("employees");
            dataSource.setPassword("employees");
        } catch (SQLException se) {
            throw new IllegalStateException("Cannot create database", se);
        }

        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(MeetingRoom meetingRoom) {

        jdbcTemplate.update("insert into rooms (name, length, width) values (?,?,?)", meetingRoom.getName(), meetingRoom.getLength(), meetingRoom.getLength());

    }

    @Override
    public List<MeetingRoom> getMeetingRoomsList() {
        return jdbcTemplate.query("select id, name, length, width from rooms",
                (rs, i) -> new MeetingRoom(rs.getLong(1), rs.getString(2), rs.getInt(3), rs.getInt(4)));
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update("delete from rooms");
    }

    @Override
    public List<MeetingRoom> getRoomPerName(String name) {
        return jdbcTemplate.query("select id, length, width from rooms where name = ?",
                (rs, i) -> new MeetingRoom(rs.getLong(1), rs.getString(2), rs.getInt(3), rs.getInt(4)), name);
    }

    @Override
    public List<MeetingRoom> getRoomPerNamePart(String namePart) {
        return jdbcTemplate.query("select id, length, width from rooms where lower(name) like ?",
                (rs,i) -> new MeetingRoom(rs.getLong(1), rs.getString(2), rs.getInt(3), rs.getInt(4)), namePart.toLowerCase());
    }
}
