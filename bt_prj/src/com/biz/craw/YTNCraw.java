package com.biz.craw;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Base;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class YTNCraw {
	
	private final static String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36";
    
    // 가짜 인증서 만든 후 SSL(SecureSocketLayer) 등록
    public static void makeFakeCertAndSSLSetting() throws NoSuchAlgorithmException, KeyManagementException {
        TrustManager[] trustAllCerts = new TrustManager[] { 
        	new X509TrustManager() {
	        	public X509Certificate[] getAcceptedIssuers() {
	                return null;
	            }
	            public void checkClientTrusted(X509Certificate[] certs, String authType) {
	            }
	            public void checkServerTrusted(X509Certificate[] certs, String authType) {
	            }
        	} 
        };
        SSLContext sc = SSLContext.getInstance("SSL");
        sc.init(null, trustAllCerts, new SecureRandom());
        HttpsURLConnection.setDefaultHostnameVerifier(
            new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            }
        );
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
    }

    public ArrayList<YTNVO> ytnCrawling(String url, String selector) {
	//public int ytnCrawling(String url, String selector) {
		// 2. SSL 체크
        if(url.indexOf("https://") >= 0){
            try {
				makeFakeCertAndSSLSetting();
			} catch (KeyManagementException | NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
        }

        
//        public interface Connection {
//        	interface Base<T extends Base> {
//        	}
//        	interface Request extends Base<Request> {
//        	}
//        	interface Response extends Base<Response> {
//        	}
//        	interface KeyVal {}
//        }
        
		Connection.Response response;
		ArrayList<YTNVO> list = new ArrayList<YTNVO>();
		try {
			//Document html = Jsoup.connect(url).get();
			response = Jsoup.connect(url)
					.method(Connection.Method.GET)
					.execute();
			System.out.println(response.statusMessage());
			System.out.println(response.statusCode());
			Document html = response.parse();
			//System.out.println(doc.html());

			Elements elms = html.select(selector);
			//div#ytn_list_v2014 dl.photo_list		
			//#ytn_list_v2014 > dl:nth-child(1) > dt > a);
			//#ytn_list_v2014 > dl:nth-child(2) > dd.text
			
			//conn = db.dbConn();
			for(Element elm : elms) {
				YTNVO vo  = new YTNVO();
				Elements AtagTitle = elm.select("dt a");
				System.out.println("http://www.ytn.co.kr"+AtagTitle.attr("href"));
				System.out.println(AtagTitle.text());
				vo.setUrl("http://www.ytn.co.kr"+AtagTitle.attr("href"));
				vo.setTitle(AtagTitle.text());

				Elements AtagContents = elm.select("dd.text a");
				System.out.println(AtagContents.text());
				vo.setContent(AtagContents.text());
				
				Elements imgtag = elm.select("dd.vertical p a img"); //>dd.vertical > p > a > img 꺽쇠를 공백으로
				System.out.println(imgtag.attr("data-src"));
				vo.setImgsrc(imgtag.attr("data-src"));
				
				System.out.println();
				list.add(vo);
			}


		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//return list.size(); //크롤링 갯수
		return list;
	}

//	public static void main(String[] args) {
//		JsoupTest soup = new JsoupTest();
//		String url = "https://www.ytn.co.kr/photo/sports_list_9902.html";
//		String selector="div#ytn_list_v2014 dl.photo_list";
//		//int res = soup.ytnCrawling(url, selector);
//		
//		ArrayList<YTNVO> list = soup.ytnCrawling(url, selector);
//		System.out.println(list.size() + "건 크롤링");
//	}

	
}
