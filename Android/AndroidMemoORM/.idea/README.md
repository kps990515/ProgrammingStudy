# PermissionUtil
- 권한설정 & 버전체크 & 승인여부 & 요청 & 최종확인

### 선언부 & 생성자
- 생성시 req_code & permissions 불러오기

```java
public class PermissionUtil {

    private int req_code;
    private String permissions[];

    public PermissionUtil(int req_code, String permissions[]){
        this.req_code = req_code;
        this.permissions = permissions;
    }
```

### checkPermission()
- 현 버전이 마시멜로 보다 높으면 requestPermission()
- 낮으면 true리턴 -> init()

```java
    public boolean checkPermission(Activity activity) {
        // 2. 버전 체크 후 권한 요청
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return requestPermission(activity);
        } else {
            return true;
        }
    }
```

### requestPermission()
- permissions에 담아뒀던 permission들을 꺼낸 후 권한이 있는지 확인한다
- 권한이 없으면 requires에 넣어준다
- requires에 있는 권한들을 꺼내서 권한요청을 한다

    @TargetApi(Build.VERSION_CODES.M)
    private boolean requestPermission(Activity activity) {
        // 3. 권한에 대한 승인 여부
        List<String> requires = new ArrayList<>();
        for (String permission : permissions) {
            if (activity.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                requires.add(permission);
            }
        }
        // 4. 승인이 안된 권한이 있을 경우만 승인 요청
        if (requires.size() > 0) {
            String [] perms = new String[requires.size()];
            perms = requires.toArray(perms);
            activity.requestPermissions(perms, req_code);
            return false;
        } else {
            return true;
        }
    }
```

#### afterPermissionResult()
- request에서 받아온 CallBack을 처리하는 기능
- grantResults가 모두 트루이면 true리턴
- 하나라도 아니면 false 리턴

```java
    public boolean afterPermissionResult(int requestCode, int[] grantResults) {
            if (requestCode==req_code) {
                boolean granted = true;
                for (int grant : grantResults) {
                    if (grant != PackageManager.PERMISSION_GRANTED) {
                        granted = false;
                        break;
                    }
                }
                if (granted) {
                    return true;
                }
             }
            return false;
    }
}
```
