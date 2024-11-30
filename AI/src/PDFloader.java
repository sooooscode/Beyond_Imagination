import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PDFLoader {
    private String pdfPath;

    public PDFLoader(String pdfPath) {
        this.pdfPath = pdfPath;
    }

    public String loadPDF() throws IOException {
        PDDocument document = PDDocument.load(new File(pdfPath));
        PDFTextStripper pdfStripper = new PDFTextStripper();
        String text = pdfStripper.getText(document);
        document.close();
        return text;
    }
}
