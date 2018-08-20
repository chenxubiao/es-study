package com.chenxb.esstudy.lucene;

import com.chenxb.esstudy.lucene.bean.Article;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: chenxb
 * @date: 2018/08/20
 * @desc:
 */
public class SearchDemo {
    // 搜索索引库
    public static void main(String args[]) throws Exception {
        // 搜索条件(不区分大小写)
        String queryString = "lucene";
//        String queryString = "compass";


        // 进行搜索得到结果
        // ==============================
        // 索引库目录
        Directory directory = FSDirectory.open(new File("./indexDir/"));
        Analyzer analyzer = new StandardAnalyzer();

        // 1、把查询字符串转为查询对象(存储的都是二进制文件，普通的String肯定无法查询，因此需要转换)
        // 只在标题里面查询
        QueryParser queryParser = new QueryParser("title",analyzer);
        Query query = queryParser.parse(queryString);

        // 2、查询，得到中间结果
        IndexReader indexReader= DirectoryReader.open(directory);
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);

        //在搜索的时候，可以使用Term配合TermQuery进行查询
//        Query q = new TermQuery(new Term("contents", "lucene"));
//        TopDocs hits = searcher.search(q, 10);

        // 根据指定查询条件查询，只返回前n条结果
        TopDocs topDocs = indexSearcher.search(query, 100);
        // 总结果数
        int count = topDocs.totalHits;
        // 按照得分进行排序后的前n条结果的信息
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;

        List<Article> articalList = new ArrayList<>();
        // 3、处理中间结果
        for (ScoreDoc scoreDoc : scoreDocs) {
            // 相关度得分
            float score = scoreDoc.score;
            // Document在数据库的内部编号(是唯一的，由lucene自动生成)
            int docId = scoreDoc.doc;

            // 根据编号取出真正的Document数据
            Document doc = indexSearcher.doc(docId);

            // 把Document转成Article
            Article article = new Article(
                    Integer.parseInt(doc.getField("id").stringValue()),//需要转为int型
                    doc.getField("title").stringValue(),
                    null,
                    doc.getField("author").stringValue()
            );

            articalList.add(article);
        }

        indexReader.close();
        // ============查询结束====================


        // 显示结果
        System.out.println("总结果数量为:" + articalList.size());
        for (Article article : articalList) {
            System.out.println("id="+article.getId());
            System.out.println("title="+article.getTitle());
            System.out.println("content="+article.getContent());
        }
    }
}
