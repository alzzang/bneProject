Map = function(){
	this.map = new Object();
};   
Map.prototype = {   
		put : function(key, value){   
			this.map[key] = value;
		},   
		get : function(key){   
			return this.map[key];
		},
		containsKey : function(key){    
			return key in this.map;
		},
		containsValue : function(value){    
			for(var prop in this.map){
				if(this.map[prop] == value) return true;
			}
			return false;
		},
		isEmpty : function(key){    
			return (this.size() == 0);
		},
		clear : function(){   
			for(var prop in this.map){
				delete this.map[prop];
			}
		},
		remove : function(key){    
			delete this.map[key];
		},
		keys : function(){   
			var keys = new Array();   
			for(var prop in this.map){   
				keys.push(prop);
			}   
			return keys;
		},
		values : function(){   
			var values = new Array();   
			for(var prop in this.map){   
				values.push(this.map[prop]);
			}   
			return values;
		},
		size : function(){
			var count = 0;
			for (var prop in this.map) {
				count++;
			}
			return count;
		}
};

var http  = require('http');
var express = require('express');
var app = express();
var server = http.createServer(app);
var socket = require('socket.io');

var io = socket.listen(server);
var socketIds = new Map();


app.get('/notice', function(request, response) {
	var fromName = request.query.fromName;
	var toId = request.query.toId;
	var link_id = request.query.link_id;
	var notice_type = request.query.notice_type;	

	var message = {
				   "fromName":fromName, 
				   "notice_type":notice_type, 
				   "link_id":link_id
				   };
	
	
	response.end(JSON.stringify(message), 'utf-8');
		
	var socketId = socketIds.get(toId);
	if(socketId!==undefined){
		io.sockets.to(socketId).emit('newmessage',message);
	}
});


server.listen(10000, function() {
	console.log('server running!!!!!!');
});




function registerUser(socket,employeeId){
	socketIds.put(employeeId,socket.id);
	console.log(socketIds);
}

io.sockets.on('connection', function (socket) {

	console.log("socket connection");

	socket.on('getId',function(data){
		registerUser(socket,data.employeeId);
	});	

	socket.on('disconnect',function(){
		var keys = socketIds.keys();

		for(var i=0; i<socketIds.size(); i++){
			if(socketIds.get(keys[i])===socket.id){
				socketIds.remove(keys[i]);
			}
		}
		console.log(socketIds);
	});

});