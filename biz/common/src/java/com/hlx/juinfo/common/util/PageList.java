/**
 * 
 */
package com.hlx.juinfo.common.util;

import java.util.ArrayList;
import java.util.Collection;

import com.alibaba.common.lang.Paginator;

/**
 * @author maxjcs
 *
 */
	
public class PageList extends ArrayList
{
  private static final long serialVersionUID = 3257568390985103409L;
  private Paginator paginator;

  public PageList()
  {
    this.paginator = new Paginator();
  }

  public PageList(Collection c)
  {
    this(c, null);
  }

  public PageList(Collection c, Paginator paginator)
  {
    super(c);
    this.paginator = (paginator == null ? new Paginator() : paginator);
  }

  public Paginator getPaginator()
  {
    return this.paginator;
  }

  public void setPaginator(Paginator paginator)
  {
    if (paginator != null)
      this.paginator = paginator;
  }

  /** @deprecated */
  public int getPageSize()
  {
    return this.paginator.getItemsPerPage();
  }

  /** @deprecated */
  public int getTotalItem()
  {
    return this.paginator.getItems();
  }

  /** @deprecated */
  public int getTotalPage()
  {
    return this.paginator.getPages();
  }

  /** @deprecated */
  public void setPageSize(int i)
  {
    this.paginator.setItemsPerPage(i);
  }

  /** @deprecated */
  public void setTotalItem(int i)
  {
    this.paginator.setItems(i);
  }

  /** @deprecated */
  public void setTotalPage(int i)
  {
    setTotalItem(i * getPageSize());
  }
}
