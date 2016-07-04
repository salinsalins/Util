/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binp.nbi.tango.util.txtdatafile;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;

/**
 *
 * @author sanin
 */
public class TxtDataFile extends File {

    TxtDataFile(String fileName) {
        super(todayFolder(),fileName);
    }
    
    TxtDataFile() {
        this("data.txt");
    }
    
    public static File todayFolder(boolean make) {
        SimpleDateFormat ydf = new SimpleDateFormat("yyyy");
        SimpleDateFormat mdf = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat ddf = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        File folder = new File(new File(ydf.format(now), mdf.format(now)), ddf.format(now));
        if (make) folder.mkdirs();
        return folder;
    }
    
    public static File todayFolder() {
        return todayFolder(true);
    }

    public Formatter getFormatter() throws IOException {
        FileWriter fw = new FileWriter(this, true);
        return new Formatter(fw);
    }
}
