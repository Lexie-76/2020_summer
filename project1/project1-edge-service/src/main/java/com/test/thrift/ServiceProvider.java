package com.test.thrift;

import com.test.thrift.spider.SpiderService;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ServiceProvider {

    @Value("${thrift.spider.ip}")
    private String spiderServerId;
    @Value("${thrift.spider.port}")
    private int spiderServerPort;

    public SpiderService.Client getSpiderService(){
        TSocket socket = new TSocket(spiderServerId,spiderServerPort,30000);
        TTransport transport = new TFramedTransport(socket);
        try{
            transport.open();
        } catch (TTransportException e) {
            e.printStackTrace();
            return null;
        }
        TProtocol protocol = new TBinaryProtocol(transport);
        SpiderService.Client result = new SpiderService.Client(protocol);

        return result;
    }
}
