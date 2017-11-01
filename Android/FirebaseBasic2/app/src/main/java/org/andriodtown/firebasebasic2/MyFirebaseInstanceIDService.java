package org.andriodtown.firebasebasic2;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by user on 2017-10-31.
 */
// 앱이깔리면 사용자의 앱 token을 데이터베이스에 추가
public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = "MyFirebaseID";

    @Override
    public void onTokenRefresh() {
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);

        sendRegistrationToServer(refreshedToken);
    }

    private void sendRegistrationToServer(String token) {
        // TODO: 내 데이터베이스의 사용자 token값을 여기서 갱신
        String user_node = "user/사용자아이디/token";
        // user_node.setValue(token)
    }
}
