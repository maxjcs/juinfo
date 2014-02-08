/**
 * 
 */
package com.hlx.juinfo.common.util;

import java.util.List;

import com.alibaba.common.lang.Paginator;

/**
 * @author maxjcs
 *
 */
public class PageUtil
{
  public static PageList createPageList(int start, int pageSize, int total, List objectList)
  {
    Paginator paginator = new Paginator();

    paginator.setItemsPerPage(pageSize);

    if (pageSize == 0)
      paginator.setPage(0);
    else if (pageSize == 1)
      paginator.setPage(start);
    else {
      paginator.setPage(start / pageSize + 1);
    }

    paginator.setItemsPerPage(pageSize);
    paginator.setItems(total);

    PageList pageList = new PageList();

    pageList.setPaginator(paginator);

    if ((paginator.getItems() > 0) && (objectList != null) && (objectList.size() > 0)) {
      pageList.addAll(objectList);
    }

    return pageList;
  }

  public static int adjustPage(int total, int pageSize, int iPage)
  {
    Paginator paginator = new Paginator(pageSize, total);
    paginator.setPage(iPage);
    return paginator.getPage();
  }

  public static int adjustStartNum(int total, int pageSize, int startNum)
  {
    Paginator paginator = new Paginator(pageSize, total);
    paginator.setItem(startNum);
    return paginator.getBeginIndex();
  }
}
