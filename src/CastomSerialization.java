import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CastomSerialization implements SuperEncoder {

    public byte[] serialize(Object anyBean) {
        byte[] data;
        FileOutputStream fileOutputStream = null;
        XMLEncoder encoder = null; //Запись в формате XML, действует аналогично ObjectOutputStream
        try {
            fileOutputStream = new FileOutputStream("serialization.xml");
            encoder = new XMLEncoder(fileOutputStream);
            encoder.writeObject(anyBean); //запись объекта в текстовый файл формала XML
        } catch (IOException e) {
        } finally { //выполняется в любом случае - произошло исключение, или нет
            try {
                encoder.close();
                fileOutputStream.close();
                data = Files.readAllBytes(Paths.get("serialization.xml")); //Преоброзование файла в поток байтов
                return data;
            } catch (IOException e) {
            }
        }
        return null;
    }

    @Override
    public Object deserialize(byte[] data) {
        try {
            try (FileOutputStream fileOutputStream = new FileOutputStream("deserialization.xml")) {
                fileOutputStream.write(data); //запись потока байтов в файл
                fileOutputStream.flush();
            }

            FileInputStream fileInputStream = new FileInputStream("deserialization.xml");
            XMLDecoder decoder = new XMLDecoder(fileInputStream); //Считывание файла в формате XML
            Person person = (Person) decoder.readObject();
            decoder.close();
            fileInputStream.close();
            return person;
        } catch (IOException e) {
        }
        return null;
    }
}

