namespace java com.test.thrift.spider
namespace py spider.api

service SpiderService{
    void SpiderAllUrls(1:string url);
void savetodb(1:string movie_name,2:string country,3:string language, 4:string type, 5:string score);
void GetInfo(1:string url);
void get_info(1:string turl);
}