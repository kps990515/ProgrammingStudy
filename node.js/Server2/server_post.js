var http = require("http");
var u = require("url");
var fs = require("fs");
var qs = require("querystring");
var mime = require("mime");

// 주소(Rest Api) 요청의 형태
// http://localhost:8090/post?filepath=/dir1/xxx.png

var server = http.createServer(function(request,response){
	var url = u.parse(request.url);
	// method 를 꺼낸다
	// 주소에서 명령어=서버자원의id(uri) 를 먼저 꺼낸다
    var path = url.pathname;
    var cmds = path.split("/");
	if(cmds[1] == "getfile"){
		if(request.method == 'POST'){
			// .. body에 넘어온 filepath
		}else if(request.method == "GET"){
			var query = qs.parse(url.query);
			if(query.filepath){
				var filepath = query.filepath;
				var mtype = mime.getType(filepath); // 파일의 mime type을 알려준다
                // 동영상이면 스트림 처리
                if(mtype=="video/mp4"){
                    var count=0;
                    // 1. 스트림 생성
                    var stream = fs.createReadStream(filepath);
                    // 2. 스트림 전송 이벤트 등록
                    stream.on('data',function(fragment){ // 데이터를 읽을 수 있는 최소 단위조각이 콜백을 통해 전달
                        console.log("count : "+ count++)
                        response.write(fragment);
                    });
                    // 3. 스트림 완료 이벤트 등록
                    stream.on('end',function(){
                        response.end();
                    });
                    // 4. 스트림 에러 이벤트 등록
                    stream.on('error', function(error){
                        response.end(error+"");
                    });
                }else{
                    fs.readFile(filepath, function(error, data){
                        if(error){
                            response.writeHead(500,{'Content-Type':'text/html'});
                            response.end(error+"");
                        }else{
                            response.writeHead(200,{'Content-Type':mtype});
                            response.end(data);
                        }
                    });
                }  
			}
		}else{
			response.writeHead(500,{'Content-Type':'text/html'});
			response.end("error : method="+request.method);
		}
	}else if(cmds[1]=="html"){
        filepath=path.substring(1);
        fs.readFile(filepath, 'utf-8', function(error, data){            
            if(error){
                response.writeHead(404,{'Content-Type':'text/html'});
                response.end("<h1>404 Page Not Found</h1>");
            }else{
                response.writeHead(200,{'Content-Type':'text/html'});
                response.end(data);
            }
        });
    }else{
        response.writeHead(404,{'Content-Type':'text/html'});
        response.end("<h1>404 Page Not Found</h1>");
    }
});

var sendHttpResult = function(){

};

server.listen(8090, function(){
	console.log("server is running...");
});