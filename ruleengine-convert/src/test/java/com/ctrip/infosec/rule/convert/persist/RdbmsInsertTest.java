package com.ctrip.infosec.rule.convert.persist;

import com.ctrip.infosec.configs.event.DataUnitColumnType;
import com.ctrip.infosec.configs.event.DatabaseType;
import com.ctrip.infosec.configs.event.DistributionChannel;
import com.ctrip.infosec.configs.event.enums.PersistColumnSourceType;
import com.google.common.collect.Maps;
import com.ctrip.infosec.configs.utils.Utils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@Ignore
public class RdbmsInsertTest {

    private RdbmsInsert rdbmsInsert;
    PersistContext ctx;

    @Before
    public void setUp() throws Exception {
        ctx = new PersistContext();

        rdbmsInsert = new RdbmsInsert();
        DistributionChannel channel = new DistributionChannel();
        channel.setDatabaseType(DatabaseType.AllInOne_SqlServer);
        channel.setDatabaseURL("PayBaseDB_INSERT_1");
        channel.setSchema("PayBaseDB ");

        rdbmsInsert.setChannel(channel);
        /**
         * 随便选了张column少的表。
         */
        rdbmsInsert.setTable("PaymentRiskControl");

        Map<String, PersistColumnProperties> map = new HashMap<String, PersistColumnProperties>();

        PersistColumnProperties pcp1 = new PersistColumnProperties();
        pcp1.setPersistColumnSourceType(PersistColumnSourceType.DB_PK);
        pcp1.setColumnType(DataUnitColumnType.Int);
        map.put("RID", pcp1);

        PersistColumnProperties pcp2 = new PersistColumnProperties();
        pcp2.setValue(17);
        pcp2.setColumnType(DataUnitColumnType.Long);
        pcp2.setPersistColumnSourceType(PersistColumnSourceType.DATA_UNIT);
        map.put("OrderID1", pcp2);

//        PersistColumnProperties pcp3=new PersistColumnProperties();
//        pcp3.setValue(new String[]{null,"from offline test"});
//        pcp3.setColumnType(DataUnitColumnType.String);
//        pcp3.setPersistColumnSourceType(PersistColumnSourceType.DATA_UNIT);
//        map.put("CheckValue",pcp3);
        rdbmsInsert.setColumnPropertiesMap(map);

    }

    @Test
//    @Ignore
    public void testExecute() throws Exception {
        rdbmsInsert.execute(ctx);

        Map<String, Object> exposedValue = rdbmsInsert.getExposedValue();
        System.out.println(Utils.JSON.toPrettyJSONString(exposedValue));

    }

//    @Test
//    public void testCreateSPA() throws Exception {
//        String spa = rdbmsInsert.createSPA(rdbmsInsert.getTable(),rdbmsInsert.getColumnPropertiesMap());
//        System.out.println(spa);
//    }
//    @Test
//    public void testSetValues() throws Exception {
//        rdbmsInsert.setValues()
//
//
//    }
//    @Test
//    public void testValueByPersistSourceType() throws Exception {
//        Map<String, PersistColumnProperties> columnPropertiesMap = rdbmsInsert.getColumnPropertiesMap();
//        for(Map.Entry<String, PersistColumnProperties> entry: columnPropertiesMap.entrySet()) {
//            System.out.println("key:"+entry.getKey()+"           value:"+entry.getValue().getValue());
//            Object o = rdbmsInsert.valueByPersistSourceType(entry.getValue(), ctx);
//            System.out.println(o);
//            System.out.println(entry.getValue().getValue());
//        }
//    }
    @Test
    public void testGetExposedValue() throws Exception {
        RdbmsInsert insert = new RdbmsInsert();
        DistributionChannel ch = new DistributionChannel();
        ch.setChannelNo("");
        ch.setDatabaseType(DatabaseType.AllInOne_SqlServer);
        ch.setChannelDesc("CardRiskDB_INSERT_1");
        ch.setDatabaseURL("CardRiskDB_INSERT_1");
        insert.setChannel(ch);
        insert.setTable("InfoSecurity_DealInfo");
        PersistColumnProperties props = new PersistColumnProperties();
        props.setPersistColumnSourceType(PersistColumnSourceType.DB_PK);
        props.setColumnType(DataUnitColumnType.Long);
        Map<String, PersistColumnProperties> map = Maps.newHashMap();
        map.put("ReqID", props);
        insert.setColumnPropertiesMap(map);
        insert.execute(ctx);
        System.out.println(insert.getExposedValue());
    }
}
