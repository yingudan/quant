package com.ujuit.quant.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author shadow
 */
public class ExportExcleUtils {

	public static void export(HttpServletResponse res, String fileName, XSSFWorkbook wb) throws Exception {

		// FileOutputStream fileOut = new
		// FileOutputStream("C:/Users/UJU205/Desktop/downLoad/ygd.xlsx");
		// wb.write(fileOut);
		// fileOut.close();

		try {
			res.setContentType("application/force-download");
			res.setHeader("Content-Disposition",
					"attachment;filename=\"" + java.net.URLEncoder.encode(fileName, "UTF-8") + ".xlsx" + "\" ");
			wb.write(res.getOutputStream());
			res.getOutputStream().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// ByteArrayOutputStream os = new ByteArrayOutputStream();
		// wb.write(os);
		// byte[] content = os.toByteArray();
		// InputStream is = new ByteArrayInputStream(content);
		// // 设置response参数，可以打开下载页面
		// res.reset();
		// res.setContentType("application/vnd.ms-excel;charset=utf-8");
		// res.setHeader("Content-Disposition",
		// "attachment;filename=" + new String((fileName + ".xls").getBytes(),
		// "iso-8859-1"));
		// ServletOutputStream out = res.getOutputStream();
		// BufferedInputStream bis = null;
		// BufferedOutputStream bos = null;
		// try {
		// bis = new BufferedInputStream(is);
		// bos = new BufferedOutputStream(out);
		// byte[] buff = new byte[2048];
		// int bytesRead;
		// // Simple read/write loop.
		// while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
		// bos.write(buff, 0, bytesRead);
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// } finally {
		// if (bis != null)
		// bis.close();
		// if (bos != null)
		// bos.close();
		// }
		//
	}

}
