/*
 * 文 件 名:  CommandUtils.java
 * 版   权: Copyright www.facegarden.com Corporation 2013 版权所有
 * 描     述:  <描述>
 * 修 改 人:  shiwei
 * 修改时间:  2013-11-13
 * 跟踪单号: <跟踪单号>
 * 修改单号: <修改单号>
 * 修改内容: <修改内容>
 */
package com.facegarden.common.utils;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  shiwei
 * @version  [版本号, 2013-11-13]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class CommandUtils
{
    private static final Log logger = LogFactory.getLog(CommandUtils.class);

    @SuppressWarnings("finally")
    public static boolean exeCommand(String command)
    {
        boolean flag = false;
        Process process = null;
        try
        {
            if (logger.isDebugEnabled())
                logger.debug("image process command is " + command);

            String osName = System.getProperty("os.name");
            if (osName.toUpperCase().startsWith("Windows".toUpperCase()))
                process = Runtime.getRuntime().exec("cmd /c " + command);
            else
                process = Runtime.getRuntime().exec(command);
            String errorMsg = convertStreamToString(process.getErrorStream());
            if (!StringUtils.isEmpty(errorMsg))
                logger.info("执行命令错误流：" + errorMsg);
            process.waitFor();
            flag = (0 == process.exitValue() || StringUtils.isEmpty(errorMsg));

            process.destroy();

            if (logger.isDebugEnabled())
                logger.debug("image process " + flag);
        }
        catch (Exception e)
        {
            logger.debug(e);
        }
        finally
        {
            return flag;
        }
    }

    public static String convertStreamToString(InputStream is)
    {
        StringBuilder sb = new StringBuilder();
        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "GBK"));

            String line = null;

            while ((line = reader.readLine()) != null)
            {
                sb.append(line + "/n");
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                is.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        return sb.toString();

    }

    public static boolean moveFile(String file, String dir)
    {
        String command = "";
        String osName = System.getProperty("os.name");
        if (osName.toUpperCase().startsWith("Windows".toUpperCase()))
            command = "move " + file + " " + dir;//XCOPY  D:\testin D:\temp\  /S /Y
        else
            command = "mv " + file + " " + dir;
        return exeCommand(command);
    }

    public static boolean deletFileDir(String dir)
    {
        String command = "";
        String osName = System.getProperty("os.name");
        if (osName.toUpperCase().startsWith("Windows".toUpperCase()))
            command = "RD /S /Q " + dir;
        else
            command = "rm -rf " + dir;
        return exeCommand(command);
    }
    
    public static boolean deletFile(String file)
    {
        File f = new File(file);
        return f.delete();
    }

    public static List<Long> getImagePixel(String filePath)
    {
        List<Long> list = new ArrayList<Long>();
        File file = new File(filePath);
        BufferedImage bi = null;
        try
        {
            bi = ImageIO.read(file);
        }
        catch (Exception e)
        {
            logger.debug(e);
        }
        list.add(new Long(bi.getWidth()));
        list.add(new Long(bi.getHeight()));
        return list;
    }
}
