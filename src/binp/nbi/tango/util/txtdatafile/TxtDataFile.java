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
public class TxtDataFile {

    File file;

    TxtDataFile(String fileName) {
        SimpleDateFormat ydf = new SimpleDateFormat("yyyy");
        SimpleDateFormat mdf = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat ddf = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        file = new File(new File(ydf.format(now), mdf.format(now)), ddf.format(now));
    }
    
    public String makeFolder() {
        String outDir=null;
        String folder = outDir + getLogFolderName();
        File file = new File(folder);
        file.mkdirs();
        //System.out.println("Created folder " + folder);
        return folder;
    }

    public static String getLogFolderName() {
        SimpleDateFormat ydf = new SimpleDateFormat("yyyy");
        SimpleDateFormat mdf = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat ddf = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        String folder = ydf.format(now) + "\\" + mdf.format(now) + "\\" + ddf.format(now);
        return folder;
    }

    public static String getFileName() {
        Date now = new Date();
        SimpleDateFormat dayFmt = new SimpleDateFormat("yyyy-MM-dd");
        String logFileName = dayFmt.format(now) + ".log";
        return logFileName;
    }

    public static Formatter openLogFile(String folder) throws IOException {
        String logFileName = folder + "\\" + getLogFileName();
        FileWriter fw = new FileWriter(logFileName, true);
        Formatter logFile = new Formatter(fw);
        String logTime = dateTimeStamp();
        logFile.format("%s", logTime);
        return logFile;
    }

    public static String dateTimeStamp() {
        return dateTimeStamp(new Date());
    }

    public static String dateTimeStamp(Date now) {
        SimpleDateFormat logTimeFmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return logTimeFmt.format(now);
    }

    public static String timeStamp() {
        Date now = new Date();
        SimpleDateFormat logTimeFmt = new SimpleDateFormat("HH:mm:ss");
        return logTimeFmt.format(now);
    }

}
