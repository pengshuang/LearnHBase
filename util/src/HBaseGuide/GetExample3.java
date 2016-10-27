package HBaseGuide;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;

import java.io.IOException;

/**
 * Created by pengshuang on 16/10/27.
 */
public class GetExample3 {

    public static void main(String[] args) throws IOException{

        Configuration conf = HBaseConfiguration.create();
        HTable table = new HTable(conf, "test1");


    }


}
