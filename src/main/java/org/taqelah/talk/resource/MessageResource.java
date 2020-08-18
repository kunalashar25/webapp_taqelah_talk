package org.taqelah.talk.resource;

import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.taqelah.talk.model.Message;
import org.taqelah.talk.service.MessageService;

@Path("/messages")
@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Consumes({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
public class MessageResource {

	MessageService messageService = new MessageService();

	@GET
	public List<Message> getMessages() {
		return messageService.getAllMessages();
	}

	@GET
	@Path("/{messageId}")
	public Response getMessage(@PathParam("messageId") long messageId) {
		return Response.status(Status.OK)
				.entity(messageService.getAllMessages().stream().filter(t -> t.getId() == messageId).findAny().get())
				.build();
	}

	@POST
	public Response addMessage(Message message) {
		Message newMessage = messageService.addMessage(message);
		return Response.status(Status.CREATED).entity(newMessage).build();
	}

	@PUT
	@Path("/{messageId}")
	public Response updateMessage(@PathParam("messageId") long messageId, Message message) {
		message.setId(messageId);
		return Response.status(Status.OK).entity(messageService.updateMessage(message)).build();
	}

	@DELETE
	@Path("/{messageId}")
	public Response deleteMessage(@PathParam("messageId") long messageId) {
		boolean flag = messageService.deleteMessage(messageId);
		if (flag)
			return Response.status(Status.OK).entity("Data Removed").build();

		return Response.status(Status.INTERNAL_SERVER_ERROR).entity("Unable to remove Data").build();
	}

	/*
	 * @GET public List<Message> getMessage(@QueryParam("author") String authorName)
	 * { if (authorName == null) return messageService.getAllMessages();
	 * 
	 * return messageService.getAllMessages().stream().filter(t ->
	 * t.getAuthor().equalsIgnoreCase(authorName)) .collect(Collectors.toList()); }
	 */
}
