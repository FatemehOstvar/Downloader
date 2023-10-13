import java.io.File; 
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path; 
import java.nio.file.Paths;

public class CreateDirectoriesFromTextFiles {

    public static void main(String[] args) {
        
        // Specify the path of the folder containing the text files
        String folderPath = "D:\\Downloader\\files";  
        
        // Specify the path of the directory where the new directories will be created
        String parentDirectoryPath = "D:\\Downloader\\links"; 
        
        // Get a list of all the files in the folder
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        
        // Iterate over the files and create directories with matching names
        for (File file : files) {
            if (file.isFile() && file.getName().endsWith(".txt")) {
                String fileName = file.getName();
                String directoryName = fileName.substring(0, fileName.lastIndexOf('.'));
                
                Path directoryPath = Paths.get(parentDirectoryPath, directoryName);
                
                // Check if the directory already exists
                if (Files.exists(directoryPath)) {
                    continue; // Skip to next file    
                }
                
                try {  
                   Files.createDirectory(directoryPath);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        System.out.println("Directories created.");
    }
}