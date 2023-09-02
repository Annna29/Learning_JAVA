package db;
import persons.Person;
import java.util.HashMap;

public class DB_Persons {

    private  HashMap<String,Person> mapPersons ;

    public DB_Persons() {
        mapPersons = new HashMap();
    }

    public HashMap<String, Person> getMapPersons() {
        return mapPersons;
    }

    public void addPersons(Person person){
        mapPersons.put(person.getUsername(), person);
    }
}