import requests
from bs4 import BeautifulSoup
import re
import time
import pymongo

from thrift.server import TServer
from thrift.transport import TSocket
from thrift.transport import TTransport
from thrift.protocol import TBinaryProtocol

from spider.api import SpiderService

request_headers = {'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) '
                                 'Chrome/60.0.3100.0 Safari/537.36'}


class SpiderServiceHandler:

    # 连接数据库的
    def savetodb(self, movie_name, country, language, type, score):
        client = pymongo.MongoClient('192.168.49.133', 27017)
        db = client.test # 数据库
        collection = db.all  # collection名，相当于数据库的表
        movie = {
            "name": movie_name,
            "country": country,
            "language": language,
            "type": type,
            "score": score
        }
        result = collection.insert_one(movie)
        print(result)

    def GetInfo(self, url):
        wb_data = requests.get(url, headers=request_headers)
        soup = BeautifulSoup(wb_data.text, 'lxml')
        links = soup.select('#content > div > div.article > ol > li> div > div.info > div.hd > a')
        # print(1)

        for link in links:
            href = link.get('href')
            self.get_info(href)

    def get_info(self, turl):
        wb_data = requests.get(turl, headers=request_headers).text
        soup = BeautifulSoup(wb_data, "lxml")

        # 电影名（仅中文）字符串
        movie_name_tag = soup.select('#content > h1 > span:nth-child(1)')
        movie_name_list = movie_name_tag[0].get_text().split(" ")
        movie_name = movie_name_list[0]

        # 制片国家/地区 列表
        countries_form = re.compile('<span class="pl">制片国家/地区:</span>(.*?)<br/>')
        countries_all = re.findall(countries_form, wb_data)
        countries_splits = countries_all[0].split("/")
        countries = []
        for country_split in countries_splits:
            countries.append(country_split)
        country = countries[0]

        # 语言 列表
        lang_from = re.compile('<span class="pl">语言:</span>(.*?)<br/>')
        languages_all = re.findall(lang_from, wb_data)
        languages_splits = languages_all[0].split("/")
        languages = []
        for language_split in languages_splits:
            languages.append(language_split)
        language = languages[0]

        # 类型 列表
        type_form = re.compile('<span property="v:genre">(.*?)</span>')
        type_all = re.findall(type_form, wb_data)
        types = []
        for type_one in type_all:
            types.append(type_one)
        type = types[0]

        # 评分 字符串
        scores = soup.select_one('#interest_sectl > div.rating_wrap.clearbox > div.rating_self.clearfix > strong')
        score = scores.get_text()
        self.savetodb(movie_name, country, language, type, score)
        print(movie_name + ":insert ok")

    def SpiderAllUrls(self, base_url):
        urls = [base_url.format(str(number * 25)) for number in [0, 1, 3, 4]]
        # each url
        for url in urls:
            self.GetInfo(url)
            time.sleep(5)
        print("all_ok")





if __name__ == '__main__':

    serverSocket = TSocket.TServerSocket(host='127.0.0.1', port=9090)
    transportFactory = TTransport.TFramedTransportFactory()
    protocolFactory = TBinaryProtocol.TBinaryProtocolFactory()
    handler = SpiderServiceHandler()
    processor = SpiderService.Processor(handler);
    thriftServer = TServer.TSimpleServer(processor, serverSocket,transportFactory,protocolFactory)
    print("启动中")
    thriftServer.serve()
    print("退出")