package HBaseGuide;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;

/**
 * Created by pengshuang on 16/10/28.
 */
public class DeleteExample1 {
    public static void main(String[] args) throws IOException {
        Configuration conf = HBaseConfiguration.create();
        HTable table = new HTable(conf, "test1");
        Delete delete = new Delete(Bytes.toBytes("row1"));
        // 设置时间戳
        delete.setTimestamp(1);
        // 删除一列中的特定版本
        delete.deleteColumn(Bytes.toBytes("colfam1"), Bytes.toBytes("qual1"), 1);
        // 删除一列中的全部版本
        delete.deleteColumns(Bytes.toBytes("colfam2"), Bytes.toBytes("qual1"));
        // 删除一列中的给定版本和所有更旧的版本
        delete.deleteColumns(Bytes.toBytes("colfam2"), Bytes.toBytes("qual3"),15);
        // 删除整个列族,包括所有的列和版本
        delete.deleteFamily(Bytes.toBytes("colfam3"));
        // 删除给定列族中的所有列的给定版本和所有更旧的版本
        delete.deleteFamily(Bytes.toBytes("colfam3"), 3);
        table.delete(delete);
        table.close();
    }
}
