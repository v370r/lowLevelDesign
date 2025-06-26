package factory.factory;

// File: DocumentGenerator.java

/**
 * This is the Abstract Creator.
 * It defines the abstract "factory method" that its subclasses must implement.
 * It can also contain shared business logic that uses the product created by
 * the factory method.
 */
public abstract class DocumentGenerator {

    // This is the Factory Method. It's abstract.
    public abstract Document createDocument();

    // This is an example of shared business logic.
    // It calls the factory method to get an object and then works with it.
    public void openAndSaveDocument() {
        // The call to createDocument() will be resolved by the concrete subclass (e.g.,
        // WordDocumentGenerator).
        Document doc = createDocument();
        doc.open();
        doc.save();
    }
}
// File: Demo.java
// File: Demo.java

class Demo {
    public static void main(String[] args) {
        System.out.println("--- Using the Factory Method Pattern ---");

        // 1. To create a Word document, we instantiate a WordDocumentGenerator.
        System.out.println("\nCreating a Word document generator...");
        DocumentGenerator wordGenerator = new WordDocumentGenerator();
        wordGenerator.openAndSaveDocument(); // This will create and use a WordDocument.

        // 2. To create a PDF document, we instantiate a PdfDocumentGenerator.
        System.out.println("\nCreating a PDF document generator...");
        DocumentGenerator pdfGenerator = new PdfDocumentGenerator();
        pdfGenerator.openAndSaveDocument(); // This will create and use a PdfDocument.
    }
}

// --- Supporting Files ---

// File: WordDocumentGenerator.java (A Concrete Creator)
class WordDocumentGenerator extends DocumentGenerator {
    @Override
    public Document createDocument() {
        // This creator only knows how to make Word documents.
        System.out.println("Word Generator: Creating a WordDocument.");
        return new WordDocument();
    }
}

// File: PdfDocumentGenerator.java (A Concrete Creator)
class PdfDocumentGenerator extends DocumentGenerator {
    @Override
    public Document createDocument() {
        // This creator only knows how to make PDF documents.
        System.out.println("PDF Generator: Creating a PdfDocument.");
        return new PdfDocument();
    }
}

// Document, WordDocument, and PdfDocument are the same as in the first example.
interface Document {
    void open();

    void save();
}

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