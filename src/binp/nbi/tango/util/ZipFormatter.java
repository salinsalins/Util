package binp.nbi.tango.util;

import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Flushable;
import java.io.IOException;
import java.util.Formatter;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFormatter extends File implements Closeable, Flushable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 177771L;
    private ZipOutputStream zipOutputStream = null;
    private Formatter formatter = null;

    public ZipFormatter(File file) throws FileNotFoundException {
        this(file.getAbsolutePath());
    }

    public ZipFormatter(String fileName) throws FileNotFoundException {
        super(fileName);
        initialize();
    }
    
    public ZipFormatter(String parent, String fileName) throws FileNotFoundException {
        super(parent, fileName);
        initialize();
    }
    
    public ZipFormatter(File parent, String fileName) throws FileNotFoundException {
        super(parent, fileName);
        initialize();
    }
    
    private void initialize() throws FileNotFoundException {
        FileOutputStream fos = new FileOutputStream(this);
        zipOutputStream = new ZipOutputStream(new BufferedOutputStream(fos));
        formatter = new Formatter(zipOutputStream);
    }

    public ZipFormatter format(String format, Object... args) {
        formatter.format(format, args);
        return this;
    }

    @Override
    public void close() throws IOException {
        closeEntry();
        formatter.close();
    }

    @Override
    public void flush() {
        formatter.flush();
    }
    
    void write(byte[] b) throws IOException {
        zipOutputStream.write(b);
    }
    
    void write(byte[] b, int off, int len) throws IOException {
        zipOutputStream.write(b, off, len);
    }
    
    void write(int b) throws IOException {
        zipOutputStream.write(b);
    }

    public void closeEntry() throws IOException {
        formatter.flush();
        zipOutputStream.closeEntry();
        zipOutputStream.flush();
    }

    public void putNextEntry(ZipEntry e) throws IOException {
        formatter.flush();
        zipOutputStream.putNextEntry(e);
    }

    public void putNextEntry(String name) throws IOException {
        ZipEntry e = new ZipEntry(name);
        putNextEntry(e);
    }
    
    public  boolean isOpened() {
        return (formatter != null);
    }

}