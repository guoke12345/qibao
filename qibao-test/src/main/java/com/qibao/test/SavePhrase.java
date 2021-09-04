package com.qibao.test;

import com.qibao.common.utils.ConnectMysqlDBUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 保存成语
 *
 * @author qibao
 * @version v0.1 2021/8/25
 */
public class SavePhrase {
    public static String filePath = "E:\\temp\\30720.txt";
    public static int count = 100;

    public static void savePhrase() {
        try {
            Path path = Paths.get(filePath);
            List<String> allLines = Files.readAllLines(path);


            List pList = new ArrayList<Phrase>(count);
            for (String line : allLines) {
                if (line.contains("拼音")
                        && line.contains("释义")
                        && line.contains("出处")
                        && line.contains("示例")) {
                    Phrase phrase = null;
                    try {
                        phrase = getPhrase(line);
                    } catch (Exception e) {
                        System.out.println("错误：" + line);
                        continue;
                    }

                    if (pList.size() < count) {
                        pList.add(phrase);
                    } else if (pList.size() == count) {
                        insertPhraseList(pList);
                        pList.clear();
                    }
                }
            }

            if (pList.size() > 0) {
                insertPhraseList(pList);
                pList.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void insertPhraseList(List<Phrase> list) throws SQLException, ClassNotFoundException {
        StringBuilder sql = new StringBuilder("INSERT INTO `qibao`.`phrase` (`name`, `spell`, `meaning`,`reference`, `example`) VALUES");
        for (Phrase phrase : list) {
            sql.append("(")
                    .append("'" + phrase.getName() + "'").append(",")
                    .append("'" + phrase.getSpell() + "'").append(",")
                    .append("'" + phrase.getMeaning() + "'").append(",")
                    .append("'" + phrase.getReference() + "'").append(",")
                    .append("'" + phrase.getExample() + "'").append(")")
                    .append(",");
        }

        String url = "jdbc:mysql://121.36.159.16:3306/qibao?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        ConnectMysqlDBUtil connectMysqlDBUtil = new ConnectMysqlDBUtil("qibao", "000000", url);
        System.out.println(sql.delete(sql.length() - 1, sql.length()));
        try {
            connectMysqlDBUtil.exeUpdate(sql.toString());
        } catch (Exception e) {
            System.out.println("错误：" + sql);
        }
    }

    public static Phrase getPhrase(String line) {
        Phrase phrase = new Phrase();
        int spellIndex = line.indexOf("拼音");
        int meanIndex = line.indexOf("释义");
        int referenceIndex = line.indexOf("出处");
        int exampleIndex = line.indexOf("示例");
        phrase.setName(line.substring(0, spellIndex).trim());
        phrase.setSpell(line.substring(spellIndex + 3, meanIndex).trim());
        phrase.setMeaning(line.substring(meanIndex + 3, referenceIndex).trim());
        phrase.setReference(line.substring(referenceIndex + 3, exampleIndex).trim());
        phrase.setExample(line.substring(exampleIndex + 3).trim());
        return phrase;
    }


    public static void main(String[] args) {
        savePhrase();
    }
}
