package com.courseplatform.Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.courseplatform.po.SharedfileTable;
import com.courseplatform.po.SharedfileexamineTable;
import com.courseplatform.po.User;
import com.courseplatform.service.SendEmailService;
import com.courseplatform.service.SharedFileExamineTableService;
import com.courseplatform.service.SharedFileTableService;
import com.courseplatform.service.UserService;
import com.courseplatform.util.MybaiduUtil;
import com.courseplatform.util.UUIDUtils;

@Controller  
@RequestMapping("/file/")  
public class FileController {
	 @Autowired
	    private UserService usersevice;
	    @Autowired
	    private SharedFileTableService shareservice;
	    @Autowired
	    private  SharedFileExamineTableService record;
	   
		@Autowired
		private SendEmailService sendemail;
	/**
     * 列举需要审核的文件
     * @param model
     * @return
     */
    @RequestMapping("filelist.do")
    public String list(Model model){
    	List<SharedfileTable> list=shareservice.listShare();
    	int count=0;
    	for(SharedfileTable share:list){
    		System.out.println(share.getSharedfilename());
    		count++;
    	}
    	model.addAttribute("list", list);
    	model.addAttribute("count", count);
    	return "jsp/files/listfile";
    }
    @RequestMapping(value="fileCheck.do" ,method=RequestMethod.GET)
    public String fileChect(@RequestParam("id")String id,Model model,HttpServletRequest request,SharedfileexamineTable shareExamine) throws IOException{
//    	String filename=request.getParameter("filename");
//    	SharedfileTable sharefile=new SharedfileTable();
//    	sharefile.setSharedfileno(filename);
//    	SharedfileTable shTable=shareservice.queryById(sharefile);
//    	//usersevice.selectByPrimaryKey(user);
//    	//上传文件编号
//    	String checkId=shTable.getSharedfileno();
//    	//文件上传人编号
//    	String uploader=shTable.getUploader();
//    	//审核人编号
//    	String userId="admin";
//    	String sfile=shTable.getSharedfileurl();
//    	sfile.trim();
    	SharedfileTable shTable=shareservice.queryById(id);
    	String sfile=shTable.getSharedfileurl();
    	File file = new File(sfile);
        InputStreamReader in = new InputStreamReader(new FileInputStream(file));
        BufferedReader bufferedReader = new BufferedReader(in);
        //Set<String> set = new HashSet<>();
        String txt;
        
        Set<String> set=new HashSet<String>();
		while((txt=bufferedReader.readLine())!=null){
			//System.out.println(txt);
			set.add(txt);
		}
		Iterator<String> it=set.iterator();
		String aaa = null;
		if(it.hasNext()){
			aaa=it.next();
			
			//System.out.println(WordFilter.isContains(txt));
			//System.out.println(WordFilter.isContains(aaa));
			UUIDUtils uuid=new UUIDUtils();
			String no=uuid.getUUID();
			
//	    	if(111==222){
//	    		
//	    		shTable.setSharedfilepassflag("1");
//	    		
//				shareExamine.setExamineno(no);
//				shareExamine.setSharedfileno(checkId);
//				shareExamine.setUploader(uploader);
//				shareExamine.setExaminer(userId);
//				shareExamine.setExamineresult("0");
//				shareExamine.setExaminefailreason("无违规操作");
//				
//	    		
//	    	}else if(WordFilter.isContains(aaa)==true){
//	    		shTable.setSharedfilepassflag("0");
//	    		//shareservice.update(shTable);
//	    		shareExamine.setExamineno(no);
//				shareExamine.setSharedfileno(checkId);
//				shareExamine.setUploader(uploader);
//				shareExamine.setExaminer(userId);
//				shareExamine.setExamineresult("1");
//	    		shareExamine.setExaminefailreason("有违规操作");
//	  
//	    	}else{
//	    		
//	    	}
		}
		System.out.println(aaa);
		MybaiduUtil util=new MybaiduUtil();
		JSONObject json=util.examine(aaa);
		Integer spam=(Integer) json.getJSONObject("result").get("spam");
		System.out.println("spam"+"+"+spam);
		JSONArray reject=  (JSONArray) json.getJSONObject("result").get("reject");
        //JSONObject obj=new JSONObject(res);
        JSONArray array=json.getJSONObject("result").getJSONArray("reject");
        List<Object> obj=new ArrayList<Object>();
        List<Object> list=new ArrayList<Object>();
        for(int i=0;i<array.length();i++){
        	JSONObject jo=array.getJSONObject(i);
        	//label请求中的违禁类型集合，一个或多个，审核通过则为空
        	obj.add(jo.get("label"));
        	
        	list.add(jo.get("hit"));
        	System.out.println(jo.get("label"));
        }
        List<Object> arr=new ArrayList<Object>();
        for(Object ob:obj){
//        	1	暴恐违禁
//        	2	文本色情
//        	3	政治敏感
//        	4	恶意推广
//        	5	低俗辱骂
        	//System.out.println(ob);
        	ob=ob.toString();
        	if("1".equals(ob)){
        		arr.add("内容存在暴恐违禁");
        		System.out.println(ob);
        	}
        	else if("2".equals(ob)){
        		arr.add("内容存在文本色情");

        	}
        	else if("3".equals(ob)){
        		arr.add("内容存在政治敏感");
        	}
        	else if("4".equals(ob)){
        		arr.add("内容存在恶意推广");
        	}
        	else if("5".equals(ob)){
        		arr.add("内容存在低俗辱骂");
        	}
        }
        String arr1=arr.toString();
        arr1=arr1.replace("[", "");
        arr1=arr1.replace("]", "");
        String obj1=arr.toString();
        System.out.println(obj1);
        String sense=list.toString();
        sense = sense.replace("[", "");
        sense = sense.replace("]", "");
        System.out.println(sense);
		bufferedReader.close();
		in.close();
		model.addAttribute("shTable", shTable);
		model.addAttribute("spam", spam);
		model.addAttribute("obj", arr1);
		model.addAttribute("list", sense);
    	//System.out.println(sfile);

// 
//    	shareservice.update(shTable);
//    	record.insertRecord(shareExamine);
    	return "jsp/files/filestatus";
    }
    @RequestMapping("shenhe.do")
    public String Shenhe(HttpServletRequest request,Model model) throws UnsupportedEncodingException{
    	request.setCharacterEncoding("utf-8");
    	String id=request.getParameter("sharedfileno");
    	
    	String flag=request.getParameter("flag");
    	SharedfileTable share=new SharedfileTable();
    	share.setSharedfileno(id);
    	SharedfileexamineTable shareExamine=new SharedfileexamineTable();
    	String uploader=request.getParameter("uploader");
    	String examiner=request.getParameter("examiner");
    	String sense=request.getParameter("sense");
    	System.out.println(sense);
    	if("1".equals(flag)){
    		share.setSharedfilepassflag(flag);
    		shareservice.update(share);
			shareExamine.setSharedfileno(id);
			shareExamine.setUploader(uploader);
			shareExamine.setExaminer(examiner);
			shareExamine.setExamineresult("1");
			shareExamine.setExaminefailreason("无违规操作");
			record.insertRecord(shareExamine);
    	}else if("0".equals(flag)){
    		
    		shareExamine.setSharedfileno(id);
			shareExamine.setUploader(uploader);
			shareExamine.setExaminer(examiner);
			shareExamine.setExamineresult("1");
			shareExamine.setExaminefailreason("有如下敏感词："+sense);
			//发送邮件
			try {
				User user=usersevice.getUserByUserNo(uploader);
				String subject="您上传的文献审核未通过";
				String content="您的文献中有许多内容不适合学生阅读："+sense;
				String toMail=user.getEmail();
				sendemail.sendSimpleMail(subject, content, toMail);
			} catch (Exception e) {
				e.printStackTrace();
			}
			record.insertRecord(shareExamine);
			shareservice.delete(share);
    	}
    	return list(model);
    }
}
