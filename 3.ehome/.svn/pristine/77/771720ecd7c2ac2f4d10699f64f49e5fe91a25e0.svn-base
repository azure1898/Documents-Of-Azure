package com.its.common.utils.zipUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import com.its.common.utils.zipUtil.ZipEntry;
import com.its.common.utils.zipUtil.ZipInputStream;

public class ZipUtils{

	/**
	 * 读取zip文件，不解压缩直接解析，支持文件名中文，解决内容乱码
	 * 
	 * @param file
	 * @throws Exception
	 */
	public static void readZipCvsFile(File file) throws Exception {
		// 获得输入流，文件为zip格式，
		// 支付宝提供
		// fund_bill_20170818.zip内包含
		// 20887217759422330156_20170818_业务明细(汇总).csv
		// 20887217759422330156_20170818_业务明细.csv
		ZipInputStream in = new ZipInputStream(new FileInputStream(file));
		// 不解压直接读取,加上gbk解决乱码问题
		BufferedReader br = new BufferedReader(new InputStreamReader(in, "GBK"));
		ZipEntry zipFile;
		// 循环读取zip中的csv文件，无法使用jdk自带，因为文件名中有中文
		while ((zipFile = in.getNextEntry()) != null) {
			if (zipFile.isDirectory()) {
				// 如果是目录，不处理
			}
			
			// 获得csv名字
			String fileName = zipFile.getName();
			System.out.println("-----" + fileName);
			
			// 检测文件是否存在
			if (fileName != null && fileName.indexOf(".") != -1) {
				String line;
				while ((line = br.readLine()) != null) {
					System.out.println(line);
				}
			}
		}
		// 关闭流
		br.close();
		in.close();
	}
	
	public static void main(String[] args) {
		File file = new File("E:\\svn\\06-开发\\ehomemanagement\\Users\\fund_bill_20160405.zip");
		try {
			ZipUtils.readZipCvsFile(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
