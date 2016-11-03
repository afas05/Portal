package DBservice.dataSets;

import javax.persistence.*;
import java.io.Serializable;
/**
 * Created by Игорь on 02.11.2016.
 */
@Entity
@Table(name = "users")
public class UserDataSet implements Serializable{
    private static final long serialVersionUID = -8706689714326132798L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "vk", unique = true, updatable = false)
    private String vk;

    public UserDataSet(){
    }

    public UserDataSet(long id, String vk) {
        this.setId(id);
        this.setVk(vk);
    }

    public UserDataSet(String vk) {
        this.setId(-1);
        this.setVk(vk);
    }

    public String getName() {
        return vk;
    }

    public void setVk(String name) {
        this.vk = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
