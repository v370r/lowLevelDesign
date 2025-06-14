// File: DocumentFactory.java

/**
 * This is the Simple Factory.
 * Its one job is to create different types of documents based on a given type.
 * All the creation logic is centralized here in this one class.
 */
public class DocumentFactory {

    public Document createDocument(String type) {
        if (type == null || type.isEmpty()) {
            return null;
        }

        // The decision logic is based on a conditional.
        if ("WORD".equalsIgnoreCase(type)) {
            return new WordDocument();
        } else if ("PDF".equalsIgnoreCase(type)) {
            return new PdfDocument();
        }
        // To add a new Document type (e.g., SPREADSHEET), you MUST modify this file.

        throw new IllegalArgumentException("Unknown document type: " + type);
    }
}

// File: Demo.java

class Demo {
    public static void main(String[] args) {
        // 1. Create the one-and-only factory.
        DocumentFactory factory = new DocumentFactory();
        System.out.println("Simple Factory created.");

        System.out.println("\nRequesting a Word document...");
        // 2. Ask the factory for a specific product by type.
        Document wordDoc = factory.createDocument("WORD");
        wordDoc.open();
        wordDoc.save();

        System.out.println("\nRequesting a PDF document...");
        // 3. Ask the same factory for a different product.
        Document pdfDoc = factory.createDocument("PDF");
        pdfDoc.open();
        pdfDoc.save();
    }
}

// --- Supporting Files ---

// File: Document.java (The Product Interface)
interface Document {
    void open();

    void save();
}

// File: WordDocument.java (A Concrete Product)
class WordDocument implements Document {
    @Override
    public void open() {
        System.out.println("Opening Word document...");
    }

    @Override
    public void save() {
        System.out.println("Saving Word document...");
    }
}

// File: PdfDocument.java (A Concrete Product)
class PdfDocument implements Document {
    @Override
    public void open() {
        System.out.println("Opening PDF document...");
    }

    @Override
    public void save() {
        System.out.println("Saving PDF document...");
    }
}