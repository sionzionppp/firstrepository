package com.biz.craw;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;

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

public class MangoCraw {
	
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

    public ArrayList<MangoVO> mangoCrawling(String url, String selector, int topn) {
	//public int ytnCrawling(String url, String selector) {
		// 2. SSL 체크
        if(url.indexOf("https://") >= 0){
            try {
				makeFakeCertAndSSLSetting();
			} catch (KeyManagementException | NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
        }
        
        //크롤링 하는 싸이트의 주소 구하기:http://www.naver.com:8088
        //http://www.naver.com:8088/aa/bb/index.html
        //https://www.naver.com/aa/bb/index.html
        //siteURl + /imgs/aa.jpg
        String siteURL = "";
        try {
        	siteURL += new URL(url).getProtocol() +"://"+ new URL(url).getHost();
        	if(new URL(url).getPort() != -1)
        		siteURL +=  ":"+ new URL(url).getPort();	
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
        
		Connection.Response response;
		ArrayList<MangoVO> list = new ArrayList<MangoVO>();
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
			//#contents_list > ul > li:nth-child(11) 
			
			//-1 : 전체
			int loopCnt = 0;
			if(topn == -1) loopCnt = elms.size();
			else loopCnt = topn;  //topn파라미터
			//------------------------------------
			//for(Element elm : elms) {
			for(int i=0; i<loopCnt; i++) {
				Element elm = elms.get(i);
			//------------------------------------	
				MangoVO vo  = new MangoVO();
				if(elm.select("div figure figcaption div span a").attr("href").indexOf("http") == -1) {
					vo.setUrl(siteURL + elm.select("div figure figcaption div span a").attr("href"));				
				} else { 
					vo.setUrl(elm.select("div figure figcaption div span a").attr("href"));
				}
				if(elm.select("div figure a div img").attr("data-original").indexOf("http") == -1) {
					vo.setImgsrc(siteURL + elm.select("div figure a div img").attr("data-original"));				
				} else { 
					vo.setImgsrc(elm.select("div figure a div img").attr("data-original"));
				}
				vo.setTitle(elm.select("div figure figcaption div span a h3").text());
				vo.setAddr(elm.select("div figure figcaption div p.etc").text());
				vo.setContent(elm.select("div div p.short_review").text());
				list.add(vo);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		//return list.size(); //크롤링 갯수
		return list;
	}

	
}
