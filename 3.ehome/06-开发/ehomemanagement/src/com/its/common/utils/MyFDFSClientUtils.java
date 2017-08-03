package com.its.common.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.csource.common.MyException;
import org.csource.myfastdfs.MyFastDFSClient;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import sun.misc.BASE64Decoder;

public class MyFDFSClientUtils {

    private static final String fdfsConfigFilename = "etc/fdfs_client.conf";
    private static final String fdhtConfigFilename = "etc/fdht_client.conf";
    private static final String fdhtNamespace = "fdfs";
    public static final String FDFS_URL = "http://218.28.28.186:9092/";

    public MyFDFSClientUtils() {
    }

    /**
     * 文件上传
     * 
     * @param file
     *            MultipartFile 待上传文件
     * @return
     * @throws MyException
     * @throws IOException
     */
    public static String uploadFile(HttpServletRequest request, MultipartFile file) throws IOException, MyException {
        return uploadFile(file, request, false);
    }

    /**
     * 文件上传
     * 
     * @param file_base64
     *            String 待上传文件
     * @param file_ext_name
     *            String 待上传文件后缀名
     * @return
     * @throws MyException
     * @throws IOException
     */
    public static String uploadFile(String file_base64, String file_ext_name, HttpServletRequest request)
            throws IOException, MyException {
        return uploadFile(file_base64, file_ext_name, false, request);
    }

    /**
     * 文件上传,并存放未750 * 563，220 * 165，134 * 100三版文件 对应文件名分别为
     * 文件ID,文件ID_COMPASS1,文件ID_COMPASS2
     * 
     * @param file
     *            待上传文件
     * @param copassFlg
     *            是否压缩
     * @return
     * @throws MyException
     * @throws IOException
     */
    public static String uploadFile(MultipartFile file, HttpServletRequest request, boolean copassFlg)
            throws IOException, MyException {

        String ctxPath = RequestContextUtils.getWebApplicationContext(request).getServletContext().getRealPath("/");
        MyFastDFSClient.init(ctxPath + fdfsConfigFilename, ctxPath + fdhtConfigFilename);
        MyFastDFSClient myFDFSClient = new MyFastDFSClient(fdhtNamespace);

        String originalFileName = file.getOriginalFilename();

        final String file_ext_name = originalFileName.substring(originalFileName.lastIndexOf(".") + 1).toUpperCase();

        File tempFile = null;
        tempFile = File.createTempFile("tmp", "." + file_ext_name);
        file.transferTo(tempFile);

        // 文件名以当前时间+3位随机数构成
        int i = (int) (Math.random() * 900) + 100;
        String my_file_id = DateUtils.getDate("yyyyMMddHHmmss") + String.valueOf(i);

        int result;
        if ((result = myFDFSClient.upload_file(my_file_id, tempFile.getAbsolutePath(), file_ext_name)) != 0) {
            System.err.println("upload_file fail, errno: " + result);
            return "";
        }
        tempFile.deleteOnExit();
        return my_file_id;
    }

    /**
     * 文件上传,并存放未750 * 563，220 * 165，134 * 100三版文件 对应文件名分别为
     * 文件ID,文件ID_COMPASS1,文件ID_COMPASS2
     * 
     * @param file
     *            待上传文件
     * @param copassFlg
     *            是否压缩
     * @return
     * @throws MyException
     * @throws IOException
     */
    public static String uploadFile(String file_base64, String file_ext_name, boolean copassFlg,
            HttpServletRequest request) throws IOException, MyException {

        String ctxPath = RequestContextUtils.getWebApplicationContext(request).getServletContext().getRealPath("/");
        MyFastDFSClient.init(ctxPath + fdfsConfigFilename, ctxPath + fdhtConfigFilename);
        MyFastDFSClient myFDFSClient = new MyFastDFSClient(fdhtNamespace);

        // 将BASE64编码开头去掉
        String img = file_base64.split(",")[1];

        // 文件名以当前时间+3位随机数构成
        int i = (int) (Math.random() * 900) + 100;
        String my_file_id = DateUtils.getDate("yyyyMMddHHmmss") + String.valueOf(i);

        int result;
        // BASE64解码器
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] file_byte = decoder.decodeBuffer(img);
        // 如果需要压缩的话
        if (copassFlg) {
            File ret = null;
            BufferedOutputStream stream = null;
            ret = File.createTempFile("tmp", "." + file_ext_name);
            FileOutputStream fstream = new FileOutputStream(ret);
            stream = new BufferedOutputStream(fstream);
            stream.write(file_byte);
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    // log.error("helper:get file from byte process
                    // error!");
                    e.printStackTrace();
                }
            }
            File file1 = resize(750, 563, ret, file_ext_name);
            if ((result = myFDFSClient.upload_file(my_file_id, file1.getAbsolutePath(),
                    file_ext_name.toUpperCase())) != 0) {
                System.err.println("upload_file fail, errno: " + result);
                return "";
            }
            File file2 = resize(220, 165, ret, file_ext_name);
            if ((result = myFDFSClient.upload_file(my_file_id + "_compress1", file2.getAbsolutePath(),
                    file_ext_name.toUpperCase())) != 0) {
                System.err.println("upload_file fail, errno: " + result);
                return "";
            }
            File file3 = resize(134, 100, ret, file_ext_name);
            if ((result = myFDFSClient.upload_file(my_file_id + "_compress2", file3.getAbsolutePath(),
                    file_ext_name.toUpperCase())) != 0) {
                System.err.println("upload_file fail, errno: " + result);
                return "";
            }
            ret.deleteOnExit();
            file1.deleteOnExit();
            file2.deleteOnExit();
            file3.deleteOnExit();
            return my_file_id;
        } else {
            if ((result = myFDFSClient.upload_file(my_file_id, file_byte, file_ext_name.toUpperCase())) != 0) {
                System.err.println("upload_file fail, errno: " + result);
                return "";
            }
            return my_file_id;
        }

    }

    /**
     * 强制压缩图片到固定的大小
     * 
     * @param w
     *            int 新宽度
     * @param h
     *            int 新高度
     * @param file
     *            file文件
     */
    private static File resize(int w, int h, File file, String file_ext_name) throws IOException {
        Image img = ImageIO.read(file); // 构造Image对象
        // SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        image.getGraphics().drawImage(img, 0, 0, w, h, null); // 绘制缩小后的图
        File destFile = File.createTempFile("tmp", "." + file_ext_name);
        FileOutputStream out = new FileOutputStream(destFile); // 输出到文件流
        // 可以正常实现bmp、png、gif转jpg
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        // JPEG编码
        encoder.encode(image);
        out.close();
        return destFile;
    }

    /**
     * 取得图片服务器中文件名
     * 
     * @param request
     * @param my_file_id
     *            文件ID
     * @return 文件服务器中文件名
     * @throws FileNotFoundException
     * @throws IOException
     * @throws MyException
     */
    public static String get_fdfs_file_id(HttpServletRequest request, String my_file_id)
            throws FileNotFoundException, IOException, MyException {
        String ctxPath = RequestContextUtils.getWebApplicationContext(request).getServletContext().getRealPath("/");
        MyFastDFSClient.init(ctxPath + fdfsConfigFilename, ctxPath + fdhtConfigFilename);
        MyFastDFSClient myFDFSClient = new MyFastDFSClient(fdhtNamespace);
        return myFDFSClient.get_fdfs_file_id(my_file_id);
    }

    /**
     * 取得图片服务器中文件URL
     * 
     * @param request
     * @param my_file_id
     *            文件ID
     * @return 文件服务器中文件URL
     * @throws FileNotFoundException
     * @throws IOException
     * @throws MyException
     */
    public static String get_fdfs_file_url(HttpServletRequest request, String my_file_id)
            throws FileNotFoundException, IOException, MyException {
    	if (StringUtils.isBlank(my_file_id)) {
    		return "";
    	}
        String ctxPath = RequestContextUtils.getWebApplicationContext(request).getServletContext().getRealPath("/");
        MyFastDFSClient.init(ctxPath + fdfsConfigFilename, ctxPath + fdhtConfigFilename);
        MyFastDFSClient myFDFSClient = new MyFastDFSClient(fdhtNamespace);
        return FDFS_URL + myFDFSClient.get_fdfs_file_id(my_file_id);
    }

    /**
     * 删除服务器中文件
     * 
     * @param request
     * @param my_file_id
     *            文件ID
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     * @throws MyException
     */
    public static int delete_file(HttpServletRequest request, String my_file_id)
            throws FileNotFoundException, IOException, MyException {
        String ctxPath = RequestContextUtils.getWebApplicationContext(request).getServletContext().getRealPath("/");
        MyFastDFSClient.init(ctxPath + fdfsConfigFilename, ctxPath + fdhtConfigFilename);
        MyFastDFSClient myFDFSClient = new MyFastDFSClient(fdhtNamespace);
        return myFDFSClient.delete_file(my_file_id);
    }
}
