var u = require("url");
var bbs = require("../b_controller/bbs");

exports.process = function(request){
    var url = u.parse(request.url);
    var method = request.method.toLowerCase();
    var cmds = url.pathname.split("/");
    if(cmds[1]=="bbs"){

        if(method == "get"){
            bbs.search(request,response);
        }else if(method == "post"){
            bbs.create(request,response);
        }else if(method == "put"){
            bbs.update(request,response);
        }else if(method == "delete"){
            bbs.delete(request,response);
        }
    }else{

    } 
};