package compani.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "events", schema = "webapi")
public class Event {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "created")
    private Date created;
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "file_id")
    private File file;
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    public Event(){

    }

    public Event(int id, Date created, File file, User user) {
        this.id = id;
        this.created = created;
        this.file = file;
        this.user = user;
    }

    public Event(Date created, File file, User user) {
        this.created = created;
        this.file = file;
        this.user = user;
    }



    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", created=" + created +
                ", file=" + file +
                ", user=" + user +
                '}';
    }
}
