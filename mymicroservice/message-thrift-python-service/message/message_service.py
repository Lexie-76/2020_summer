import smtplib #简单邮件传输协议
from email.mime.text import MIMEText #邮件发送内容
from email.header import Header #邮件头

from thrift.server import TServer
from thrift.transport import TSocket
from thrift.transport import TTransport
from thrift.protocol import TBinaryProtocol

from message.api import MessageService

sender='mlx000127@163.com'
authorCode='shouquanma'
class MessageServiceHandler:
    def sendMobileMessage(self,mobile,message):
        print(mobile+message)
        return True

    def sendEmailMessage(self,email,message):
        print(email+message)
        messageObj = MIMEText(message,'plain','utf-8')
        messageObj['From'] = sender
        messageObj['To'] = email
        messageObj['Subject'] = Header("邮箱验证",'uf-8')

        try:
            smtpObj = smtplib.SMTP('smtp.163.com')
            smtpObj.login(sender,authorCode)
            smtpObj.sendmail(sender,[email],messageObj.as_string())
            print("发送成功")
            return True
        except smtplib.SMTPException:
            print("发送失败")
            return False


if __name__ =='__main__':
    #监听服务器端口（本机）
    serverSocket = TSocket.TServerSocket(host='127.0.0.1', port='9090')
    transportFactory = TTransport.TFramedTransportFactory()
    protocolFactory = TBinaryProtocol.TBinaryProtocolFactory()
    handler = MessageServiceHandler()
    processor = MessageService.Processor(handler);
    thriftServer = TServer.TSimpleServer(handler, serverSocket,transportFactory,protocolFactory)
    print("启动中")
    thriftServer.serve()
    print("退出")