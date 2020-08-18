package org.taqelah.talk.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.taqelah.talk.model.Message;

public class MessageService {

	private static Map<Long, Message> messages = new HashMap<Long, Message>();

	// set initial data
	public MessageService() {
		messages.put(1L, new Message(1, "Message1", "Author1"));
		messages.put(2L, new Message(2, "Message2", "Author2"));
	}

	/**
	 * All available messages
	 * 
	 * @return message list
	 */
	public List<Message> getAllMessages() {
		return new ArrayList<Message>(messages.values());
	}

	/**
	 * Adding new message
	 * 
	 * @param message payload
	 * @return new message
	 */
	public Message addMessage(Message message) {
		message.setId(messages.size() + 1);
		message.setCreated(new Date());
		messages.put(message.getId(), message);
		return message;
	}

	/**
	 * Update values of existing content
	 * 
	 * @param message payload
	 * @return updated message
	 */
	public Message updateMessage(Message message) {
		message.setCreated(messages.get(message.getId()).getCreated());
		messages.put(message.getId(), message);
		return messages.get(message.getId());
	}

	public boolean deleteMessage(long messageId) {
		boolean isRemoved = false;
		messages.remove(messageId);
		if (!messages.containsKey(messageId))
			isRemoved = true;
		return isRemoved;
	}
}
