package org.slsale.common;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页支持类
 * 
 * @author Administrator
 *
 */
public class PageSupport {
	private Integer totalCount;// 总记录数
	private Integer currentPageNo;// 当前页码
	private Integer pageCount;// 总页数
	private Integer pageSize=5;// 页大小

	private List items = new ArrayList();// 保存需要展示数据的集合
	private int num = 3;// 当前页之前和之后显示的页数个数 如：假设当前页是 6 共有11页 那么 显示分页条会显示 1 2 3 4
						// 5 [6] 7 8 9 10 11

	public void setTotalCount(Integer totalCount) {
		if (totalCount > 0) {
			this.totalCount = totalCount;
			this.pageCount = this.totalCount % pageSize == 0 ? this.totalCount / pageSize : (this.totalCount / pageSize) + 1;
		}

	}

	public Integer getCurrentPageNo() {
		return currentPageNo;
	}

	public void setCurrentPageNo(Integer currentPageNo) {
		this.currentPageNo = currentPageNo;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public List getItems() {
		return items;
	}

	public void setItems(List items) {
		this.items = items;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	/**
	 * 获取前一页
	 */
	public Integer getPrve() {
		return this.currentPageNo - 1;
	}

	/**
	 * 获取后一页
	 */
	public Integer getNext() {
		return this.currentPageNo + 1;
	}

	/**
	 * 判断是否有前一页
	 */
	public boolean isExistsPrve() {
		if (this.currentPageNo > 1) {
			return true;
		}
		return false;
	}

	/**
	 * 判断是否有后一页
	 */
	public boolean isExistNext() {
		if (this.pageCount != null && this.currentPageNo < this.pageCount) {
			return true;
		}
		return false;
	}

	/**
	 * 获取当前页的前num页 如共10页，当前是4，显示前num=3页，那么4-3=1，显示1 2 3
	 */
	public List<Integer> getPrevPages() {
		List<Integer> list = new ArrayList<Integer>();
		Integer _start = 1;
		if (this.currentPageNo > num) {
			_start = this.currentPageNo - num;
		}
		for (int i = _start; i < this.currentPageNo; i++) {
			list.add(i);
		}

		return list;
	}

	/**
	 * 显示当前页的后num页
	 */
	public List<Integer> getNextPages() {
		List<Integer> list = new ArrayList<Integer>();
		Integer _end = num;
		if (this.pageCount != null) {
			if ((this.currentPageNo + num) < this.pageCount) {
				_end = this.currentPageNo + num;
			} else {
				_end = this.pageCount;
			}
		}
		for (int i = this.currentPageNo + 1; i <= _end; i++) {
			list.add(i);
		}
		return list;
	}

}
