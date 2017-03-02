package DBservice.dataSets;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by ihor on 01.03.17.
 */
@Entity
@Table(name = "admins")
public class AdminDataSet implements Serializable{
    private static final long serialVersionUID = -8706689714326132798L;

    @Id
    @Column(name = "login", updatable = false)
    private String login;

    @Column(name = "pass", updatable = false)
    private String pass;

    @Column(name = "sid")
    private String sid;

    public AdminDataSet(String login, String pass, String sid) {
        setId(login);
        setPass(pass);
        setSid(sid);
    }

    public String getId() {
        return login;
    }

    public String getPass() {
        return pass;
    }

    public void setId(String login) {
        this.login = login;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getSid() {
        return sid;
    }
}
