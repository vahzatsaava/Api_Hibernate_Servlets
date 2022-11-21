package compani.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "files", schema = "webapi")
@JsonIgnoreProperties("event")
public class File {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "fileName")
    private String fileName;
    @Column(name = "location")
    private String location;
    @OneToOne(fetch = FetchType.LAZY,mappedBy = "file",cascade = CascadeType.ALL)
    private Event event;

    public File() {
    }


    public File(String fileName, String location) {
        this.fileName = fileName;
        this.location = location;
    }

    public File(int id, String fileName, String location) {
        this.id = id;
        this.fileName = fileName;
        this.location = location;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "File{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
