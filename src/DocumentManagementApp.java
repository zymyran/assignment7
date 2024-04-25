import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

interface DocumentStorage {
	Document uploadDocument(String documentContent, String username);
	Document downloadDocument(int documentId, String username);
	void editDocument(int documentId, String newContent, String username);
	void deleteDocument(int documentId, String username);
	List<Document> searchDocuments(String query, String username);
}

class RealDocumentStorage implements DocumentStorage {
	private Map<Integer, Document> documentMap;
	private int nextId;

	public RealDocumentStorage() {
		documentMap = new HashMap<>();
		nextId = 1;
	}

	@Override
	public Document uploadDocument(String documentContent, String username) {
		Document document = new Document(nextId++, documentContent, username);
		documentMap.put(document.getId(), document);
		return document;
	}

	@Override
	public Document downloadDocument(int documentId, String username) {
		Document document = documentMap.get(documentId);
		if (document != null) {
			return document;
		} else {
			System.out.println("Document not found");
			return null;
		}
	}

	@Override
	public void editDocument(int documentId, String newContent, String username) {
		Document document = documentMap.get(documentId);
		if (document != null) {
			document.setContent(newContent);
			System.out.println("Document edited successfully");
		} else {
			System.out.println("Document not found");
		}
	}

	@Override
	public void deleteDocument(int documentId, String username) {
		Document document = documentMap.remove(documentId);
		if (document != null) {
			System.out.println("Document deleted successfully");
		} else {
			System.out.println("Document not found");
		}
	}

	@Override
	public List<Document> searchDocuments(String query, String username) {
		List<Document> result = new ArrayList<>();
		for (Document document : documentMap.values()) {
			if (document.getContent().contains(query)) {
				result.add(document);
			}
		}
		return result;
	}
}

class DocumentStorageProxy implements DocumentStorage {
	private DocumentStorage realDocumentStorage;

	public DocumentStorageProxy() {
		this.realDocumentStorage = new RealDocumentStorage();
	}

	private Map<Integer, Document> documentMap;
	private int nextId;


	@Override
	public Document uploadDocument(String documentContent, String username) {
		Document document = new Document(nextId++, documentContent, username);
		documentMap.put(document.getId(), document);
		return document;
	}

	@Override
	public Document downloadDocument(int documentId, String username) {
		Document document = documentMap.get(documentId);
		if (document != null) {
			return document;
		} else {
			System.out.println("Document not found");
			return null;
		}
	}

	@Override
	public void editDocument(int documentId, String newContent, String username) {
		Document document = documentMap.get(documentId);
		if (document != null) {
			document.setContent(newContent);
			System.out.println("Document edited successfully");
		} else {
			System.out.println("Document not found");
		}
	}

	@Override
	public void deleteDocument(int documentId, String username) {
		Document document = documentMap.remove(documentId);
		if (document != null) {
			System.out.println("Document deleted successfully");
		} else {
			System.out.println("Document not found");
		}
	}

	@Override
	public List<Document> searchDocuments(String query, String username) {
		List<Document> result = new ArrayList<>();
		for (Document document : documentMap.values()) {
			if (document.getContent().contains(query)) {
				result.add(document);
			}
		}
		return result;
	}




}

class Document {
	private int id;
	private String content;
	private String author;

	public Document(int i, String documentContent, String username) {
		this.id = i;
		this.content = documentContent;
		this.author = username;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}

// Document management system class
class DocumentManagementSystem {
	private DocumentStorage documentStorage;

	public DocumentManagementSystem(DocumentStorage documentStorage) {
		this.documentStorage = documentStorage;
	}

}

// Client code
public class DocumentManagementApp {
	public static void main(String[] args) {
		DocumentStorage proxy = new DocumentStorageProxy();

		DocumentManagementSystem dms = new DocumentManagementSystem(proxy);

	}
}
