package cn.e3mall.search.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cn.e3mall.common.pojo.SearchResult;
import cn.e3mall.search.service.SearchService;

@Controller
public class SearchController {
	@Value("${SEARCH_RESULT_ROWS}")
	private int rows;
	
	@Autowired
	private SearchService searchService;
	
	@RequestMapping("/search")
	public String searchItemList(String keyword,@RequestParam(defaultValue="1") Integer page,Model model) throws Exception{
		//解决get请求乱码
		keyword = new String(keyword.getBytes("iso-8859-1"),"utf-8");
		SearchResult result = searchService.search(keyword, page, rows);
		model.addAttribute("query", keyword);//回显搜索关键词
		model.addAttribute("totalPages",result.getTotalPages());
		model.addAttribute("recourdCount",result.getRecourdCount());
		model.addAttribute("itemList",result.getItemList());
		model.addAttribute("page",page);
		//异常测试
		//int a = 1/0;
		return "search";
	}
}
