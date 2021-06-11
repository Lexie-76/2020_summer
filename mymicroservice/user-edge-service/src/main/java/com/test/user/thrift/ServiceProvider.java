package com.test.user.thrift;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import com.test.thrift.message.MessageService;
import com.test.thrift.user.UserService;
import org.apache.thrift.TServiceClient;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.apache.tomcat.util.net.openssl.ciphers.Protocol;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

// 实现对ThriftServer调用，信息+服务
@Component
public class ServiceProvider {
    @Value("${thrift.user.ip}")
    private String serverId;
    @Value("${thrift.user.port}")
    private int serverPort;

    @Value("${thrift.message.ip}")
    private String messageServerId;
    @Value("${thrift.message.port}")
    private int messageServerPort;

    private enum ServiceType{
        USER,
        MESSAGE
    }

    public <T> T getService(String ip,int port,ServiceType serverType){
        TSocket socket = new TSocket(ip,port,30000);
        TTransport transport = new TFramedTransport(socket);
        try{
            transport.open();
        } catch (TTransportException e) {
            e.printStackTrace();
            return null;
        }
        TProtocol protocol = new TBinaryProtocol(transport);
        TServiceClient result = null;
        switch (serverType){
            case USER:
                result = new UserService.Client(protocol);
                break;
            case MESSAGE:
                result = new MessageService.Client(protocol);
                break;
        }
        return (T) result;
    }

    public UserService.Client getUserService(){
        return getService(serverId,serverPort,ServiceType.USER);
    }

    public MessageService.Client getMessageService(){
        return getService(messageServerId,messageServerPort,ServiceType.MESSAGE);
    }


}
