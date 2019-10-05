package com.example.submission4;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;

import org.jetbrains.annotations.Nullable;

import java.lang.ref.WeakReference;
import java.util.Calendar;

import static android.support.constraint.Constraints.TAG;

public class NotificationService extends Service implements  NotifAsyncCallback {

    public NotificationService() {
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        NotifAsync notifAsync = new NotifAsync(this);
        notifAsync.execute();
        Log.d(TAG, "onStartCommand: NotificationService");
        return START_STICKY;
    }

    @Override
    public void preAsync() {
        Log.d(TAG, "preAsync: Mulai.....");

    }

    @Override
    public void postAsync() {
        Log.d(TAG, "postAsync: Selesai.....");
        stopSelf();
    }


    private class NotifAsync extends AsyncTask<Void, Void, Void>{
        WeakReference<NotifAsyncCallback> callbackWeakReference;
        Calendar calendar = Calendar.getInstance();

        NotifAsync(NotifAsyncCallback callbackWeakReference) {
            this.callbackWeakReference = new WeakReference<>(callbackWeakReference);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            preAsync();
            calendar.set(Calendar.HOUR_OF_DAY, 8);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Log.d(TAG, "doInBackground: memulai...");
            Intent intent1 = new Intent(getApplicationContext(), ReleaseTodayService.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0,intent1, PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager am = (AlarmManager) getApplicationContext().getSystemService(ALARM_SERVICE);
            am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            postAsync();
        }
    }
}
