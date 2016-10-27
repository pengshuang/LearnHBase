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
public class GetExample1 {

    public static void main(String[] args) throws IOException{

        Configuration conf = HBaseConfiguration.create();
        HTable table = new HTable(conf, "test1");
        // 使用一个指定的行健构建一个 Get 实例
        Get get = new Get(Bytes.toBytes("row1"));
        // 向 Get 实例中添加一个列
        get.addColumn(Bytes.toBytes("colfam1"), Bytes.toBytes("qual1"));
        // 从 HBase 中获取指定列的行数据
        Result result = table.get(get);
        // 从返回的结果中获取对应列的数据
        byte[] val = result.getValue(Bytes.toBytes("colfam1"), Bytes.toBytes("qual1"));
        // 将数据转换为字符串打印输出
        System.out.println("Value: " + Bytes.toString(val));
    }


}
