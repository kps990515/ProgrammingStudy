# mysql_connect
- settings에 mysql연결하기 위한 기본값들을 넣어준다


```javascript
var mysql = require("mysql");

var settings = {
    host : "localhost",
    user : "root",
    password : "dream9025",
    port : "3306",
    database : "memo"
};
var con = mysql.createConnection(settings);

con.connect();
con.query("select * from memo", function(error,record_set,columns){
    record_set.forEach(function(record) {
        console.log(record);   
    });
    this.end(); // 쿼리처리 연결해제
})
con.end(); // 데이터베이스 연결해제
```
