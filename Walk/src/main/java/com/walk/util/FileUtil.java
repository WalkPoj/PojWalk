package com.walk.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

public class FileUtil {
    public static String getOrderIdByUUId() {
        Random random = new Random();
        SimpleDateFormat allTime = new SimpleDateFormat("MMddHHSSSmmYYYY");
        String subjectno = allTime.format(new Date()) + random.nextInt(10);
        return subjectno + random.nextInt(10);
    }
    /**
     * 递归获取某路径下的所有文件，文件夹，并输出
     */
    public static List getFiles(String path) {
        File file = new File(path);
        List<String> pname = new ArrayList<>();
        // 如果这个路径是文件夹
        if (file.isDirectory()) {
            // 获取路径下的所有文件
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                // 如果还是文件夹 递归获取里面的文件 文件夹
                if (files[i].isDirectory()) {
                    getFiles(files[i].getPath());
                } else {
                    pname.add(files[i].getName());
                }
            }
        } else {
        }
        System.out.println(pname.size());
        return pname;
    }

    /**
     * 冒泡排序，用于排序文件
     * @param arr
     */
    public static void BubbleSort(int[] arr) {
        int temp;//定义一个临时变量
        for(int i=0;i<arr.length-1;i++){//冒泡趟数
            for(int j=0;j<arr.length-i-1;j++){
                if(arr[j+1]<arr[j]){
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }
}
