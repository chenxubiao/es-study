package com.chenxb.esstudy.ik;

import com.chenxb.esstudy.util.StringUtil;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashSet;
import java.util.Set;

/**
 * @author: chenxb
 * @date: 2018/08/17
 * @desc:
 */
public class IKAnalyzerDemo {
    public static void main(String[] args) {
        String text = "这个问题我推荐内科的医生来回答。理由是：我是药师，您目前的症状建议咨询医生后，再对药物相互作用的问题来咨询药师，有时候您认为的感冒，可能是其他疾病，只是症状相似，建议由医生先行诊断后再给出治疗方案！";

        Set<String> results = extractTagSet(text);
        for (String result : results) {
            System.out.println(result);
        }
    }

    public static Set<String> extractTagSet(String content){
        Set<String> tagSet = new HashSet<>();
        if (StringUtil.isEmpty(content)) {
            return tagSet;
        }

        StringReader reader = new StringReader(content);
        // 当为true时，分词器进行最大词长切分
        IKSegmenter ik = new IKSegmenter(reader, true);
        try {
            Lexeme lexeme;
            while (true) {
                lexeme = ik.next();
                if (lexeme == null) {
                    break;
                }
                tagSet.add(lexeme.getLexemeText());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            reader.close();
        }

        return tagSet;
    }
}
