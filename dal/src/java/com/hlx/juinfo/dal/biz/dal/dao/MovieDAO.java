package com.hlx.juinfo.dal.biz.dal.dao;

import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;

import com.hlx.juinfo.dal.biz.dal.dataobject.MovieDO;

/**
 * 数据访问对象接口
 * @since 2013-01-27
 */
public interface MovieDAO {

    /**
     * 插入数据
     * @param movieDO
     * @return 插入数据的主键
     */
    public Integer insertMovieDO(MovieDO movieDO) throws DataAccessException;

    /**
     * 统计记录数
     * @param movieDO
     * @return 查出的记录数
     */
    public Integer countMovieDOByExample(MovieDO movieDO) throws DataAccessException;

    /**
     * 更新记录
     * @param movieDO
     * @return 受影响的行数
     */
    public Integer updateMovieDO(MovieDO movieDO) throws DataAccessException;

    /**
     * 获取对象列表
     * @param movieDO
     * @return 对象列表
     */
    public List<MovieDO> findListByExample(MovieDO movieDO) throws DataAccessException;

    /**
     * 根据主键获取movieDO
     * @param id
     * @return movieDO
     */
    public MovieDO findMovieDOByPrimaryKey(Integer id) throws DataAccessException;

    /**
     * 删除记录
     * @param id
     * @return 受影响的行数
     */
    public Integer deleteMovieDOByPrimaryKey(Integer id) throws DataAccessException;
    
    /**
     * 查询记录
     * @param paramMap
     * @return
     * @throws DataAccessException
     */
    public List<MovieDO> queryByParam( Map paramMap) throws DataAccessException;
    
    /**
     * count查询记录
     * @param paramMap
     * @return
     * @throws DataAccessException
     */
    public Integer countByParam( Map paramMap) throws DataAccessException;

}