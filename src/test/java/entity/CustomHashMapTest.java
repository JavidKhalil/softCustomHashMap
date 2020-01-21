package entity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CustomHashMapTest {

    private CustomHashMap<Integer, Long> customHashMap;

    @Before
    public void initialiseBaseObject() {
        customHashMap = new CustomHashMap<Integer, Long>();
    }

    @Test
    public void PutEntryToHashMapTest() {

        for(int i = 0; i < 10; i++){
            customHashMap.put(i, new Long(i));
        }

        Assert.assertEquals(10, customHashMap.size());
    }


    @Test
    public void GetEntryFromHashMapTest() {

        for(int i = 10; i > 0; i--){
            customHashMap.put(i, new Long(i));
            Assert.assertEquals(new Long(i), customHashMap.get(i));
        }

    }
}

