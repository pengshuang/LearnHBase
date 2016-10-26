package Basic;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HBaseAdmin;

import java.io.IOException;

/**
 * Created by pengshuang on 16/10/24.
 */
public class EnableTable {
    public static void main(String[] args) throws IOException
    {
        Configuration conf = HBaseConfiguration.create();
        HBaseAdmin admin = new HBaseAdmin(conf);

        Boolean b = admin.isTableEnabled("temp");
        System.out.println(b);
        if (!b) {
            admin.enableTable("temp");
            System.out.println("Table enabled!");
        }

    }
}
