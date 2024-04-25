interface SupportHandler {
	void handleRequest(SupportTicket ticket);
}

class SupportTicket {
	private int id;
	private String description;
	private Priority priority;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}
}

enum Priority {
	LOW, MEDIUM, HIGH
}

class HardwareHandler implements SupportHandler {
	private SupportHandler nextHandler;

	public void setNextHandler(SupportHandler nextHandler) {
		this.nextHandler = nextHandler;
	}

	public void handleRequest(SupportTicket ticket) {
		if (ticket.getPriority() == Priority.LOW) {
			System.out.println("Hardware issue handled by Level 1 support team.");
		} else {
			if (nextHandler != null) {
				nextHandler.handleRequest(ticket);
			} else {
				System.out.println("No suitable handler found for this ticket.");
			}
		}
	}
}

class SoftwareHandler implements SupportHandler {
	private SupportHandler nextHandler;

	public void setNextHandler(SupportHandler nextHandler) {
		this.nextHandler = nextHandler;
	}

	public void handleRequest(SupportTicket ticket) {
		if (ticket.getPriority() == Priority.MEDIUM) {
			System.out.println("Software issue handled by Level 2 support team.");
		} else {
			if (nextHandler != null) {
				nextHandler.handleRequest(ticket);
			} else {
				System.out.println("No suitable handler found for this ticket.");
			}
		}
	}
}

class NetworkHandler implements SupportHandler {
	private SupportHandler nextHandler;

	public void setNextHandler(SupportHandler nextHandler) {
		this.nextHandler = nextHandler;
	}

	public void handleRequest(SupportTicket ticket) {
		if (ticket.getPriority() == Priority.HIGH) {
			System.out.println("Network issue handled by Level 3 support team.");
		} else {
			if (nextHandler != null) {
				nextHandler.handleRequest(ticket);
			} else {
				System.out.println("No suitable handler found for this ticket.");
			}
		}
	}
}
