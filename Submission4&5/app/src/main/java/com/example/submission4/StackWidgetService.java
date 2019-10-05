package com.example.submission4;

import android.content.Intent;
import android.widget.RemoteViewsService;

import com.example.submission4.db.StackRemoteViewsFactory;

public class StackWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new StackRemoteViewsFactory(this.getApplicationContext());
    }
}
