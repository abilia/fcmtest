package com.abilia.pushertest;

import com.google.api.client.http.HttpResponseException;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.ClassPathResource;

import java.util.concurrent.ExecutionException;

@SpringBootApplication
public class PushTestApplication {

    private static final String TOKEN = "<Push reg id here>";

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(PushTestApplication.class, args);

        try {
            FirebaseOptions options = new FirebaseOptions.Builder().setCredentials(
                    GoogleCredentials.fromStream(new ClassPathResource("google-services.json").getInputStream()))
                    .build();

            FirebaseApp.initializeApp(options);
            FirebaseMessaging fm = FirebaseMessaging.getInstance();

            String token = args.length > 0 ? args[0] : TOKEN;

            Message msg = Message.builder()
                    .setAndroidConfig(
                            AndroidConfig.builder().setCollapseKey("calendar").putData("calendar", "updated").build())
                    .setNotification(new Notification("title", "body")).setToken(token).build();

            String res = fm.sendAsync(msg).get();
            System.out.println("Sent push to \"" + token + "\". Got result: " + res);
        } catch (Exception e) {
            System.out.println(e.getClass());
            e.printStackTrace();
        }

        ctx.close();
    }
}
