package Basic;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HBaseAdmin;

import java.io.IOException;

/**
 * Created by pengshuang on 16/10/24.
 */
public class DisableTable {

    public static void main(String[] args) throws IOException
    {
        Configuration conf = HBaseConfiguration.create();
        HBaseAdmin admin = new HBaseAdmin(conf);

        Boolean b = admin.isTableDisabled("temp");
        System.out.println(b);

        if (!b) {
            admin.disableTable("temp");
            System.out.println("Table diabled!");
        }

    }
}
