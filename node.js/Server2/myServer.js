// 1. 서버모듈 가져오기
var http = require("http");
var url = require("url");
var qs = require("querystring");

// 2. 서버 생성하기
var server = http.createServer(function(request, response){
    var parsedUrl = url.parse(request.url);
    console.log(parsedUrl);

    var parsedQs = qs.parse(parsedUrl.query);
    console.log(parsedQs);

    response.end(request.url);
});

// 3. 서버 실행하기
server.listen(8090,function(){
    console.log("서버 실행중...");
});
