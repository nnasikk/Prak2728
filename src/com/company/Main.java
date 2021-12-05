package prak_27_28;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args)
    {
        Runtime runtime = Runtime.getRuntime();
        int a = runtime.availableProcessors();

        String srcFolder = "/home/pasta/Pictures/java_start_pictures/";
        String dstFolder = "/home/pasta/Pictures/java_finish_pictures/";
        File srcDir = new File(srcFolder);

        long start = System.currentTimeMillis();

        File[] files = srcDir.listFiles();
        int n = files.length/a;
        ArrayList<File[]> array = new ArrayList<File[]>();

        try
        {
            if (!Files.exists(Paths.get(dstFolder)))
            {
                Files.createDirectories(Paths.get(dstFolder));
            }

            int i = 0;
            File[] f = new File[n];
            for(File file : files)
            {
                if(i < n)
                {
                    f[i] = file;
                    i++;
                }
                if(i >= n){
                    array.add(f);
                    i = 0;
                    f = new File[n];
                }
            }
            for(int k = 0; k < a; k++)
            {
                Foto foto = new Foto(array.get(k));
                Thread thread = new Thread(new Minimum(foto, dstFolder));
                thread.start();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        System.out.println("Duration: " + (System.currentTimeMillis() - start));
    }
}