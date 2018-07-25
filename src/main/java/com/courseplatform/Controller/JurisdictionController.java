package com.courseplatform.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.courseplatform.po.FunctionTable;
import com.courseplatform.po.JurisdictionTable;
import com.courseplatform.po.RoleTable;
import com.courseplatform.po.User;
import com.courseplatform.po.DTO.JurisdictionTableDTO;
import com.courseplatform.po.DTO.UserDTO;
import com.courseplatform.service.FunctionService;
import com.courseplatform.service.JurisdictionTableService;
import com.courseplatform.service.RoleService;
import com.courseplatform.service.UserService;

@Controller
@RequestMapping("/role/")
public class JurisdictionController {
	@Autowired
	private JurisdictionTableService jservice;
	@Autowired
	private FunctionService fservice;
	@Autowired
	private RoleService rservice;
	@Autowired
	private UserService usersevice;
	/**
	 * 权限列表
	 * @param jurisdicton
	 * @param model
	 * @return
	 */
	@RequestMapping("list.do")
	public String funcList(JurisdictionTable jurisdicton,Model model){
		List<JurisdictionTableDTO> list=new ArrayList<JurisdictionTableDTO>();
		list=jservice.select(jurisdicton);
		int count=0;
		for(JurisdictionTableDTO j:list){
			count++;
		}
		model.addAttribute("list",list );
		model.addAttribute("count",count);
		return "jsp/maneger/role";
		
	}
	/**
	 * 添加
	 * @param jurisdicton
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 */
	@RequestMapping("add.do")
	public String addRecord(HttpServletRequest request,HttpSession session){
		
		try{
			JurisdictionTable record = new JurisdictionTable();
			//获取前台输入信息
			request.setCharacterEncoding("UTF-8");
			String roleId=request.getParameter("roleid");
			String functinno=request.getParameter("functionno");
			record.setJurisdictionno("QX"+System.currentTimeMillis());
			record.setFunctionno(functinno);
			record.setRoleid(roleId);
			jservice.insert(record);
			return "jsp/maneger/role";
		}catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
			return "jsp/maneger/role";
		}
		
	}
	/**
	 * 批量删除操作
	 * @param array
	 * @return
	 */
	 @SuppressWarnings("null")  
	 @RequestMapping("deleteMap.do")
	 public @ResponseBody Integer deleteMap(@RequestParam(value = "array") String[] array,Model model) {
		
		if (array == null && array.length <= 0) {
			return 0;	
		}
		for(String s:array){
			System.out.println(s);
		}
		 System.out.println(array.length);
		 
		return jservice.deleteMap(array);
	 }
	 /**
	  * 删除
	  * @return
	  */
	 @RequestMapping("delete.do")
	 public String delete(@RequestParam(value="id",required=true) String record,Model model){
		System.out.println(record);
		jservice.delete(record);
		return funcList(new JurisdictionTable(), model);
	 }
	 /**
	  * 获取到角色和功能渲染到select标签中
	  * @return
	  */
	 @RequestMapping("addquanxian.do")
	 public String addquanxian(Model model,HttpServletRequest request){
		 try {
			 List<RoleTable> list1=rservice.list();
			 List<FunctionTable> list2=fservice.listFun();
			 model.addAttribute("list1", list1);
			 model.addAttribute("list2", list2);
			 return "jsp/maneger/addquanxian";
		} catch (Exception e) {
			model.addAttribute("message","系統繁忙，请稍后再试");
			return "pages/info";
		}
		 
	 }
	 
	 /**
	  * 根据选择的角色编号获取其未拥有的功能
	  * @param request
	  * @param response
	 * @throws IOException 
	  */
	 @RequestMapping("showFunByRole.do")
	 public void showFunByRole(Model model,HttpServletRequest request,HttpServletResponse response) throws IOException{
		 String roleId = request.getParameter("roleId");
		 PrintWriter printWriter = response.getWriter();
		 int flag = 0;
		 JSONObject jsonObject = new JSONObject();
		 List<FunctionTable> noFnctionList = new ArrayList<FunctionTable>();
		 if (roleId== null || "".equals(roleId)) {
			 System.out.println(1);
			 jsonObject.append("retCode", "000000");
			 jsonObject.append("retMag", "数据传输异常，请稍后再试");
		 }else {
			try {
				List<JurisdictionTableDTO> jurisdictionTableDTOList = jservice.selectByRole(roleId);
				List<FunctionTable> functionTableList = fservice.listFun();
				for (int i = 0; i < functionTableList.size(); i++) {
					for (int j = 0; j < jurisdictionTableDTOList.size(); j++) {
						if (functionTableList.get(i).getFunctionno().equals(jurisdictionTableDTOList.get(j).getFunctionno())) {
							flag = 1;
							break;
						}
						flag = 0;
					}
					if (flag == 0) {
						noFnctionList.add(functionTableList.get(i));
					}
				}
				StringBuffer functionListHtmlStr = new StringBuffer();
				
				functionListHtmlStr.append("<option value='0' selected='selected'>---请选择---</option>");
				for (FunctionTable functionTable : noFnctionList) {
					functionListHtmlStr.append("<option value='"+functionTable.getFunctionno()+"'>"+functionTable.getFunctionname()+"</option>");
				}
				jsonObject.append("retCode", "111");
				jsonObject.append("functionListHtmlStr", functionListHtmlStr.toString());
			} catch (Exception e) {
				System.out.println(3);
			}
			printWriter.write(jsonObject.toString());
			printWriter.flush();
		}
	 }
	 /**
	  * 添加权限
	  * @param model
	  * @param request
	  * @return
	  */
	 @RequestMapping("add1.do")
	 public String nofunc(Model model,HttpServletRequest request){
		 String roleid=request.getParameter("role");
		 String funcno=request.getParameter("function");
		 List<JurisdictionTableDTO> list=jservice.selectByRole(roleid);
		 //获取前台的功能编号与该角色id的功能编号匹配，如果存在表示已经拥有该功能
		 System.out.println(list.size());
		 for (int i = 0; i < list.size(); i++) {
			if (funcno.equals(list.get(i).getFunctionno())) {
				 model.addAttribute("message", "该功能已存在");
				 return "pages/info";
			}
		}
		 JurisdictionTable record=new JurisdictionTable();
		 record.setFunctionno(funcno);
		 record.setRoleid(roleid);
		 record.setJurisdictionno("QX"+System.currentTimeMillis());
		 jservice.insert(record);
		 return funcList(new JurisdictionTable(), model);
	 }
	 /**
	  * 功能信息
	  * @param func
	  * @param model
	  * @return
	  */
	 @RequestMapping("funcinfo.do")
	 public String funcinfo(FunctionTable func,Model model){
		 List<FunctionTable> slist=fservice.selectS(func);
		 List<FunctionTable> jlist=fservice.selectJ(func);
		 List<FunctionTable> glist=fservice.selectG(func);
		 model.addAttribute("slist", slist);
		 model.addAttribute("jlist", jlist);
		 model.addAttribute("glist", glist);
		 
		 return "jsp/maneger/funcinfo";
	 }
	 /**
	  * 获取用户列表
	  * @param userTable
	  * @param model
	  * @return
	  */
	 
//	@RequestMapping("getuser.do")
//	public String getUsser(User userTable,Model model){
//		List<UserDTO> user = usersevice.getUser(userTable);
//		model.addAttribute("user",user);
//		for(User u:user){
//			//System.out.println(u.getUserno());
//		}
//		
//		return "jsp/maneger/userfunc";
//	}
	@RequestMapping("queryfunc")
	public String queryFunc(@RequestParam("id")String id){
		return "";
	}
	@RequestMapping("getuser.do")
	public String getUser(User userTable,Model model){
		List<UserDTO> user = usersevice.getUser(userTable);
		model.addAttribute("user",user);
		for(UserDTO u:user){
			System.out.println(u.getUserNo());
		}
	
		return "jsp/maneger/role";
	}
}
