var SocketMap = function() {
	this.map = new Object();
};

SocketMap.prototype = {
		put : function(socketId, empId) {
			this.map[socketId] = empId;
		},
		get : function(socketId) {
			return this.map[socketId];
		},
		SocketIds : function() {
			var socketIds = new Array();
			for(var socket_id in this.map) {
				socketIds.push(socket_id);
			}
			return socketIds;
		},
		getSocketIdList : function(empId) {
			var socketIds = new Array();
			for(var socket_id in this.map) {
				if(this.map[socket_id] === empId) {
					socketIds.push(socket_id);
				}
			}

			return socketIds;
		},
		remove : function(socketId){    
			delete this.map[socketId];
		},
		EmpIds : function() {
			var empIds = new Array();
			for(var socket_id in this.map) {
				empIds.push(this.map[socket_id]);
			}
		}
};






var http  = require('http');
var express = require('express');
var app = express();
var server = http.createServer(app);
var socket = require('socket.io');

var io = socket.listen(server);
var socketmap = new SocketMap();

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

	var socketIdList = socketmap.getSocketIdList(toId);
	for(var i=0; i<socketIdList.length; i++) {
		io.sockets.to(socketIdList[i]).emit('newmessage', message);
	}
});


server.listen(10000, function() {
	console.log('server running!!!!!!');
});




function registerUser(socket,employeeId){
	socketmap.put(socket.id,employeeId);
	console.log(socketmap);
}

io.sockets.on('connection', function (socket) {

	console.log("socket connection");

	socket.on('getId',function(data){
		registerUser(socket,data.employeeId);
	});	

	socket.on('disconnect',function(){
		socketmap.remove(socket.id);
		console.log(socket.id + " disconnected.");
	});

});





