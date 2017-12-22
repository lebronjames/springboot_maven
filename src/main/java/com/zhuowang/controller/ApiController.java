package com.zhuowang.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhuowang.dto.Content;
import com.zhuowang.dto.Contract;
import com.zhuowang.dto.Product;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 测试json组装和json解析
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/api")
public class ApiController {

	private static final Logger logger = LoggerFactory.getLogger(ApiController.class);
	
    @RequestMapping(value = "/show", method=RequestMethod.POST)// 这里指定RequestMethod，如果不指定Swagger会把所有RequestMethod都输出，在实际应用中，具体指定请求类型也使接口更为严谨。
    @ApiOperation(value="测试接口", notes="测试接口详细描述")//描述一个类的一个方法，或者说一个接口
    public String show(
            @ApiParam(required=true, name="name", value="姓名")//单个参数描述
            @RequestParam(name = "name", required=true) String name){
        return name+":success";
    }
    
    //将实体对象输出     application/json
    //{"id":1,"name":"qwe","createTime":"2017-08-30 12:05:04"}
    @ResponseBody
    @RequestMapping(value = "/dtoOutput", method=RequestMethod.GET)
    public Content dtoOutput(){
    	Content content = new Content(1,"qwe",new Date());
    	return content;
    }
    
  //将对象转换成json字符串    text/plain
    //{"id":1,"name":"qwe","createTime":"2017-08-30 12:05:04"}
    @ResponseBody
    @RequestMapping(value = "/dtoToStr", method=RequestMethod.GET)
    public String dtoToStr(){
    	ObjectMapper objectMapper=new ObjectMapper();
    	Content content = new Content(1,"qwe",new Date());
    	String jsonStr="";
		try {
			jsonStr = objectMapper.writeValueAsString(content);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
    	return jsonStr;
    }
    
  //将对象转换成json字符串
    //{"id":1,"name":"qwe","createTime":"2017-08-30 12:05:04"}
    @ResponseBody
    @RequestMapping(value = "/jsonDtoToStr", method=RequestMethod.POST)
    public String jsondtoToStr(HttpServletRequest request,HttpServletResponse response){
    	ObjectMapper objectMapper=new ObjectMapper();
    	Content content = new Content(1,"qwe",new Date());
    	String jsonStr="";
		try {
			jsonStr = objectMapper.writeValueAsString(content);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
    	return jsonStr;
    }
    
    //将map转换成json字符串
    //[{"id":1,"name":"qwe","createTime":"2017-08-30 12:05:39"},{"id":2,"name":"asd","createTime":"2017-08-30 12:05:39"}]
    @ResponseBody
    @RequestMapping(value = "/mapToStr", method=RequestMethod.POST)
    public String mapToStr(){
    	ObjectMapper objectMapper=new ObjectMapper();
    	Content content = new Content(1,"qwe",new Date());
    	Content content1 = new Content(2,"asd",new Date());
    	Map<String,Object> map = new HashMap<String,Object>();
    	String jsonStr="";
		try {
			map.put("con1", content);
			map.put("con2", content1);
			jsonStr = objectMapper.writeValueAsString(map);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
    	return jsonStr;
    }
    
    //将List转换成json字符串
    //{"con1":{"id":1,"name":"qwe","createTime":"2017-08-30 12:06:02"},"con2":{"id":2,"name":"asd","createTime":"2017-08-30 12:06:02"}}
    @ResponseBody
    @RequestMapping(value = "/listToStr", method=RequestMethod.POST)
    public String listToStr(){
    	ObjectMapper objectMapper=new ObjectMapper();
    	Content content = new Content(1,"qwe",new Date());
    	Content content1 = new Content(2,"asd",new Date());
    	List<Content> list = new ArrayList<Content>();
    	String jsonStr="";
		try {
			list.add(content);
			list.add(content1);
			jsonStr = objectMapper.writeValueAsString(list);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
    	return jsonStr;
    }
    
    
    //{"id":8,"name":"xcv","createTime":"2017-08-30 13:45:04","productMap":{"prod2":{"id":7,"name":"bnm","createTime":"2017-08-30 13:45:04","contents":[{"id":1,"name":"qwe","createTime":"2017-08-30 13:45:04"},
    //{"id":2,"name":"asd","createTime":"2017-08-30 13:45:04"}]},"prod1":{"id":6,"name":"bnm","createTime":"2017-08-30 13:45:04","contents":[{"id":3,"name":"opds","createTime":"2017-08-30 13:45:04"},{"id":5,"name":"fgh","createTime":"2017-08-30 13:45:04"}]}}}
    @ResponseBody
    @RequestMapping(value = "/multiToStr", method=RequestMethod.POST)
    public String multiToStr(){
    	ObjectMapper objectMapper=new ObjectMapper();
    	Content content = new Content(1,"qwe",new Date());
    	Content content1 = new Content(2,"asd",new Date());
    	Content content2 = new Content(3,"opds",new Date());
    	Content content3 = new Content(5,"fgh",new Date());
    	Product product = new Product(6,"bnm",new Date());
    	Product product2 = new Product(7,"bnm",new Date());
    	Contract contract = new Contract(8,"xcv",new Date());
    	Map<String,Product> map = new HashMap<String,Product>();
    	List<Content> list = new ArrayList<Content>();
    	List<Content> list2 = new ArrayList<Content>();
    	String jsonStr="";
		try {
			list.add(content2);
			list.add(content3);
			product.setContents(list);
			
			list2.add(content);
			list2.add(content1);
			product2.setContents(list2);
			
			map.put("prod1", product);
			map.put("prod2", product2);
			contract.setProductMap(map);
			jsonStr = objectMapper.writeValueAsString(contract);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
    	return jsonStr;
    }
    
    //将json dto字符串转换成Map
    @ResponseBody
    @RequestMapping(value = "/jsonToDto", method=RequestMethod.GET)
    public void jsonToDto(){
    	ObjectMapper objectMapper=new ObjectMapper();
    	String str = "{\"id\":1,\"name\":\"qwe\",\"createTime\":\"2017-08-30 12:05:04\"}";
    	try {
			Map<String, Object> map = objectMapper.readValue(str, Map.class);
			for (Map.Entry<String, Object> entry : map.entrySet()) {  
			    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue().toString());  
			}  
		} catch (IOException e) {
			e.printStackTrace();
		} 
    }
    
  //将json map字符串转换成Map
    @ResponseBody
    @RequestMapping(value = "/jsonToMap", method=RequestMethod.GET)
    public void jsonToMap(){
    	ObjectMapper objectMapper=new ObjectMapper();
    	String str = "[{\"id\":1,\"name\":\"qwe\",\"createTime\":\"2017-08-30 12:05:39\"},{\"id\":2,\"name\":\"asd\",\"createTime\":\"2017-08-30 12:05:39\"}]";
    	try {
			Map<String, Object> map = objectMapper.readValue(str, Map.class);
			for (Map.Entry<String, Object> entry : map.entrySet()) {  
			    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue().toString());  
			}  
		} catch (IOException e) {
			e.printStackTrace();
		} 
    }
    
    //将json list字符串转换成Map
    @ResponseBody
    @RequestMapping(value = "/jsonToList", method=RequestMethod.GET)
    public void jsonToList(){
    	ObjectMapper objectMapper=new ObjectMapper();
    	String str = "{\"con1\":{\"id\":1,\"name\":\"qwe\",\"createTime\":\"2017-08-30 12:06:02\"},\"con2\":{\"id\":2,\"name\":\"asd\",\"createTime\":\"2017-08-30 12:06:02\"}}";
    	try {
			Map<String, Object> map = objectMapper.readValue(str, Map.class);
			for (Map.Entry<String, Object> entry : map.entrySet()) {  
			    System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue().toString());  
			}  
		} catch (IOException e) {
			e.printStackTrace();
		} 
    }
    
  //将json字符串转换成Object
    @ResponseBody
    @RequestMapping(value = "/jsonToObect", method=RequestMethod.GET)
    public void jsonToObect(){
    	ObjectMapper objectMapper=new ObjectMapper();
    	String str = "{\"id\":1,\"name\":\"qwe\",\"createTime\":\"2017-08-30 12:05:04\"}";
    	try {
			Map<String, Object> map = objectMapper.readValue(str, Map.class);
			//转换对象类型
			Content content = objectMapper.convertValue(map, Content.class);
		    System.out.println("id = " + content.getId() + ", name = " + content.getName()+ ", createTime = " + content.getCreateTime());
		} catch (IOException e) {
			e.printStackTrace();
		} 
    }
    
    //将json list字符串转换成List
    @ResponseBody
    @RequestMapping(value = "/jsonListToList", method=RequestMethod.GET)
    public void jsonListToList(){
    	ObjectMapper objectMapper=new ObjectMapper();
    	String str = "[{\"id\":1,\"name\":\"qwe\",\"createTime\":\"2017-08-30 12:05:39\"},{\"id\":2,\"name\":\"asd\",\"createTime\":\"2017-08-30 12:05:39\"}]";
    	try {
			//将json字符串转换成List
			JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, Content.class);
			List<Content> jsonToPersonList = objectMapper.readValue(str, javaType);
			for(Content content:jsonToPersonList){
				System.out.println("id = " + content.getId() + ", name = " + content.getName()+ ", createTime = " + content.getCreateTime());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
    }
    
    //json'数组'数据转化为ArrayList 
    @ResponseBody
    @RequestMapping(value = "/jsonToArrayList", method=RequestMethod.GET)
    public void jsonToArrayList(){
    	ObjectMapper objectMapper=new ObjectMapper();
    	String str = "[{\"id\":1,\"name\":\"qwe\",\"createTime\":\"2017-08-30 12:05:39\"},{\"id\":2,\"name\":\"asd\",\"createTime\":\"2017-08-30 12:05:39\"}]";
    	try {
			//将json字符串转换成List
    		Content[] contents = objectMapper.readValue(str, Content[].class);  
			for(int i = 0; i < contents.length; i++){
				List<Content> contentList = Arrays.asList(contents[i]);  
				Content content = contentList.get(0);
				System.out.println("id = " + content.getId() + ", name = " + content.getName()+ ", createTime = " + content.getCreateTime());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
    }
}
