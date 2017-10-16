# Remote
- HttpURLConnection을 통해 URL정보 가져오기

```java
public class Remote {
    public static String getData(String s) {
            final StringBuilder result = new StringBuilder();
            try {
                URL url = new URL(s);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                // 통신이 성공인지 체크
                if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    // 여기서 부터는 파일에서 데이터를 가져오는 것도 동일
                    InputStreamReader isr = new InputStreamReader(con.getInputStream());
                    BufferedReader br = new BufferedReader(isr);
                    String temp = "";
                    while ((temp = br.readLine()) != null) {
                        result.append(temp).append("\n");
                    }
                    br.close();
                    isr.close();
                } else {
                    Log.e("ServerError", con.getResponseCode() + "");
                }
                con.disconnect();
            } catch (Exception e) {
                Log.e("ERROR", e.toString());
            }
            return result.toString();
        }
    }
```
