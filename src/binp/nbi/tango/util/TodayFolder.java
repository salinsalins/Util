/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binp.nbi.tango.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Sanin
 */
public class TodayFolder extends File {
    
    TodayFolder(String baseDir, boolean make){
        super(todayFolder(baseDir, make).getAbsolutePath());
    }
    
    TodayFolder(String baseDir){
        this(baseDir, true);
    }
    
    TodayFolder(){
        this(null, true);
    }

    public static File todayFolder(String baseDir, boolean make) {
        SimpleDateFormat ydf = new SimpleDateFormat("yyyy");
        SimpleDateFormat mdf = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat ddf = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        File folder;
        if (baseDir==null || "".equals(baseDir))
            folder = new File(new File(ydf.format(now), mdf.format(now)), ddf.format(now));
        else     {
            File dir = new File(baseDir);
            if (dir.isDirectory())
                folder = new File(new File(new File(baseDir, ydf.format(now)), mdf.format(now)), ddf.format(now));
            else
                folder = new File(new File(ydf.format(now), mdf.format(now)), ddf.format(now));
        }
        if (make) folder.mkdirs();
        return folder;
    }
    
    public static File todayFolder() {
        return todayFolder(null, true);
    }
}
