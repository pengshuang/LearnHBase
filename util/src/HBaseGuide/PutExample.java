package HBaseGuide;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * Created by pengshuang on 16/10/27.
 */
public class PutExample {

    public static void main(String[] args) throws IOException{

        Configuration conf = HBaseConfiguration.create();

        HTable table = new HTable(conf, "test1");
        //指定一行来创建一个 Put
        Put put = new Put(Bytes.toBytes("row1"));
        //向 Put 中添加一个名为 "colfam1:qual1" 的列
        put.add(Bytes.toBytes("colfam1"), Bytes.toBytes("qual1"), Bytes.toBytes("val1"));
        //将这一行存储到 HBase 表中
        table.put(put);
        // 关闭表
        table.close();
    }


}
