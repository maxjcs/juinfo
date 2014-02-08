package com.hlx.juinfo.dal.biz.dal.dao;

import com.hlx.juinfo.dal.biz.dal.dataobject.MoviePinDO;
import java.util.List;
import org.springframework.dao.DataAccessException;

/**
 * 数据访问对象接口
 * @since 2013-01-17
 */
public interface MoviePinDAO {

    /**
     * 插入数据
     * @param moviePinDO
     * @return 插入数据的主键
     */
    public Integer insertMoviePinDO(MoviePinDO moviePinDO) throws DataAccessException;

    /**
     * 统计记录数
     * @param moviePinDO
     * @return 查出的记录数
     */
    public Integer countMoviePinDOByExample(MoviePinDO moviePinDO) throws DataAccessException;

    /**
     * 更新记录
     * @param moviePinDO
     * @return 受影响的行数
     */
    public Integer updateMoviePinDO(MoviePinDO moviePinDO) throws DataAccessException;

    /**
     * 获取对象列表
     * @param moviePinDO
     * @return 对象列表
     */
    public List<MoviePinDO> findListByExample(MoviePinDO moviePinDO) throws DataAccessException;

    /**
     * 根据主键获取moviePinDO
     * @param id
     * @return moviePinDO
     */
    public MoviePinDO findMoviePinDOByPrimaryKey(Integer id) throws DataAccessException;

    /**
     * 删除记录
     * @param id
     * @return 受影响的行数
     */
    public Integer deleteMoviePinDOByPrimaryKey(Integer id) throws DataAccessException;

}