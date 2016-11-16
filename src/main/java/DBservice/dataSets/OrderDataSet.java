package DBservice.dataSets;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Игорь on 03.11.2016.
 */
@Entity
@Table(name = "orders")
public class OrderDataSet implements Serializable{
    private static final long serialVersionUID = -8706689714326132798L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "vk", updatable = false)
    private String vk;

    @Column(name = "data", updatable = false, columnDefinition="TEXT")
    private String data;

    @Column(name = "signature", updatable = false)
    private String signature;

    @Column(name = "payBy", updatable = false)
    private String payBy;

    public OrderDataSet() {}

    public OrderDataSet(long id, String vk, String data, String signature, String payBy) {
        this.setId(id);
        this.setVk(vk);
        this.setData(data);
        this.setSignature(signature);
        this.setPayBy(payBy);
    }

    public OrderDataSet(String vk, String data, String signature, String payBy) {
        this.setId(-1);
        this.setVk(vk);
        this.setData(data);
        this.setSignature(signature);
        this.setPayBy(payBy);
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setVk(String vk) {this.vk = vk; }

    public String getVk() {return vk;}

    public void setData(String data) { this.data = data; }

    public String getData() { return data; }

    public void setSignature(String signature) { this.signature = signature; }

    public String getSignature() { return signature; }

    public void setPayBy(String payBy) { this.payBy = payBy; }

    public String getPayBy() { return payBy; }
}
