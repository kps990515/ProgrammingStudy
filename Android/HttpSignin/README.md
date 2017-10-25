# HttpSignin

### LoginActivity
- 수정파트만 작성

#### AysncTask선언부

```java
public class UserLoginTask extends AsyncTask<Void, Void, String> {

    private final String mEmail;
    private final String mPassword;

    UserLoginTask(String email, String password) {
        mEmail = email;
        mPassword = password;
    }
```

#### doInBackground파트
- Sign에 String id,pw 선언하고 getter,setter설정
- 앱에 입력한 id,pw를 json으로 변환해서 node.js로 전달
```java
    @Override
    protected String doInBackground(Void... params) {

        Sign sign = new Sign();
        sign.setId(mEmail);
        sign.setPw(mPassword);

        String json = new Gson().toJson(sign);
        String result = Remote.sendPost("http://192.168.0.246:8090/signin",json);

        return result;
    }
```

#### onPostExecute파트
- node.js에서 메시지, 코드가 전달되면
- result(웹정보)를 Result.class(code, msg)의 형식으로 Gson사용해 변환 / rst에 저장
- rst의 메시지가 ok / 코드가 200(정상)이면 정상접근 메시지 띄어주기

```java
    @Override
    protected void onPostExecute(final String result) {
        mAuthTask = null;
        showProgress(false);

        Result rst = new Gson().fromJson(result, Result.class);

        if (rst.getMsg().equals("OK")) {
            Toast.makeText(LoginActivity.this, "Welcome", Toast.LENGTH_LONG).show();
        } else {
            mPasswordView.setError(getString(R.string.error_incorrect_password));
            mPasswordView.requestFocus();
        }
    }
```
