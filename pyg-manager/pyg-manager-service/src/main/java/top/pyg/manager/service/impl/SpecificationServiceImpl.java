package top.pyg.manager.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import top.pyg.mapper.TbSpecificationMapper;
import top.pyg.mapper.TbSpecificationOptionMapper;
import top.pyg.pojo.TbSpecification;
import top.pyg.pojo.TbSpecificationExample;
import top.pyg.pojo.TbSpecificationExample.Criteria;
import top.pyg.pojo.TbSpecificationOption;
import top.pyg.pojo.TbSpecificationOptionExample;
import top.pyg.manager.service.SpecificationService;

import top.pyg.utils.PageResult;
import top.pyg.vo.Specification;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class SpecificationServiceImpl implements SpecificationService {

	@Autowired
	private TbSpecificationMapper specificationMapper;
	//规格选项mapping对象
	@Autowired
	private TbSpecificationOptionMapper specificationOptionMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<TbSpecification> findAll() {
		return specificationMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<TbSpecification> page=   (Page<TbSpecification>) specificationMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(Specification specification) {
		//获取规格对象
		TbSpecification tbSpecification = specification.getTbSpecification();
		//保存规格选项
		specificationMapper.insert(tbSpecification);
		//获取规格选项集合
		List<TbSpecificationOption> specificationOptionList = specification.getSpecificationOptionList();
		//遍历集合，进行保存
		for (TbSpecificationOption tbSpecificationOption : specificationOptionList) {
			tbSpecificationOption.setSpecId(tbSpecification.getId());//设置外键
			specificationOptionMapper.insertSelective(tbSpecificationOption);
		}
		
		
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(Specification specification){
		//获取规格对象
		TbSpecification tbSpecification = specification.getTbSpecification();
		//更新
		specificationMapper.updateByPrimaryKeySelective(tbSpecification);
		//获取规格选项集合
		List<TbSpecificationOption> specificationOptionList = specification.getSpecificationOptionList();
		//创建example对象
		TbSpecificationOptionExample example = new TbSpecificationOptionExample();
		//根据外键来删除选中的规格选项,创建criteria对象
		top.pyg.pojo.TbSpecificationOptionExample.Criteria createCriteria = example.createCriteria();
		//设置查询的参数
		createCriteria.andSpecIdEqualTo(tbSpecification.getId());
		//执行操作
		specificationOptionMapper.deleteByExample(example);
		
		//循环更新规格选项
		for (TbSpecificationOption tbSpecificationOption : specificationOptionList) {
			tbSpecificationOption.setSpecId(tbSpecification.getId());
			specificationOptionMapper.insertSelective(tbSpecificationOption);
		}
		
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public Specification findOne(Long id){
		//以id号查询规格
		TbSpecification tbSpecification = specificationMapper.selectByPrimaryKey(id);
		//查询规格选项列表
		TbSpecificationOptionExample example = new TbSpecificationOptionExample();
		//创建criteria对象
		top.pyg.pojo.TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
		//设置查询条件
		criteria.andSpecIdEqualTo(id);
		//执行查询
		List<TbSpecificationOption> optionList = specificationOptionMapper.selectByExample(example);
		//返回结果对象
		Specification specification = new Specification();
		specification.setTbSpecification(tbSpecification);
		specification.setSpecificationOptionList(optionList);
		
		return specification;
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
			specificationMapper.deleteByPrimaryKey(id);
			//删除该规格的选项
			TbSpecificationOptionExample example = new TbSpecificationOptionExample();
			//创建criteria对象
			top.pyg.pojo.TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
			//指定id
			criteria.andSpecIdEqualTo(id);
			//执行删除
			specificationOptionMapper.deleteByExample(example);
		
		
		}		
	}
	
	
		@Override
	public PageResult findPage(TbSpecification specification, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		TbSpecificationExample example=new TbSpecificationExample();
		Criteria criteria = example.createCriteria();
		
		if(specification!=null){			
						if(specification.getSpecName()!=null && specification.getSpecName().length()>0){
				criteria.andSpecNameLike("%"+specification.getSpecName()+"%");
			}
	
		}
		
		Page<TbSpecification> page= (Page<TbSpecification>)specificationMapper.selectByExample(example);		
		return new PageResult(page.getTotal(), page.getResult());
	}
	
}
