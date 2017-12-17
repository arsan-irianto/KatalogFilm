package com.example.arsan_irianto.katalogfilm;

import android.content.Intent;
import android.widget.RemoteViewsService;

/**
 * Created by arsan-irianto on 12/16/17.
 */

public class StackWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new StackRemoteViewsFactory(this.getApplicationContext(), intent);
    }
}
