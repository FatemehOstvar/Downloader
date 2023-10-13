import java.io.Console;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteStringToFile {

    public static void main(String[] args) {
        
        Console console = System.console();
        
        String fileNameAndContents = console.readLine("Enter the file name and contents: ");
        String directoryPath = "D:\\Downloader\\files";
        
        String[] lines = fileNameAndContents.split("\n");
        String fileName = lines[0];
        
        StringBuilder fileContentsBuilder = new StringBuilder();
        for (int i = 1; i < lines.length; i++) {
            fileContentsBuilder.append(lines[i]).append("\n");
        }
        
        System.out.println("Enter file contents (terminate with an empty line):");
        String line = console.readLine();
        while (!line.isEmpty()) {
            fileContentsBuilder.append(line).append("\n");
            line = console.readLine();
        }
        
        String fileContents = fileContentsBuilder.toString();
        String filePath = directoryPath + "\\" + fileName+".txt";
        
        try {
            FileWriter writer = new FileWriter(new File(filePath));
            writer.write(fileContents);
            writer.close();
            
            System.out.println("File written to " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    while (true){
        main(null);
    }
    }
}
