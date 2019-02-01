'use strict';

var serverpage = document.querySelector('#server-page');
var joinpage = document.querySelector('#join-area');
var chatPage = document.querySelector('#chat-page');
// var usernameForm = document.querySelector('#usernameForm');
 var messageForm = document.querySelector('#messageForm');
 var messageInput = document.querySelector('#message');
 var messageArea = document.querySelector('#messageArea');
// var connectingElement = document.querySelector('.connecting');

var stompClient = null;
var username = null;

var colors = [ '#2196F3', '#32c787', '#00BCD4', '#ff5652', '#ffc107',
		'#ff85af', '#FF9800', '#39bbb0' ];

var server = {
	start : function() {

		var socket = new SockJS('/ws');
		stompClient = Stomp.over(socket);

		stompClient.connect({}, onConnected, onError);
		
	}
}

server.start();



function onConnected() {
	// Subscribe to the Public Topic
	stompClient.subscribe('/topic/public/chat/create', onMessageReceived);
}

function onError(error) {
	connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
	connectingElement.style.color = 'red';
}

function sendMessage(event) {
	var messageContent = messageInput.value.trim();
	if (messageContent && stompClient) {
		var chatMessage = {
			sender : username,
			content : messageInput.value,
			type : 'CHAT'
		};
		stompClient.send("/app/chat.sendMessage/"+username, {}, JSON
				.stringify(chatMessage));
		messageInput.value = '';
	}
}

function onMessageReceived(payload) {
	var message = JSON.parse(payload.body);
		username = message.sender;
		
	var messageElement = document.createElement('li');
	if (message.type === 'JOIN') {
		joinpage.classList.remove('hidden');
		chatPage.classList.remove('hidden');
		messageElement.classList.add('event-message');
		message.content = message.sender + ' joined!';
	
	var textElementSender = document.createElement('p');
	var messageText = document.createTextNode(message.content);
	textElementSender.appendChild(messageText);
	messageElement.appendChild(textElementSender);
	
	var textElementId = document.createElement('p');
	var roomIdText = document.createTextNode(message.roomId);
	textElementId.appendChild(roomIdText);
	messageElement.appendChild(textElementId);
	

	 messageArea.appendChild(messageElement);
	// messageArea.scrollTop = messageArea.scrollHeight;
	}
}

function getAvatarColor(messageSender) {
	var hash = 0;
	for (var i = 0; i < messageSender.length; i++) {
		hash = 31 * hash + messageSender.charCodeAt(i);
	}
	var index = Math.abs(hash % colors.length);
	return colors[index];
}

// usernameForm.addEventListener('submit', connect, true)
 messageForm.addEventListener('submit', sendMessage, true)
