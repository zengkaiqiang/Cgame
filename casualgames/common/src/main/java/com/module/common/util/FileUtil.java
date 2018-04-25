package com.module.common.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

/**
 * 文件相关的工具类
 * author zjj
 */
public class FileUtil {

    /*拍照的照片存储位置*/
    public static final File PHOTO_DIR = new File(Environment.getExternalStorageDirectory() + "/DCIM/Camera");
    public static final File SAVE_DIR = new File(Environment.getExternalStorageDirectory() + "/huba");

    /**
     * 根据字节内容生成新文件
     *
     * @param file
     * @param datas
     * @return
     */
    public static boolean generateFile(File file, List<byte[]> datas) {
        BufferedOutputStream bos = null;
        try {
            bos = new BufferedOutputStream(new FileOutputStream(file));
            for (byte[] data : datas) {
                bos.write(data);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    /**
     * 往现有文件中追加数据
     *
     * @param file
     * @param datas
     * @return
     */
    public static boolean appendData(File file, byte[]... datas) {
        RandomAccessFile rfile = null;
        try {
            rfile = new RandomAccessFile(file, "rw");
            rfile.seek(file.length());
            for (byte[] data : datas) {
                rfile.write(data);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rfile != null) {
                try {
                    rfile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    /**
     * 创建文件夹
     */
    public static void createDir(String dir) {
        File f = new File(dir);
        if (!f.exists()) {
            f.mkdirs();
        }

    }

    /**
     * 往创建文件，并往文件中写内容
     */
    public static void writeFile(String path, String content, boolean append) {
        try {
            File f = new File(path);
            if (!f.getParentFile().exists()) {
                f.getParentFile().mkdirs();
            }
            if (!f.exists()) {
                f.createNewFile();
                f = new File(path); // 重新实例化
            }
            FileWriter fw = new FileWriter(f, append);
            if ((content != null) && !"".equals(content)) {
                fw.write(content);
                fw.flush();
            }
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除某个文件
     *
     * @param path 文件路径
     */
    public static void delFile(String path) {
        try {
            File f = new File(path);
            if (f.exists()) {
                f.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除文件夹
     *
     * @param folderPath
     */
    public static void delFolder(String folderPath) {
        try {
            delAllFile(folderPath); // 删除完里面所有内容
            String filePath = folderPath;
            filePath = filePath.toString();
            File f = new File(filePath);
            f.delete(); // 删除空文件夹
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除指定文件夹下所有文件
     *
     * @param path
     * @return
     */
    public static boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
                delFolder(path + "/" + tempList[i]);// 再删除空文件夹
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 删除文件夹内所有文件
     *
     * @throws
     * @param: path 文件夹目录
     * @param: filenameFilter 过滤器 支持null
     * @return: boolean 删除成功
     */
    public static boolean delAllFile(String path, FilenameFilter filenameFilter) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        File[] tempList = file.listFiles(filenameFilter);
        int length = tempList.length;
        for (int i = 0; i < length; i++) {

            if (tempList[i].isFile()) {
                tempList[i].delete();
            }
            if (tempList[i].isDirectory()) {
                /**
                 * 删除内部文件
                 */
                delAllFile(tempList[i].getAbsolutePath(), filenameFilter);
                /**
                 * 删除空文件夹
                 */
                String[] ifEmptyDir = tempList[i].list();
                if (null == ifEmptyDir || ifEmptyDir.length <= 0) {
                    tempList[i].delete();
                }
                flag = true;
            }
        }
        return flag;
    }

    /**
     * 文件复制类
     *
     * @param srcFile  源文件
     * @param destFile 目录文件
     * @return 是否复制成功
     */
    public static boolean copy(String srcFile, String destFile) {
        try {
            FileInputStream in = new FileInputStream(srcFile);
            FileOutputStream out = new FileOutputStream(destFile);
            byte[] bytes = new byte[1024];
            int c;
            while ((c = in.read(bytes)) != -1) {
                out.write(bytes, 0, c);
            }
            in.close();
            out.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error!" + e);
            return false;
        }
    }

    /**
     * 复制整个文件夹内容
     *
     * @param oldPath String 原文件路径 如：c:/fqf
     * @param newPath String 复制后路径 如：f:/fqf/ff
     * @return boolean
     */
    public static void copyFolder(String oldPath, String newPath) {
        (new File(newPath)).mkdirs(); // 如果文件夹不存在 则建立新文件夹
        File a = new File(oldPath);
        String[] file = a.list();
        File temp = null;
        for (int i = 0; i < file.length; i++) {
            try {
                if (oldPath.endsWith(File.separator)) {
                    temp = new File(oldPath + file[i]);
                } else {
                    temp = new File(oldPath + File.separator + file[i]);
                }

                if (temp.isFile()) {
                    FileInputStream input = new FileInputStream(temp);
                    FileOutputStream output = new FileOutputStream(newPath + "/" + (temp.getName()).toString());
                    byte[] b = new byte[1024 * 5];
                    int len;
                    while ((len = input.read(b)) != -1) {
                        output.write(b, 0, len);
                    }
                    output.flush();
                    output.close();
                    input.close();
                }
                if (temp.isDirectory()) {// 如果是子文件夹
                    copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
                }
            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
        }
    }

    /**
     * 移动文件到指定目录
     *
     * @param oldPath String 如：c:/fqf.txt
     * @param newPath String 如：d:/fqf.txt
     */
    public static void moveFile(String oldPath, String newPath) {
        copy(oldPath, newPath);
        delFile(oldPath);

    }

    /**
     * 重命名文件或文件夹
     *
     * @param resFilePath 源文件路径
     * @param newFilePath 重命名
     * @return 操作成功标识
     */
    public static boolean renameFile(String resFilePath, String newFilePath) {
        File resFile = new File(resFilePath);
        File newFile = new File(newFilePath);
        return resFile.renameTo(newFile);
    }

    /**
     * 移动文件夹到指定目录
     *
     * @param oldPath String 如：c:/fqf
     * @param newPath String 如：d:/fqf
     */
    public static void moveFolder(String oldPath, String newPath) {
        copyFolder(oldPath, newPath);
        delFolder(oldPath);
    }

    /**
     * <br>
     * Description: 将输入流保存为文件 <br>
     * Author:yangjun <br>
     *
     * @param in
     * @param fileName 文件名称
     */
    public static void saveStream2File(InputStream in, String fileName) {
        int size;
        byte[] buffer = new byte[1000];
        BufferedOutputStream bufferedOutputStream = null;
        try {
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File(fileName)));
            while ((size = in.read(buffer)) > -1) {
                bufferedOutputStream.write(buffer, 0, size);
            }
            bufferedOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 图片文件过滤器
     */
    public static FileFilter imagefileFilter = new FileFilter() {
        public boolean accept(File pathname) {
            String tmp = pathname.getName().toLowerCase();
            if (tmp.endsWith(".png") || tmp.endsWith(".jpg") || tmp.endsWith(".bmp") || tmp.endsWith(".gif") || tmp.endsWith(".jpeg")) {
                return true;
            }
            return false;
        }
    };

    /**
     * 铃声文件过滤器
     */
    public static FileFilter mp3fileFilter = new FileFilter() {
        public boolean accept(File pathname) {
            String tmp = pathname.getName().toLowerCase();
            if (tmp.endsWith(".mp3")) {
                return true;
            }
            return false;
        }
    };

    /**
     * <br>
     * 获取已存在的文件名列表 <br>
     *
     * @param dir
     * @param fileFilter
     * @param hasSuffix
     * @return
     * @author yangjun
     */
    public static List<String> getExistsFileNames(String dir, FileFilter fileFilter, boolean hasSuffix) {
        String path = dir;
        File file = new File(path);
        File[] files = file.listFiles(fileFilter);
        List<String> fileNameList = new ArrayList<String>();
        if (null != files) {
            for (File tmpFile : files) {
                String tmppath = tmpFile.getAbsolutePath();
                String fileName = getFileName(tmppath, hasSuffix);
                fileNameList.add(fileName);
            }
        }
        return fileNameList;
    }

    /**
     * 描述: 获取已存在的文件名列表 (包括子目录)
     *
     * @param dir
     * @param hasSuffix
     * @return
     * @author yangjun
     */
    public static List<String> getAllExistsFileNames(String dir, boolean hasSuffix) {
        String path = dir;
        File file = new File(path);
        File[] files = file.listFiles();
        List<String> fileNameList = new ArrayList<String>();
        if (null != files) {
            for (File tmpFile : files) {
                if (tmpFile.isDirectory()) {
                    fileNameList.addAll(getAllExistsFileNames(tmpFile.getPath(), hasSuffix));
                } else {
                    String tmp = tmpFile.getName().toLowerCase();
                    if (tmp.endsWith(".png") || tmp.endsWith(".jpg") || tmp.endsWith(".bmp") || tmp.endsWith(".gif") || tmp.endsWith(".jpeg")) {
                        fileNameList.add(tmpFile.getAbsolutePath());
                    }
                }
            }
        }
        return fileNameList;
    }

    /**
     * 从路径中获取 文件名
     *
     * @param path
     * @param hasSuffix 是否包括后缀
     * @return
     */
    public static String getFileName(String path, boolean hasSuffix) {
        if (null == path || -1 == path.lastIndexOf("/") || -1 == path.lastIndexOf("."))
            return null;
        if (!hasSuffix)
            return path.substring(path.lastIndexOf("/") + 1, path.lastIndexOf("."));
        else
            return path.substring(path.lastIndexOf("/") + 1);
    }

    /**
     * 获取目录
     *
     * @param path
     * @return
     */
    public static String getPath(String path) {
        File file = new File(path);

        try {
            if (!file.exists() || !file.isDirectory())
                file.mkdirs();
            return file.getAbsolutePath();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 指定目录下是否存在指定名称的文件
     *
     * @param dir      目录
     * @param fileName 文件名称
     * @return
     */
    public static boolean isFileExits(String dir, String fileName) {
        fileName = fileName == null ? "" : fileName;
        dir = dir == null ? "" : dir;
        int index = dir.lastIndexOf("/");
        String filePath;
        if (index == dir.length() - 1)
            filePath = dir + fileName;
        else
            filePath = dir + "/" + fileName;
        File file = new File(filePath);
        return file.exists();
    }

    /**
     * 指定路么下是否存在文件
     *
     * @param filePath 文件路径
     * @return
     */
    public static boolean isFileExits(String filePath) {
        try {
            File file = new File(filePath);
            if (file.exists())
                return true;
        } catch (Exception e) {

        }
        return false;
    }

    /**
     * 保存方法
     */
    public static void saveBitmap(Bitmap bitmap) {
        File f = new File(SAVE_DIR, System.currentTimeMillis() + ".jpg");
        try {
            SAVE_DIR.mkdirs();
            FileOutputStream out = new FileOutputStream(f);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * 获取目录文件大小
     *
     * @author zkc 2015年4月23日 下午4:26:32
     */
    public static long getDirSize(File dir) {
        if (dir == null) {
            return 0;
        }
        if (!dir.isDirectory()) {
            return 0;
        }
        long dirSize = 0;
        File[] files = dir.listFiles();
        for (File file : files) {
            if (file.isFile()) {
                dirSize += file.length();
            } else if (file.isDirectory()) {
                dirSize += file.length();
                dirSize += getDirSize(file); // 递归调用继续统计
            }
        }
        return dirSize;
    }

    /**
     * 获取文件夹总大小 B单位
     *
     * @param path
     * @return
     * @author yangjun
     */
    public static long getFileAllSize(String path) {
        try {
            File file = new File(path);
            if (file.exists()) {
                if (file.isDirectory()) {
                    File[] children = file.listFiles();
                    long size = 0;
                    for (File f : children)
                        size += getFileAllSize(f.getPath());
                    return size;
                } else {
                    long size = file.length();
                    return size;
                }
            } else {
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 读取文件内容
     *
     * @param path
     * @return
     */
    public static String readFileContent(String path) {
        StringBuffer sb = new StringBuffer();
        if (!isFileExits(path)) {
            return sb.toString();
        }
        InputStream ins = null;
        BufferedReader reader = null;
        try {
            ins = new FileInputStream(new File(path));
            reader = new BufferedReader(new InputStreamReader(ins));
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ins != null) {
                try {
                    ins.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    /**
     * 从assets目录复制文件到指定路径
     *
     * @param context
     * @param srcFileName    复制的文件名
     * @param targetDir      目标目录
     * @param targetFileName 目标文件名
     * @return
     */
    public static boolean copyAssetsFile(Context context, String srcFileName, String targetDir, String targetFileName) {
        AssetManager asm = null;
        FileOutputStream fos = null;
        DataInputStream dis = null;
        try {
            asm = context.getAssets();
            dis = new DataInputStream(asm.open(srcFileName));
            createDir(targetDir);
            File targetFile = new File(targetDir, targetFileName);
            if (targetFile.exists()) {
                targetFile.delete();
            }

            fos = new FileOutputStream(targetFile);
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = dis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
            fos.flush();
            return true;
        } catch (Exception e) {
            Log.e("e", "copy assets file failed:" + e.toString());
        } finally {
            try {
                if (fos != null)
                    fos.close();
                if (dis != null)
                    dis.close();
            } catch (Exception e2) {
            }
        }

        return false;
    }//end copyAssetsFile

    /**
     * 创建任意深度的文件所在文件夹,可以用来替代直接new File(path)。
     *
     * @param path
     * @return File对象
     */
    public static File createFile(String path) {
        File file = new File(path);

        // 寻找父目录是否存在
        File parent = new File(file.getAbsolutePath().substring(0,
                file.getAbsolutePath().lastIndexOf(File.separator)));
        // 如果父目录不存在，则递归寻找更上一层目录
        if (!parent.exists()) {
            createFile(parent.getPath());
            // 创建父目录
            parent.mkdirs();
        }

        return file;
    }

    /**
     * 获取文件大小
     *
     * @param size
     * @return
     */
    public static String getMemorySizeString(long size) {
        float result = size;
        if (result < 1024) {
            BigDecimal temp = new BigDecimal(result);
            temp = temp.setScale(2, BigDecimal.ROUND_HALF_UP);
            return temp + "Bytes";
        } else {
            result = result / 1024;
            if (result < 1024) {
                BigDecimal temp = new BigDecimal(result);
                temp = temp.setScale(2, BigDecimal.ROUND_HALF_UP);
                return temp + "KB";
            } else {
                result = result / 1024;
                if (result < 1024) {
                    BigDecimal temp = new BigDecimal(result);
                    temp = temp.setScale(2, BigDecimal.ROUND_HALF_UP);
                    return temp + "MB";
                } else {
                    result = result / 1024;
                    if (result < 1024) {
                        BigDecimal temp = new BigDecimal(result);
                        temp = temp.setScale(2, BigDecimal.ROUND_HALF_UP);
                        return temp + "GB";
                    } else {
                        result = result / 1024;
                        BigDecimal temp = new BigDecimal(result);
                        temp = temp.setScale(2, BigDecimal.ROUND_HALF_UP);
                        return temp + "TB";
                    }
                }
            }
        }
    }

    /**
     * url转化为路径
     *
     * @param url
     * @param rootpath
     * @return
     */
    public static String url2path(String url, String rootpath) {
        if (url == null)
            url = "";
        String rs = rootpath;
        String picname = getPicNameFromUrlWithSuff(url);
        rs = rs + picname;
        rs = renameRes(rs);
        return rs;
    }

    /**
     * 从图片url中获得图片名
     *
     * @param url
     * @return
     */
    public static String getPicNameFromUrlWithSuff(String url) {
        if (url == null || "".equals(url))
            return "";
        String str = url;
        String[] s = str.split("\\/");
        //当s.length为0时，会出现java.lang.StringIndexOutOfBoundsException
        if (s.length > 0) {
            str = s[s.length - 1];
        } else {
            str = "";
        }
        return str;
    }


    /**
     * @param path
     * @return
     */
    public static String renameRes(String path) {
        if (path == null) {
            return null;
        }
        return path.replace(".png", ".a").replace(".jpg", ".b");
    }


    /**
     * 获取单个文件的MD5值！
     *
     * @param file
     * @return
     */
    public static String getFileMD5(File file) {

        if (!file.isFile()) {
            return null;
        }
        MessageDigest digest = null;
        FileInputStream in = null;
        byte buffer[] = new byte[1024];
        int len;
        try {
            digest = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            while ((len = in.read(buffer, 0, 1024)) != -1) {
                digest.update(buffer, 0, len);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        BigInteger bigInt = new BigInteger(1, digest.digest());
        return bigInt.toString(16);
    }

    /**
     * 解压缩功能.
     * 将zipFile文件解压到folderPath目录下.
     *
     * @throws Exception
     */
    public static int upZipFile(File zipFile, String folderPath, String renamePath) throws ZipException, IOException {
        ZipFile zfile = new ZipFile(zipFile);
        Enumeration zList = zfile.entries();
        ZipEntry ze = null;
        byte[] buf = new byte[1024];
        while (zList.hasMoreElements()) {
            ze = (ZipEntry) zList.nextElement();
            if (ze.isDirectory()) {
                Log.d("upZipFile", "ze.getName() = " + ze.getName());
                String dirstr = folderPath + ze.getName();
                //dirstr.trim();
                dirstr = new String(dirstr.getBytes("8859_1"), "GB2312");
                Log.d("upZipFile", "str = " + dirstr);
                File f = new File(dirstr);
                f.mkdir();
                continue;
            }
            Log.d("upZipFile", "ze.getName() = " + ze.getName());
            OutputStream os = new BufferedOutputStream(new FileOutputStream(folderPath + "/" + renamePath));
            InputStream is = new BufferedInputStream(zfile.getInputStream(ze));
            int readLen = 0;
            while ((readLen = is.read(buf, 0, 1024)) != -1) {
                os.write(buf, 0, readLen);
            }
            is.close();
            os.close();
        }
        zfile.close();
        Log.d("upZipFile", "finishssssssssssssssssssss");
        return 0;
    }

    /**
     * 给定根目录，返回一个相对路径所对应的实际文件名.
     *
     * @param baseDir     指定根目录
     * @param absFileName 相对路径名，来自于ZipEntry中的name
     * @return java.io.File 实际的文件
     */
    public static File getRealFileName(String baseDir, String absFileName) {
        String[] dirs = absFileName.split("/");
        File ret = new File(baseDir);
        String substr = null;
        if (dirs.length > 1) {
            for (int i = 0; i < dirs.length - 1; i++) {
                substr = dirs[i];
                try {
                    //substr.trim();
                    substr = new String(substr.getBytes("8859_1"), "GB2312");

                } catch (UnsupportedEncodingException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                ret = new File(ret, substr);

            }
            Log.d("upZipFile", "1ret = " + ret);
            if (!ret.exists())
                ret.mkdirs();
            substr = dirs[dirs.length - 1];
            try {
                //substr.trim();
                substr = new String(substr.getBytes("8859_1"), "GB2312");
                Log.d("upZipFile", "substr = " + substr);
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            ret = new File(ret, substr);
            Log.d("upZipFile", "2ret = " + ret);
            return ret;
        }
        return ret;
    }

    public static Bitmap getBitmapFromUri(Uri uri, Context mContext) {
        try {
            // 读取uri所在的图片
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(
                    mContext.getContentResolver(), uri);
            return bitmap;
        } catch (Exception e) {
            Log.e("[Android]", e.getMessage());
            Log.e("[Android]", "目录为：" + uri);
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

}
