/*
 * Copyright 2012 zgnet.com All right reserved. This software is the
 * confidential and proprietary information of zgnet.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with zgnet.com.
 */
package com.hxl.juinfo.web.home.moudle.action;

import javax.annotation.Resource;

import com.alibaba.citrus.turbine.Context;
import com.alibaba.citrus.turbine.Navigator;
import com.alibaba.citrus.turbine.TurbineRunData;
import com.alibaba.citrus.turbine.dataresolver.FormGroup;
import com.alibaba.citrus.turbine.dataresolver.Param;
import com.alibaba.common.logging.Logger;
import com.alibaba.common.logging.LoggerFactory;
import com.hlx.juinfo.common.constants.Constants;
import com.hlx.juinfo.common.service.MovieService;
import com.hlx.juinfo.dal.biz.dal.dataobject.MovieDO;

/**
 * 类MovieAction.java的实现描述：视频action
 * 
 * @author maxjcs 2012-9-5 下午3:12:01
 */
public class MovieAction {

    private static final Logger logger = LoggerFactory.getLogger(MovieAction.class);

    @Resource
    MovieService                 movieService;

    public void doCreate(@FormGroup(name = "createMovieForm") MovieDO movieDO, Navigator nav, Context context,
                         TurbineRunData rundata) {
        movieDO.setFlag(Constants.FLAG_B);// 审核通过
        Integer id = movieService.insert(movieDO);
        if (id > 0) {
            context.put("isSuccess", Boolean.TRUE);
        }
    }
    
    public void doUpdate(@FormGroup(name = "createMovieForm") MovieDO movieDO, Navigator nav, Context context,
                         TurbineRunData rundata) {
        Integer id = movieService.update(movieDO);
        if (id > 0) {
            context.put("isSuccess", Boolean.TRUE);
        }
    }
    
//    /**
//     * 下架商品
//     * @param itemBatchDO
//     * @param nav
//     * @param context
//     * @param rundata
//     */
//    public void doDownitem(@Param(name="id") Integer id, Navigator nav, Context context,
//                         TurbineRunData rundata) {
//        ItemDO itemDO=new ItemDO();
//        itemDO.setId(id);
//        itemDO.setItemStatus(Constants.ITEM_STATUS_DOWN);
//        Integer num= itemService.update(itemDO);
//        if (num > 0) {
//            context.put("isSuccess", Boolean.TRUE);
//        }
//    }
//    
//    /**
//     * 上架商品
//     * @param itemBatchDO
//     * @param nav
//     * @param context
//     * @param rundata
//     */
//    public void doUpitem(@FormGroup(name = "createItemBatchForm") ItemBatchDO itemBatchDO, Navigator nav, Context context,
//                         TurbineRunData rundata) {
//       Integer batchNum= itemBatchService.getMaxBatchByItemId(itemBatchDO.getItemId());
//       itemBatchDO.setBatchNum(batchNum==null?1:batchNum+1);
//       itemBatchDO.setrStatus(Constants.ITEM_BATCH_STATUS_1);//正常
//        
//        Integer id = itemBatchService.insert(itemBatchDO);
//        if (id > 0) {
//            context.put("upItemSuccess", Boolean.TRUE);
//        }
//    }

}
