# Xiaomi Ants Alarm Service
Not supported any more. The service is not handling notification any more because the structure of it was changed.
A service for Xiaomi YI smart IP camera Android app. It can help you to not miss any movement alarm.

## How ot works?
It just handle all notifications from original Xiaomi Android application and shows you a fullscreen alarm with the sound and vibration.

## What's inside?
### MainActivity
Settings activity which will launch if you'll launch an app. You can enable alarm, vibration etc.
### AlarmActivity
Is an activity to show fullscreen alarm
### AntsNotificationService
It is NotificationListenerService to handle com.ants360.yicamera notifications.
