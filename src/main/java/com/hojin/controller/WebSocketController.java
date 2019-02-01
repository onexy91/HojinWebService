package com.hojin.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.hojin.api.Message;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@AllArgsConstructor
@Controller
@Slf4j
public class WebSocketController {
	private SimpMessagingTemplate template;
	
	@MessageMapping("/chat.sendMessage/{id}")
	public Message sendMessage(@DestinationVariable String id, @Payload Message message) {
		template.convertAndSend("/topic/public/"+id,message);
		return message;
		
	}
	@MessageMapping("/chat/create")
	public Message addUser(@Payload Message message, SimpMessageHeaderAccessor header) {
		header.getSessionAttributes().put("userName", message.getSender());
		log.info(header.toString());
		return message;
	}
	
	
	@GetMapping("/chat")
	public String chatView() {
		
		return "index";
	}
	@GetMapping("/server")
	public String serverview() {
		
		return "chatserver";
	}
}
