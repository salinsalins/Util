/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binp.nbi.tango.util.datafile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sanin
 */
public class DataFile extends File {
    private Formatter formatter = null;
    private BufferedReader reader = null;

    public DataFile(String fileName) throws IOException {
        super(todayFolder(true), fileName);
    }

    public static File todayFolder(boolean make) {
        SimpleDateFormat ydf = new SimpleDateFormat("yyyy");
        SimpleDateFormat mdf = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat ddf = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        File dir = new File(new File(ydf.format(now), mdf.format(now)), ddf.format(now));
        if(make) dir.mkdirs();
        return dir;
    }

    public Formatter formatter() throws IOException {
        if(reader != null) return null;
        if(formatter != null) return formatter;
        
        FileWriter fw = new FileWriter(this, true);
        BufferedWriter bw = new BufferedWriter(fw);
        formatter = new Formatter(bw);
        return formatter;
    }

    public BufferedReader reader() throws FileNotFoundException {
        if(formatter != null) return null;
        if(reader != null) return reader;

        FileReader fr = new FileReader(this);
        reader = new BufferedReader(fr);
        return reader;
    }

    public String readLine() {
        if(formatter != null) return null;
        try {
            String line = reader.readLine();
            String[] fields = line.split(";");
            int n = fields.length;
            double[] data;
            long time;
            time = 0;
            if(n > 1) {
                data = new double[n-1];
                for(int i = 1; i < n; i++) {
                    data[i-1] = Double.parseDouble(fields[i].trim());
                }
            }
            return line;
        } catch (IOException ex) {
            Logger.getLogger(DataFile.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
/*            
            //persistent rffs;
            //persistent rffn;
            //persistent rffd;
            if isempty(rffn)
                    rffn = 0;
            end
                    result = '';
            
            if fid < 0
                    return
                            end
                            if rffn <= 0
                                    rffs = fgetl(fid);
                            n = strfind(rffs, ';');
                            [rffd, rffn] = sscanf(rffs((n(1)+2):end), '%f; ');
                            cr1 = datevec([rffs(1:n(1)-1) 0], 'HH:MM:SS.FFF');
                            cr(3:6) = cr1(3:6);
                            if rffn < 24
                                    rffd((rffn+1):24) = 0;
                            end
                                    rffn = 1;
                            end
                                    result = ['<' sprintf('%+07.3f', rffd(rffn:rffn+7))];
                                    rffn = rffn + 8;
                                    if rffn > 24
                                            rffn = 0;
                                    end
                                            if feof(fid)
                                                    frewind(fid);
                                            end
                                                    %pause(0.01);
                                            end
                                                    
  */                                                  
                                                    
                                                    
                                                    
                                                    
                                                    
                                                    
                                                    
                                                    
                                                    
                                                    
                                                    
                                                    
                                                    
                                                    
		















    public static String dateTimeStamp() {
        return dateTimeStamp(new Date());
    }

    public static String dateTimeStamp(Date now) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return fmt.format(now);
    }

    public static String timeStamp() {
        Date now = new Date();
        SimpleDateFormat fmt = new SimpleDateFormat("HH:mm:ss");
        return fmt.format(now);
    }

}
