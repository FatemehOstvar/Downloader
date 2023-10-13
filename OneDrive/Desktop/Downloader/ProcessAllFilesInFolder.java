import java.io.IOException;
import java.nio.file.*;

public class ProcessAllFilesInFolder {

    public static void main(String[] args) throws IOException {
        String sourceFolder = "D:\\Downloader\\files";
        Files.newDirectoryStream(Paths.get(sourceFolder), path -> path.toString().endsWith(".txt"))
            .forEach(path -> {
                String filename = path.getFileName().toString();
                String name = filename.substring(0, filename.lastIndexOf('.'));
                try {
                    DownloadLinks.downloadLinksForFile(name);
                } catch (IOException e) {
                    System.err.println("Failed to process file: " + name);
                    e.printStackTrace();
                }
            });
    }
}