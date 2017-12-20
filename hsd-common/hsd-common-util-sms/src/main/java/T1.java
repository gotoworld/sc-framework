import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class T1 {
    static PrintWriter pw=null;
    public static void main(String[] args) throws IOException {
        pw=new PrintWriter("d:/ydm.txt");
        readfile("D:/IDE/IdeaProjects/hsd-servers");
    }
    public static boolean readfile(String filepath) throws IOException {
        try {

            File file = new File(filepath);
            if (!file.isDirectory()) {
                if(file.getName().endsWith(".java")
                        &&!file.getName().startsWith("ServiceServer")
                        &&!file.getName().startsWith("Application")
                        ){
                    Files.lines(Paths.get(file.getPath())).forEach(line->{
                        if(line.trim().length()>0
                                &&!line.trim().startsWith("import ")
                                &&!line.trim().startsWith("package ")
                                &&!line.trim().startsWith("/*")
                                &&!line.trim().startsWith("*")
                                &&!line.trim().startsWith("*/")
                                &&!line.trim().startsWith("//")
                                ){
                            pw.append(line+"\n");
                        }
                    });
                    pw.flush();
                }
            } else if (file.isDirectory()) {
                String[] filelist = file.list();
                for (int i = 0; i < filelist.length; i++) {
                    File readfile = new File(filepath + "\\" + filelist[i]);
                    if (!readfile.isDirectory()) {
                        if(readfile.getName().endsWith(".java")
                                &&!readfile.getName().startsWith("ServiceServer")
                                &&!readfile.getName().startsWith("Application")
                                ){
                            Files.lines(Paths.get(readfile.getPath())).forEach(line->{
                                if(line.trim().length()>0
                                        &&!line.trim().startsWith("import ")
                                        &&!line.trim().startsWith("package ")
                                        &&!line.trim().startsWith("/*")
                                        &&!line.trim().startsWith("*")
                                        &&!line.trim().startsWith("*/")
                                        &&!line.trim().startsWith("//")
                                        ){
                                    pw.append(line+"\n");
                                }
                            });
                            pw.flush();
                        }
                    } else if (readfile.isDirectory()) {
                        readfile(filepath + "\\" + filelist[i]);
                    }
                }

            }

        } catch (FileNotFoundException e) {
            System.out.println("readfile()   Exception:" + e.getMessage());
        }
        return true;
    }
}
