package com.test.user.thrift;

import com.test.thrift.user.UserService;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Configuration
public class ThriftServer {
    @Value("${service.port}")
    private int servicePort;

    @Resource
    private UserService.Iface userService;

    @PostConstruct
    public void startThriftServer(){
        TNonblockingServerSocket serverSocket=null;
        try {
            serverSocket = new TNonblockingServerSocket(servicePort);
        } catch (TTransportException e) {
            e.printStackTrace();
            return;
        }

        TFramedTransport.Factory transport = new TFramedTransport.Factory();

        TBinaryProtocol.Factory protocolFactory = new TBinaryProtocol.Factory();

        TProcessor processor = new UserService.Processor<>(userService);

        TNonblockingServer.Args args = new TNonblockingServer.Args(serverSocket);
        args.transportFactory(transport);
        args.protocolFactory(protocolFactory);
        args.processor(processor);

        TServer server = new TNonblockingServer(args);

        server.serve();
    }


}
