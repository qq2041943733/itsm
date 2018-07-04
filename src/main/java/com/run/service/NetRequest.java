package com.run.service;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * @Description
 * @author
 * @Date 2018年6月21日 下午2:19:24
 * @version 1.0.0
 */
public class NetRequest {

    public static void getHtmlNode() throws Exception {
        URL url = new URL("https://item.taobao.com/item.htm?id=571255443694");
        WebClient webClient = new WebClient();  
        webClient.getOptions().setJavaScriptEnabled(false);  
        webClient.getOptions().setCssEnabled(false);  
        webClient.getOptions().setUseInsecureSSL(false);  
  
  
        //获取页面  
       // String url ="https://www.baidu.com";  
        HtmlPage page = webClient.getPage(url);  
  
        System.out.println("页面文本:"+page.getTitleText());  
        org.w3c.dom.Document ownerDocument = page.getOwnerDocument();
        //获取页面元素  
        HtmlInput htmlInput = page.getHtmlElementById("J_PromoPriceNum");  
        Document doc = Jsoup.parse(url, 100000);
        Element elementById = doc.getElementById("J_PromoPriceNum");
        String text = elementById.text();
        System.out.println(text);
    }

    public static void main(String[] args) throws Exception {
        getHtmlNode();
    }
}
