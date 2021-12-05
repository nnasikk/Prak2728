package prak_27_28;

import java.awt.image.BufferedImage;
import java.io.File;

public class Foto {
    public File[] files;


    public Foto(File[] files)
    {
        this.files = files;
    }

    public File[] getFiles() {
        return files;
    }
}
