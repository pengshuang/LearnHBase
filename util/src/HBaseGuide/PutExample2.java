package HBaseGuide;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * Created by pengshuang on 16/10/27.
 */
public class PutExample2 {

    public static void main(String[] args) throws IOException{

        Configuration conf = HBaseConfiguration.create();

        HTable table = new HTable(conf, "test1");
        //检查自动刷写标识位的设置
        System.out.println("Auto flush: " + table.isAutoFlush());
        //将一些行和列数据存入 HBase
        table.setAutoFlushTo(false);

        Put put1 = new Put(Bytes.toBytes("row1"));
        put1.add(Bytes.toBytes("colfam1"), Bytes.toBytes("qual1"), Bytes.toBytes("val1"));
        table.put(put1);

        Put put2 = new Put(Bytes.toBytes("row2"));
        put2.add(Bytes.toBytes("colfam1"), Bytes.toBytes("qual1"), Bytes.toBytes("val2"));
        table.put(put2);

        Get get = new Get(Bytes.toBytes("row1"));
        //试图加载先前存储的行,结果会打印出 " Restful: keyvalues=NONE "
        Result res1 = table.get(get);
        System.out.println("Result: " + res1);
        // 强制刷写缓冲区,会导致产生一个 RPC 请求
        table.flushCommits();
        // 现在,这一行被持久化了,可以被读取了
        Result res2 = table.get(get);
        System.out.println("Result: " + res2);

    }


}
