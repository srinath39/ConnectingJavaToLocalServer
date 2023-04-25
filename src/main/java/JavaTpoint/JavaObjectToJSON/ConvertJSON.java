package JavaTpoint.JavaObjectToJSON;  
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.gson.*;  
public class ConvertJSON {  
    public static MobilePhone getMobile(MobilePhone mobile) {    
        mobile.setBrand("SAMSUNG");    
        mobile.setName("J2 Core");    
        mobile.setRam(2);  
        mobile.setRom(4);    
        return mobile;    
        }   
    public static void main(String[] args) throws IOException {  
        // TODO Auto-generated method stub  
        MobilePhone mobilePhone = new MobilePhone();    
        mobilePhone = getMobile(mobilePhone);    
        
        URL url = new URL("http://localhost:5000/");

        // Create a HttpURLConnection object
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        // Set the request method to POST
        con.setRequestMethod("POST");
        con.setDoOutput(true);


        // Set the content type to application/json
        con.setRequestProperty("Content-Type", "application/json");
        
        
        
        String json=new Gson().toJson(mobilePhone);  
        
        //System.out.println(json);
        
        
        OutputStream os = con.getOutputStream();
        os.write(json.getBytes());
        os.flush();
        os.close();
        
        
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
           response.append(inputLine);
        }
        in.close();

        // Print the response to the console
       System.out.println(response.toString());

        // Read the response from the Node.js server
        int responseCode = con.getResponseCode();
        System.out.println("Response Code : " + responseCode);

        // Disconnect the HttpURLConnection instance
        con.disconnect();
    }  
}