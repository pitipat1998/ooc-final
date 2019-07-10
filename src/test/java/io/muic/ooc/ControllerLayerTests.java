package io.muic.ooc;

import com.google.gson.Gson;
import io.muic.ooc.formatconverter.FormatConverterDTO;
import static org.assertj.core.api.Assertions.assertThat;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.Scanner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ControllerLayerTests {

    @LocalServerPort
    private int port;

    @Test
    public void testErrorInput() {
        Gson gson = new Gson();
        String url = "http://localhost:"+port+"/convert";
        try{
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost post = new HttpPost(url);
            FormatConverterDTO testcase1 = new FormatConverterDTO("Profile", "ckass", "camelCase");
            StringEntity postingString1 = new StringEntity(gson.toJson(testcase1));
            post.setEntity(postingString1);
            post.setHeader("Content-type", "application/json");
            HttpResponse response = httpClient.execute(post);
            int statusCode = response.getStatusLine().getStatusCode();
            assertThat(statusCode).isEqualTo(HttpStatus.SC_BAD_REQUEST);

            httpClient = HttpClientBuilder.create().build();
            post = new HttpPost(url);
            FormatConverterDTO testcase2 = new FormatConverterDTO("Profile", "class", "CamelCase");
            StringEntity postingString2 = new StringEntity(gson.toJson(testcase2));
            post.setEntity(postingString2);
            post.setHeader("Content-type", "application/json");
            response = httpClient.execute(post);
            statusCode = response.getStatusLine().getStatusCode();
            assertThat(statusCode).isEqualTo(HttpStatus.SC_BAD_REQUEST);

            httpClient = HttpClientBuilder.create().build();
            post = new HttpPost(url);
            FormatConverterDTO testcase3 = new FormatConverterDTO(null, "properly", "snake_case");
            StringEntity postingString3 = new StringEntity(gson.toJson(testcase3));
            post.setEntity(postingString3);
            post.setHeader("Content-type", "application/json");
            response = httpClient.execute(post);
            statusCode = response.getStatusLine().getStatusCode();
            assertThat(statusCode).isEqualTo(HttpStatus.SC_BAD_REQUEST);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Test
    public void finalTestCases(){
        File file;
        Scanner scanner = null;
        Gson gson = new Gson();
        String url = "http://localhost:"+port+"/convert";
        HttpClient httpClient = HttpClientBuilder.create().build();
        try{
            file = new File("src/test/resources/test-cases.csv");
            scanner = new Scanner(file);
            scanner.nextLine();
            while(scanner.hasNextLine()){
                String[] testcase = scanner.nextLine().split(",");
                for (int i = 0; i < testcase.length; i++) {
                    testcase[i] = testcase[i].trim();
                }
                FormatConverterDTO dto = new FormatConverterDTO(testcase[0], testcase[1], testcase[2]);
                HttpPost post = new HttpPost(url);
                StringEntity postingString = new StringEntity(gson.toJson(dto));
                post.setEntity(postingString);
                post.setHeader("Content-type", "application/json");
                HttpResponse response = httpClient.execute(post);
                int statusCode = response.getStatusLine().getStatusCode();
                String json = EntityUtils.toString(response.getEntity());
                assertThat(json).isEqualTo(testcase[3]);
                assertThat(statusCode).isEqualTo(HttpStatus.SC_OK);
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            if(scanner != null){
                scanner.close();
            }
        }
    }
}
