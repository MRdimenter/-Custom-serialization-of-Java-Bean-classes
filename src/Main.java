import java.util.logging.Logger;

public class Main {
    private static Logger log = Logger.getLogger(Main.class.getName()); //логирование

    public static void main(String[] args) {
        Person person = new Person(1, "Dima", "mr.dimenter@gmail.com");
        CastomSerialization castomSerialization = new CastomSerialization();
        byte[] person1 = castomSerialization.serialize(person);


        log.info("Serialization method test: \n" + new String(person1));
        log.info("Deserialization method test: \n" + castomSerialization.deserialize(person1).toString());
    }
}
