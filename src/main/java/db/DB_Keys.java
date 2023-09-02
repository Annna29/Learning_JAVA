package db;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;

public class DB_Keys {

    // Username + keys used for Encrypt/Decrypt Password.
    private HashMap<String, KeyPair> mapKeys;

    public DB_Keys() {
        mapKeys = new HashMap();
    }

    public HashMap<String, KeyPair> getMapKeys() {
        return mapKeys;
    }

    public void addKeys(String username, KeyPair keys){
        mapKeys.put(username, keys);
    }

    public void  removeKeys(String username) {
        mapKeys.remove(username);
    }

    public PublicKey getPublicKeyForUser(String username){
        return mapKeys.get(username).getPublic();
    }

    public PrivateKey getPrivateKeyForUser(String username){
        return mapKeys.get(username).getPrivate();
    }

}
