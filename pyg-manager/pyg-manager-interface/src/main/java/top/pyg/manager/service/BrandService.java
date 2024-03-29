package top.pyg.manager.service;

import java.util.List;
import java.util.Map;

import top.pyg.pojo.TbBrand;
import top.pyg.utils.PageResult;
import top.pyg.utils.PygResult;


public interface BrandService {
	
	/**
	 * 需求:查询所有品牌数据
	 */
	public List<TbBrand> findAll();
	/**
	 * 需求:分页展示品牌列表
	 * 参数:Integer page,Integer rows
	 * 返回值:分页包装类对象PageResult
	 */
	public PageResult findPage(Integer page,Integer rows);

	//实现品牌增加
	public PygResult add(TbBrand brand);
	
	//查找一个品牌的信息
	public TbBrand findOne(Long id);
	
	//实现品牌信息修改
	public PygResult update(TbBrand brand);

	//实现品牌条件查询  pageNum当前页   pageSize当前页记录数
	public PageResult findBrandByPage(TbBrand brand,Integer page,Integer rows);
	
	//删除品牌信息
	public PygResult delete(Long[] ids);
	
	/**
	 * 品牌下拉框
	 */
	public List<Map> selectOptionList();
	
}
