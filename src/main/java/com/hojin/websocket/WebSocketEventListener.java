package com.hojin.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.messaging.support.NativeMessageHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.hojin.api.Message;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class WebSocketEventListener {
	@Autowired
	private SimpMessageSendingOperations messagingTemplate;

	@EventListener
	    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
		 MessageHeaderAccessor accessor = NativeMessageHeaderAccessor.getAccessor(event.getMessage(), SimpMessageHeaderAccessor.class);
	      GenericMessage<?> generic = (GenericMessage<?>) accessor.getHeader("simpConnectMessage");
	      String sessionId = (String) generic.getHeaders().get("simpSessionId");
	      log.info("Received a new web socket connection");
	      log.info(null, event.getUser());
	      log.info(event.getMessage().toString());
	    }

	@EventListener
	public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
		StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

		String username = (String) headerAccessor.getSessionAttributes().get("username");
		if (username != null) {
			log.info("User Disconnected : " + username);

			Message message = new Message();
			message.setType(Message.MessageType.LEAVE);
			message.setSender(username);

			messagingTemplate.convertAndSend("/topic/public", message);
		}
	}
}
