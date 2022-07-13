package com.frog.demo.util;

import com.frog.demo.model.Book;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

public class BookUtil {

    public static Integer[] spiltStrToIntArray(String str) {
        if (StringUtils.isBlank(str)) return null;
        try {
            Integer[] intArray = Arrays.stream(str.split(","))
                    .map(Integer::parseInt)
                    .toArray(Integer[]::new);
            return intArray;

        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getBookIds(List<Book> books) {
        String bookIds = "";
        for (int i=0; i<books.size(); i++) {
            if (i==0)
                bookIds += i;
            else
                bookIds += "," + i;
        }
        return bookIds;
    }

}
