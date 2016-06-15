package binp.nbi.tango.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Formatter;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFormatter {

    private File file = null;
    private ZipOutputStream zipOutputStream = null;
    private Formatter formatter = null;

    public ZipFormatter(String fileName) throws FileNotFoundException {
        this(new File(fileName));
    }

    public ZipFormatter(File f) throws FileNotFoundException {
        file = f;
        FileOutputStream zipDest = new FileOutputStream(file);
        zipOutputStream = new ZipOutputStream(new BufferedOutputStream(zipDest));
        formatter = new Formatter(zipOutputStream);
    }

    public ZipFormatter format(String format, Object... args) {
        formatter.format(format, args);
        return this;
    }

    public void close() {
        formatter.close();
    }

    public void flush() {
        formatter.flush();
    }

    public void closeEntry() throws IOException {
        formatter.flush();
        zipOutputStream.closeEntry();
    }

    public void putNextEntry(ZipEntry e) throws IOException {
        formatter.flush();
        zipOutputStream.putNextEntry(e);
    }

    public void putNextEntry(String name) throws IOException {
        ZipEntry e = new ZipEntry(name);
        putNextEntry(e);
    }
    
    public File getFile(){
        return file;
    }

    public String getFileName(){
        return file.getAbsolutePath();
    }

    public String getName(){
        return file.getName();
    }

    public String getParent(){
        return file.getParent();
    }

    public File getParentFile(){
        return file.getParentFile();
    }
}
