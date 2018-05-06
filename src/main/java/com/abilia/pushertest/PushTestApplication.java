package com.abilia.pushertest;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.ClassPathResource;

@SpringBootApplication
public class PushTestApplication {

    private static final String TOKEN = "APA91bFEjzLxk1_zqL2IiGNs6N_XugTddOYRqTMgZ_pLA0uSpqrSDS2BZkqq1rjJa5n2_mDMnDjY-TPiEAXThAG6nWfOO-OydvBDAcoxRi8BAbbvFecjH_xq4Kdh40F-qoNoBMdftDpB";

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(PushTestApplication.class, args);

        try {
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(new ClassPathResource("google-services.json").getInputStream()))
                    .build();

            FirebaseApp.initializeApp(options);
            FirebaseMessaging fm = FirebaseMessaging.getInstance();

            String token = args.length > 0 ? args[0] : TOKEN;

            Message msg = Message.builder()
                    .setAndroidConfig(AndroidConfig.builder().setCollapseKey("calendar")
                            .putData("calendar", "updated").build())
                    .setNotification(new Notification("title", "body"))
                    .setToken(token)
                    .build();

            String res = fm.sendAsync(msg).get();
            System.out.println("Sent push to \"" + token + "\". Got result: " + res);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        ctx.close();
    }
}
