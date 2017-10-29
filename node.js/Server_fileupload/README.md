# Server.js

### 메인코드
- formidalbe.IncomingForm()을 통해 파일 받아올 준비
- multiples를 통해 여러개 받을 준비
- parse해서 파일마다 원래 파일path에서 실제 저장할 path로 변경해준다
- 이름이 겹치면 renameFile()을 통해 바꿔준다

```javascript
var h = require("http");
var u = require("url");

// 파일 업로드하기
var formidable = require("formidable");
var fs = require("fs");

var s = h.createServer(function(request, response){
    var url = u.parse(request.url);
    if(url.pathname === "/upload"){
        var form = new formidable.IncomingForm();
        form.multiples = true;
        form.parse(request, function(err, names, files){ // 임시폴더에 저장
            for(i in files){
                var oldPath = files[i].path;
                var realPath = "/Users/daeho/Documents/android/nodejs/server_fileupload/upload/" + files[i].name;

                renameFile(oldPath, realPath, 0);
                //fs.renameSync(oldPath, realPath);
            }

            response.end("upload completed!");
        });
    }
    else{
        response.end("404 not found");
    }
});

s.listen(8090, function(){ console.log("Server is running..."); });
```

### renameFile()
- 만약 index가 0보다 크면 파일 이름바꿔주고
- existsSync를 통해 중복된 파일 있으면 renameFile()한번 더 호출
- 중복된 파일 없으면 기존 파일의 경로를 새 파일의 경로로 변경해준다

```javascript
function renameFile(oldFile, newFile, index){
    var checkName = newFile;
    if(index > 0) {
        var split = checkName.split(".");
        checkName = split[0] + "_" + index + "." + split[1];
    }

    if(fs.existsSync(checkName)) renameFile(oldFile, newFile, ++index);
    else fs.renameSync(oldFile, checkName);
}
```
