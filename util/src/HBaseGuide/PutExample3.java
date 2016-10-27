package HBaseGuide;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pengshuang on 16/10/27.
 */
public class PutExample3 {

    public static void main(String[] args) throws IOException{

        Configuration conf = HBaseConfiguration.create();
        HTable table = new HTable(conf, "test1");

        List<Put> puts = new ArrayList<Put>();

        Put put1 = new Put(Bytes.toBytes("row1"));
        put1.add(Bytes.toBytes("colfam1"), Bytes.toBytes("qual1"), Bytes.toBytes("val1"));
        puts.add(put1);

        Put put2 = new Put(Bytes.toBytes("row2"));
        put2.add(Bytes.toBytes("colfam1"), Bytes.toBytes("qual2"), Bytes.toBytes("val2"));
        puts.add(put2);

        table.put(puts);


    }


}
