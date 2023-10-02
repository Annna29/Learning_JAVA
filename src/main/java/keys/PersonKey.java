package keys;

import jakarta.persistence.*;

@Entity
public class PersonKey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;       // Primary Key
    private String privateKeyVal;
    private String publicKeyVal;

    public PersonKey() {}

    public PersonKey( String privateKeyVal, String publicKeyVal) {
        this.privateKeyVal = privateKeyVal;
        this.publicKeyVal = publicKeyVal;
    }

    public String getPrivateKeyVal() {
        return privateKeyVal;
    }

    public void setPrivateKeyVal(String privateKeyVal) {
        this.privateKeyVal = privateKeyVal;
    }

    public String getPublicKeyVal() {
        return publicKeyVal;
    }

    public void setPublicKeyVal(String publicKeyVal) {
        this.publicKeyVal = publicKeyVal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

