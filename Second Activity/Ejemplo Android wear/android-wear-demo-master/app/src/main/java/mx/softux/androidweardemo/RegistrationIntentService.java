/**
 * Copyright 2015 Google Inc. All Rights Reserved.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package mx.softux.androidweardemo;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

public class RegistrationIntentService extends IntentService {
    private static final String TAG = RegistrationIntentService.class.getSimpleName();

    public static final String DEVICE_ID = "device_id";
    public static final String DEVICE_TOKEN = "device_token";

    private static Device device;

    public RegistrationIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        if (sharedPreferences.getInt(DEVICE_ID, 0) > 0) {
            device = new Device(sharedPreferences.getInt(DEVICE_ID, 0), sharedPreferences.getString(DEVICE_TOKEN, ""));
            Log.i(TAG, "Existing GCM Registration Token: " + device.token);
            return;
        }

        try {
            synchronized (TAG) {
                InstanceID instanceID = InstanceID.getInstance(this);
                String token = instanceID.getToken(getString(R.string.gcm_defaultSenderId), GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
                Log.i(TAG, "GCM Registration Token: " + token);
                device = sendRegistrationToServer(token);

                sharedPreferences.edit().putInt(DEVICE_ID, device.id).putString(DEVICE_TOKEN, device.token).commit();
            }
        } catch (Exception e) {
            Log.e(TAG, "Failed to complete token refresh", e);
        }
    }

    private Device sendRegistrationToServer(String token) {
        return Api.getService().register(new Device(token));
    }
}
