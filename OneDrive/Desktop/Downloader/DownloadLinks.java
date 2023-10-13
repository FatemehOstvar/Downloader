import java.io.BufferedReader; 
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;
public class DownloadLinks {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        CreateDirectoriesFromTextFiles.main(null);
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        String filePath = "D:\\Downloader\\files"+name+".txt"; 
        String folderPath = "D:\\Downloader\\links"+name;
        downloadLinksForFile(name);  
        }   DownloadLinks d = new DownloadLinks();
     public static void downloadLinksForFile(String name) throws IOException {
        String filePath = "D:\\Downloader\\files"+name+".txt"; 
        String folderPath = "D:\\Downloader\\links"+name;
        
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdir();
        }
    
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            int i=0;
            String line;
            while ((line = br.readLine()) != null) {
              i++;
                URL url = new URL(line);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                if(conn.getResponseCode() != 200) {
                  // URL returned error, skip it
                  i++;
                  continue; 
                }
                String fileName = url.getFile().substring(url.getFile().lastIndexOf("/") + 1); 
                File outputFile = new File(folderPath, fileName);
                            
                if(outputFile.exists()) {
                    continue;
                }

                InputStream in = conn.getInputStream();  
                Files.copy(in, outputFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
                System.out.println(i);
                
            }
            System.out.println("Downloads completed successfully."); 
    }
   
}   
}