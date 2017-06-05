/*
 * 文 件 名:  FtpClientUtils.java
 * 版   权: Copyright www.face-garden.com Corporation 2016 版权所有
 * 描     述:  <描述>
 * 修 改 人:  shiwei
 * 修改时间:  2016年5月26日
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.facegarden.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  shiwei
 * @version  [版本号, 2016年5月26日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class FtpClientUtils
{
    private static Logger log = LoggerFactory.getLogger(FtpClientUtils.class);

    /** <一句话功能简述>
     * <功能详细描述>
     * @param args
     * @see [类、类#方法、类#成员]
     */
    public static void main(String[] args)
    {
        FTPClient ftp = FtpClientUtils.getFTPClient("127.0.0.1", 2121, "admin", "admin");
        FtpClientUtils.uploadFile("D:/资料/2016年4月/", ftp);

    }

    public static FTPClient getFTPClient(String ip, int port, String userName, String pwd)
    {
        FTPClient ftp = new FTPClient();
        FTPClientConfig config = new FTPClientConfig();
        config.setServerLanguageCode("zh");

        ftp.configure(config);
        try
        {
            ftp.setDataTimeout(1000000000);
            ftp.setConnectTimeout(1000000000);
            ftp.setDefaultPort(port);
            ftp.connect(ip);
            ftp.login(userName, pwd, "10");
            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
        }
        catch (IOException e)
        {
            log.error("获取ftp连接失败", e);
        }
        return ftp;
    }

    public static boolean uploadFile(String src, FTPClient ftp)
    {
        boolean flag = false;
        try
        {
            File file = new File(src);
            if (file.isDirectory())
            {
                // 上传文件目录 迭代上传
                flag = upLoadFileList(ftp, file, file.getParent());
            }
            else
            {
                String path = file.getParent().replace(file.getParentFile().getParent(), "").replace("\\", "/");
                if (!ftp.changeWorkingDirectory(path))
                {
                    ftp.makeDirectory(new String(path.getBytes("GBK"), "iso-8859-1"));
                    ftp.changeWorkingDirectory(new String(path.getBytes("GBK"), "iso-8859-1"));
                }
                flag = ftp.storeFile(new String(file.getName().getBytes("GBK"), "iso-8859-1"),
                        new FileInputStream(file));
            }
            //            ftp.abort();
            // After connection attempt, you should check the reply code to verify
            // success.
            int reply = ftp.getReplyCode();

            if (!FTPReply.isPositiveCompletion(reply))
            {
                ftp.disconnect();
            }

            ftp.logout();
        }
        catch (IOException e)
        {
            log.error("上传文件到ftp失败", e);
        }
        finally
        {
            if (ftp.isConnected())
            {
                try
                {
                    ftp.disconnect();
                }
                catch (IOException ioe)
                {
                    // do nothing
                }
            }
        }
        return flag;
    }

    private static boolean upLoadFileList(FTPClient ftp, File file, String basePath)
            throws UnsupportedEncodingException, IOException, FileNotFoundException
    {
        Set<Boolean> set = new TreeSet<Boolean>();
        boolean flag = false;
        String path = "";
        if (file.getName() != null && !"".equals(file.getName().trim()))
        {
            String[] pathes = file.getName().split("/");
            for (String onepath : pathes)
            {
                if (onepath == null || "".equals(onepath.trim()))
                {
                    continue;
                }
                onepath = new String(onepath.getBytes("GBK"), "iso-8859-1");
                if (!ftp.changeWorkingDirectory(onepath))
                {
                    ftp.makeDirectory(onepath);
                    path = file.getPath().replace(basePath, "").replace("\\", "/");
                    ftp.changeWorkingDirectory(new String(path.getBytes("GBK"), "iso-8859-1"));
                }
            }

            File[] list = file.listFiles();

            for (File file2 : list)
            {
                if (file2.isDirectory())
                {
                    flag = upLoadFileList(ftp, file2, basePath);
                }
                else
                {
                    path = file2.getParent().replace(basePath, "").replace("\\", "/");
                    if (!ftp.changeWorkingDirectory(path))
                    {
                        ftp.makeDirectory(new String(path.getBytes("GBK"), "iso-8859-1"));
                        ftp.changeWorkingDirectory(new String(path.getBytes("GBK"), "iso-8859-1"));
                    }
                    flag = ftp.storeFile(new String(file2.getName().getBytes("GBK"), "iso-8859-1"),
                            new FileInputStream(file2));
                }
                set.add(Boolean.valueOf(flag));
            }

        }
        if (set.size() == 1 && set.contains(Boolean.TRUE))
            return true;
        else
            return false;
    }

}
