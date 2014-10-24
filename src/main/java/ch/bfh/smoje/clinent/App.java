package ch.bfh.smoje.clinent;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.apache.commons.codec.binary.Base64;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	Client client = ClientBuilder.newBuilder().build();
    	
    	
    	WebTarget target = client.target("http://192.168.43.205:8080/piservice/api/smoje/sensors/camera"); // Pi Swen
//    	WebTarget target = client.target("http://178.62.210.231:8080/piservice/api/smoje/sensors/camera"); // Digital Ocean Mock
    	
    	
    	String res = target.request(MediaType.APPLICATION_JSON).get(String.class);
    	
    	ObjectMapper mapper = new ObjectMapper();
    	try {
            JsonNode json = mapper.readTree(res);
            JsonNode jsonNode = json.get("value");
            
            System.out.println(jsonNode.asText());
            
            byte[] data = Base64.decodeBase64(jsonNode.asText());
            
            try (OutputStream stream = new FileOutputStream("/home/adrian/temp/image.jpg")) {
                stream.write(data);
            }
            

            
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    	
    	
    }
}
