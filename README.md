This project consists of 2 parts.
* `fcmsender` - A minimal Spring project for sending push messages through [FMC Admin API](https://firebase.google.com/docs/cloud-messaging/admin/). This simulates our server software.
* `gcmreceiver` - A minimal Android project for receiving push messages through GCM. This simulates our legacy android apps.

Our intent is to use FCM Admin to send push messages to both our legacy- and our newer Android apps.


## Reproduction of issue:
### `gcmreceiver`
* Edit `SENDER_ID` in [GcmActivity.java](https://github.com/abilia/fcmtest/tree/master/gcmreceiver/app/src/main/java/se/abilia/gcmreceiver/GcmActivity.java)
* Run app
    * Press 'GET REGID' and copy the generated GCM reg id from logcat.

### `fcmsender`
* Paste generated GCM reg id into `TOKEN` in [PushTestApplication.java](https://github.com/abilia/fcmtest/blob/master/fcmsender/src/main/java/com/abilia/pushertest/PushTestApplication.java)
* Fill in [google-services.json](https://github.com/abilia/fcmtest/tree/master/fcmsender/src/main/resources)
* Run application

## Expected
* An actual push message arrives to running Android app which shows up in logcat and as a Toast.

## Actual
* FCM Admin API reports success (ex `projects/whalewhale2013/messages/0:1525894515775967%00000000f55efb3e`) but no push message is received in Android app.