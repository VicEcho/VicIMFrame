package com.example.demo;

import com.Application;
import com.Disruptor.DisruptorProdutor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.nio.ByteBuffer;

/**
 * @author Vic Zhang
 * @date 2020/3/11 3:27 PM
 */


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class DesruptorTest {
    @Autowired
    private DisruptorProdutor productor;

    @Test
    public void test () throws InterruptedException {
        ByteBuffer bb = ByteBuffer.allocate(8);
        for(long l = 0; true; l++) {
            bb.putLong(0, l);
//            productor.publishEvent(bb);
            Thread.sleep(1000);
        }
    }


//    @Test
//    public static void main(String[] args) throws InterruptedException{
////        DisruptorProduter producter = new DisruptorProduter();
//        ByteBuffer bb = ByteBuffer.allocate(8);
//        for(long l = 0; true; l++) {
//            bb.putLong(0, l);
//            producter.publishEvent(bb);
//            Thread.sleep(1000);
//        }
//
//
//    }
}
