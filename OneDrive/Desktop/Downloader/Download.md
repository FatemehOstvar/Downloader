import java.io.*; 
import java.net.*;    
import java.nio.file.*;
import java.util.Scanner;
public class Download {
  public static void main(String[] args) throws IOException {
    Scanner sc = new Scanner(System.in);
    System.out.print("Enter name: "); 
    String name = sc.nextLine();   
    String filePath = "D:\\SourceFolder\\"+name+".txt";   
    String folderPath = "D:\\git.ir\\"+name;

    File folder = new File(folderPath);       
    if (!folder.exists()) {
        folder.mkdir();
    }

    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {               
        String line;              
        int i=0;     
        while ((line = br.readLine()) != null) {     
            i++;              
            URL url = new URL(line);
            try {
                downloadFile(url, folderPath, url.getFile());
                System.out.println("Downloads completed successfully.");
            } catch (Exception e) {
                System.out.println("Network failure, stopping downloads.");
                break;
            }  
        }                 
    }
}
  
public static void downloadFile(URL url, String destFolder, String fileName) throws IOException{                    
   
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("GET");
   
    int fileLength = conn.getContentLength();                                        
    InputStream in = conn.getInputStream();
    String filePath = destFolder + "/" + fileName;         
    FileOutputStream out = new FileOutputStream(filePath);         
    byte[] buffer = new byte[1024];
    int read;
      
    int i = 0;
    while ((read = in.read(buffer)) > 0) {            
        out.write(buffer, 0, read);
        i += read;               
        showDownloadProgress(i, fileLength, fileName);        
    }
     
        in.close();      
        out.close();       
}       
 
public static void showDownloadProgress(int downloaded, int fileLength, String fileName) {
    System.out.printf("\rDownloading %s%% complete", String.format("%.2f", (downloaded * 100.0) / fileLength));
    System.out.flush();   
}
}