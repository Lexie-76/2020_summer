from hdfs import Client

client = Client("http://master:9870")
#client.makedirs("/abc/xyz")
x = client.list("/")
y = client.list("/",status=True)
