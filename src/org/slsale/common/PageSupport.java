package org.slsale.common;

import java.util.ArrayList;
import java.util.List;

/**
 * ��ҳ֧����
 * 
 * @author Administrator
 *
 */
public class PageSupport {
	private Integer totalCount=0;// �ܼ�¼��
	private Integer currentPageNo=1;// ��ǰҳ��
	private Integer pageCount=1;// ��ҳ��
	private Integer pageSize=5;// ҳ��С

	private List items = new ArrayList();// ������Ҫչʾ���ݵļ���
	private int num = 3;// ��ǰҳ֮ǰ��֮����ʾ��ҳ������ �磺���赱ǰҳ�� 6 ����11ҳ ��ô ��ʾ��ҳ������ʾ 1 2 3 4
						// 5 [6] 7 8 9 10 11

	public void setTotalCount(Integer totalCount) {
		if (totalCount > 0) {
			this.totalCount = totalCount;
			//this.pageCount = (totalCount+getPageSize()-1)/getPageSize();
			this.pageCount = this.totalCount % this.pageSize == 0 ? this.totalCount / this.pageSize : (this.totalCount / this.pageSize + 1);
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
	 * ��ȡǰһҳ
	 */
	public Integer getPrve() {
		return this.currentPageNo - 1;
	}

	/**
	 * ��ȡ��һҳ
	 */
	public Integer getNext() {
		return this.currentPageNo + 1;
	}

	/**
	 * �ж��Ƿ���ǰһҳ
	 */
	public boolean isExistsPrve() {
		if (this.currentPageNo > 1) {
			return true;
		}
		return false;
	}

	/**
	 * �ж��Ƿ��к�һҳ
	 */
	public boolean isExistNext() {
		if (this.pageCount != null && this.currentPageNo < this.pageCount) {
			return true;
		}
		return false;
	}

	/**
	 * ��ȡ��ǰҳ��ǰnumҳ �繲10ҳ����ǰ��4����ʾǰnum=3ҳ����ô4-3=1����ʾ1 2 3
	 */
	public List<Integer> getPrevPages() {
		List<Integer> list = new ArrayList<Integer>();
		Integer _start = 1;
		if (currentPageNo > num) {
			_start = currentPageNo - num;
		}
		for (int i = _start; i < currentPageNo; i++) {
			list.add(i);
		}

		return list;
	}

	/**
	 * ��ʾ��ǰҳ�ĺ�numҳ
	 */
	public List<Integer> getNextPages() {
		List<Integer> list = new ArrayList<Integer>();
		Integer _end = num;
		
		if (this.pageCount != null) {
			if (num<pageCount && (currentPageNo + num) < pageCount) {
				_end = currentPageNo + _end;
			} else {
				_end = pageCount;
			}
		}
		
		for (int i = currentPageNo + 1; i <= _end; i++) {
			list.add(i);
		}
		return list;
	}

}
