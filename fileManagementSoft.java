// Code to display the welcome screen. It should display:
// Application name and the developer details 
// The details of the user interface such as options displaying the user interaction information 
// Features to accept the user input to select one of the options listed 
// The first option should return the current file names in ascending order. The root directory can be either empty or contain few files or folders in it
// The second option should return the details of the user interface such as options displaying the following:
// Add a file to the existing directory list
// You can ignore the case sensitivity of the file names 
// Delete a user specified file from the existing directory list
// You can add the case sensitivity on the file name in order to ensure that the right file is deleted from the directory list
// Return a message if FNF (File not found)
// Search a user specified file from the main directory
// You can add the case sensitivity on the file name to retrieve the correct file
// Display the result upon successful operation
// Display the result upon unsuccessful operation
// Option to navigate back to the main context
// There should be a third option to close the application

import java.util.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

// class INF extends Exception{
//     INF(String s){
//         super("Get the file not found exception");
//     }
// }

public class fileManagementSoft {

    public static void displayFiles(File currDir){
        System.out.println("Displaying files in ascending order");
        File[] files = currDir.listFiles();
        String[] fileNames = new String[files.length];
        int i=0;
        for(File file: files){
            // System.out.println(file.getName());
            fileNames[i++] = file.getName();
        }
        Arrays.sort(fileNames);
        for(String fileName: fileNames){
            System.out.println(fileName);
            // Path p = Paths.get(fileName);
            // long size = Files.size(p);
            // Paths path = Paths.get(fileName); long size = Files.size(path)
            File file = new File(fileName);
            if(file.isFile())
                System.out.println("Size of the file is: "+file.length()+" bytes");
        }
        System.out.println();
        System.out.println();
        System.out.println("Files displayed based on last modified time");
        Arrays.sort(files, new Comparator<File>() {
            @Override
            public int compare(File p1, File p2) {
                return Long.compare(p1.lastModified(), p2.lastModified());
            }
        });
        for(File file: files){
            System.out.println(file.getName());
        }
    }

    public static void countFilesAndDirectories(File currDir){
        File[] files = currDir.listFiles();
        int countFiles = 0;
        int countDirectories = 0;
        for(File file: files){
            if(file.isFile()){
                countFiles++;
            }else if(file.isDirectory()){
                countDirectories++;
            }
        }
        System.out.println("Number of files: "+countFiles);
        System.out.println("Number of directories: "+countDirectories);
    }




    public static void addFile(String fileName){
        File newFile = new File(fileName);
        try{
            if(newFile.createNewFile()){
                System.out.println("File created");
            }else{
                System.out.println("File already exists");
            }
        }catch(IOException e){
            System.out.println("An error occured");
            e.printStackTrace();
        }
    }

    public static void SearchFile(String fileName, File currDir){
        File[] files = currDir.listFiles();
        String[] fileNames = new String[files.length];
        int flag = 0;
        int i=0;
        for(File file: files){
            // System.out.println(file.getName());
            fileNames[i++] = file.getName();
        }
        for(String file: fileNames){
            // System.out.println(file.getName());
            if(file.equals(fileName)){
                System.out.println("File found");
                System.out.println(file);
                flag = 1;
                // break;
            }
        }
        if(flag==0){
            // System.out.println("File found");
            System.out.println("File not found");
        }

    }


    public static void main(String[] agrs){
        System.out.println("File Management Software");
        System.out.println("Developer: Shivaansh Mital");
        System.out.println("Choose any of the following options:");
        // System.out.println("");
        Scanner sc = new Scanner(System.in);
        while (true) {
        System.out.println("1. Display current file names in ascending order");
        System.out.println("2. Add or Delete a user specified file to the existing directory list");
        System.out.println("3. Get Files and Directories count in the current directory");
        System.out.println("4. Read a file and display the content of the file");
        System.out.println("5. Close the application");
        int input = sc.nextInt();
        sc.nextLine();
        if (input == 1) {
            System.out.println("Displaying current file names in ascending order");
            File currDir = new File(".");
            if (currDir.isDirectory()) {
                displayFiles(currDir);
            }
        } else if (input == 2) {
            System.out.println("Add or Delete or Search a user specified file to the existing directory list");
            System.out.println("1. Add a file");
            System.out.println("2. Delete a file");
            System.out.println("3. Search a file");
            int choice = sc.nextInt();
            sc.nextLine();
            if (choice == 1) {
                System.out.println("Enter the file name to be added");
                String fileName = sc.nextLine();
                addFile(fileName);
            } else if (choice == 2) {
                System.out.println("Enter the file name to be deleted");
                String fileName = sc.nextLine();
                File file = new File(fileName);
                if (file.delete()) {
                    System.out.println("File deleted successfully");
                } else {
                    System.out.println("File not found");
                }
            } else if (choice == 3) {
                System.out.println("Enter the file name to be searched");
                String fileName = sc.nextLine();
                File currDir = new File(".");
                SearchFile(fileName, currDir);
            } else {
                System.out.println("Invalid input");
            }
        } else if(input==3){
            countFilesAndDirectories(new File("."));
        } 
        else  if(input==4){
            System.out.println("Enter the file name to be read");
            String fileName = sc.nextLine();
            try{
                File file = new File(fileName);
                Scanner fileReader = new Scanner(file);
                while(fileReader.hasNextLine()){
                    System.out.println(fileReader.nextLine());
                }
                fileReader.close();
            }catch(FileNotFoundException e){
                System.out.println("An error occured");
                e.printStackTrace();
            }
        }
        else if (input == 5) {
            System.out.println("Closing the application");
            break;
        } else {
            System.out.println("Invalid input");
        }

    }   
    sc.close();
}

}
