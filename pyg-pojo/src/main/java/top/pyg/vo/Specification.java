package top.pyg.vo;

import java.io.Serializable;
import java.util.List;

import top.pyg.pojo.TbSpecification;
import top.pyg.pojo.TbSpecificationOption;

public class Specification implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1543809573273121919L;

	private TbSpecification tbSpecification;
	private List<TbSpecificationOption> specificationOptionList;
	
	public TbSpecification getTbSpecification() {
		return tbSpecification;
	}
	public void setTbSpecification(TbSpecification tbSpecification) {
		this.tbSpecification = tbSpecification;
	}
	public List<TbSpecificationOption> getSpecificationOptionList() {
		return specificationOptionList;
	}
	public void setSpecificationOptionList(List<TbSpecificationOption> specificationOptionList) {
		this.specificationOptionList = specificationOptionList;
	}
	
	
}
