package com.courseplatform.util;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * 报表输出
 */
public class ReportExport {
	
	
	/**根据传入参数给出对应的result_map
	 * @param pdf 是否生成pdf文件
	 * @param csv 是否生成csv文件
	 * @param xls 是否生成xls文件
	 * @param infoList 生成表所用到的数据
	 * @param parameters 生成表所用到的参数
	 * @param jasp 生成表所用到的jasp文件
	 * @param xmlPath 生成表所用到的jrxml文件
	 * @param conDir 绝对路径
	 * @return Map:展现在jsp页面上的表的参数
	 */
	public static Map fixreportMap(String pdf,String html,String xls,List infoList,Map parameters,String jasp,String jasperPath,String conDir)throws Exception
	{
		Map<String, Serializable> result_map = new HashMap<String, Serializable>();
		String file=UUID.randomUUID().toString();		
		/** 
		 * 判断文件夹是否存在
		 * 不存在，则创建
		 *
		 */
		File doc = new File(conDir);
		if(!doc.exists() && !doc.isDirectory()){
			doc.mkdirs();
		}

		/*设置生成的PDF文件名*/
		if(pdf!=null&&"on".equals(pdf)) 
		{
			pdf=conDir+file+".pdf";
			result_map.put("pdftag", "1");
			result_map.put("pdf",pdf);
			System.out.println("pdf:"+pdf);
		}	
		else 
		{
			result_map.put("pdftag", "0");
			pdf=null;	
		}
		
		/*设置生成的HTML文件名*/
		if(html!=null&&"on".equals(html)) 
		{
			html=conDir+file+".html";
			result_map.put("htmltag", "1");
			result_map.put("html",html);
			System.out.println("html:"+html);
		}
		else 
		{
			result_map.put("htmltag", "0");
			html=null;	
		}
		
		/*设置生成的XLS文件名*/
		if(xls!=null&&"on".equals(xls)) 
		{
			xls=conDir+file+".xls";
			result_map.put("xlstag", "1");
			result_map.put("xls",xls);
			System.out.println("xls:"+xls);
		}	
		else 
		{
			result_map.put("xlstag", "0");
			xls=null;	
		}
		
		/*create datasource*/
		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(infoList);
//		System.out.println(ds.toString());
		
		JasperReport jasperReport=null;
		/*直接将jrxml文件编译成jasper文件*/
		
		try
		{
//			jasperReport = (JasperReport)JasperCompileManager.compileReport(xmlPath); 
			jasperReport =(JasperReport)JRLoader.loadObjectFromLocation(jasperPath);
		}
		catch(Exception e)
		{
			System.out.println("获取jasper文件出错");
			e.printStackTrace();
			throw new Exception(e);				
		}
		
		try 
		{
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, ds);			
			result_map.put("jasperPrint",jasperPrint);	
			/*生成PDF文件	*/			
			if(pdf!=null)
			{	
				File destFile = new File(pdf);
				JRPdfExporter exporter = new JRPdfExporter();
				exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
				exporter.setParameter(JRExporterParameter.OUTPUT_FILE, destFile);	
				exporter.exportReport();	
			}
			/*生成HTML文件	*/	
			if(html!=null)
			{	
				File destFile = new File(html);
				JRHtmlExporter exporter = new JRHtmlExporter();
				exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
				exporter.setParameter(JRExporterParameter.OUTPUT_FILE, destFile);
				exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING,"UTF-8");
				
				exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN,Boolean.FALSE); 
				exporter.setParameter(JRHtmlExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.FALSE);
			
				exporter.exportReport();
			}
			/*生成XLS文件	*/	
			if(xls!=null)
			{	
				File destFile = new File(xls);
				JRXlsExporter exporter = new JRXlsExporter();
				exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
				exporter.setParameter(JRExporterParameter.OUTPUT_FILE, destFile);
				exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS,Boolean.TRUE);//移除行与行之间的空格
				exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND,Boolean.FALSE);//页面背景是否为白色
				exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, new Boolean(false));//去掉excel单元格自动变换
				exporter.setParameter(JRXlsExporterParameter.IS_FONT_SIZE_FIX_ENABLED, new Boolean(false));//自动修正Excel每个栏位的大小
				exporter.exportReport();	
			}	
		}
		catch (JRException ex) 
		{	
			System.out.println("生成报表文件出错");
			ex.printStackTrace();
			throw new Exception(ex);
			
		}
		return result_map;
	}
}