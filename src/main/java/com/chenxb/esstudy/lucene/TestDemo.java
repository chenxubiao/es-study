package com.chenxb.esstudy.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.analysis.cn.ChineseAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.IOException;
import java.io.StringReader;

/**
 * @author: chenxb
 * @date: 2018/08/20
 * @desc:
 */
public class TestDemo {

    @Test
    public void testStandardAnalyzer() throws IOException {
        Analyzer analyzer = new StandardAnalyzer();
        String text = "An IndexWriter creates and maintains an index";
        TokenStream tokenStream = analyzer.tokenStream("", text);
        tokenStream.reset();
        while (tokenStream.incrementToken()) {
            CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
            System.out.println(charTermAttribute);

        }
    }

    @Test
    public void testStandardAnalyzerCn() throws IOException {
        Analyzer analyzer = new StandardAnalyzer();
        String text = "我爱北京天安门";
        TokenStream tokenStream = analyzer.tokenStream("", text);
        tokenStream.reset();
        while (tokenStream.incrementToken()) {
            CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
            System.out.println(charTermAttribute);
        }
    }

    @Test
    public void testChineseAnalyzer() throws Exception {
        //中文分词
        String text = "传智播客：Lucene是全文检索的框架";

        //单字分词StandardAnalyzer、ChineseAnalyzer
//        Analyzer analyzer=new StandardAnalyzer(Version.LUCENE_30);//也是单字分词
        Analyzer analyzer2 = new ChineseAnalyzer();//也是单字分词

        //相连的两个字组合在一起
        Analyzer analyzer3 = new CJKAnalyzer(Version.LUCENE_30);

        //词库分词IKAnalyzer
        Analyzer analyzer = new IKAnalyzer();

        testAnalyzer(analyzer, text);
    }

    /**
     * 使用指定的分词器对指定的文本进行分词，并打印结果--不需要掌握
     *
     * @param analyzer
     * @param text
     * @throws Exception
     */
    public void testAnalyzer(Analyzer analyzer, String text) throws Exception {
        System.out.println("当前使用的分词器：" + analyzer.getClass());

        TokenStream tokenStream = analyzer.tokenStream("content", new StringReader(text));
        tokenStream.addAttribute(CharTermAttribute.class);

        while (tokenStream.incrementToken()) {
            CharTermAttribute termAttribute = tokenStream.getAttribute(CharTermAttribute.class);
            System.out.println(termAttribute);
        }
    }
}
