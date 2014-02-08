package com.hlx.juinfo.dal.biz.dal.dao.impl;

import com.hlx.juinfo.dal.biz.dal.dao.MoviePinDAO;
import com.hlx.juinfo.dal.biz.dal.dataobject.MoviePinDO;
import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 * 数据访问对象实现类
 * @since 2013-01-17
 */
public class MoviePinDAOImpl extends SqlMapClientDaoSupport implements MoviePinDAO {

    /**
     * 插入数据
     * @param moviePinDO
     * @return 插入数据的主键
     */
    public Integer insertMoviePinDO(MoviePinDO moviePinDO) throws DataAccessException {
        Object id = getSqlMapClientTemplate().insert("MoviePin.insert", moviePinDO);
        return (Integer) id;
    }

    /**
     * 统计记录数
     * @param moviePinDO
     * @return 查出的记录数
     */
    public Integer countMoviePinDOByExample(MoviePinDO moviePinDO) throws DataAccessException {
        Integer count = (Integer) getSqlMapClientTemplate().queryForObject("MoviePin.countByDOExample", moviePinDO);
        return count;
    }

    /**
     * 更新记录
     * @param moviePinDO
     * @return 受影响的行数
     */
    public Integer updateMoviePinDO(MoviePinDO moviePinDO) throws DataAccessException {
        int result = getSqlMapClientTemplate().update("MoviePin.update", moviePinDO);
        return result;
    }

    /**
     * 获取对象列表
     * @param moviePinDO
     * @return 对象列表
     */
    @SuppressWarnings("unchecked")
    public List<MoviePinDO> findListByExample(MoviePinDO moviePinDO) throws DataAccessException {
        List<MoviePinDO> list = getSqlMapClientTemplate().queryForList("MoviePin.findListByDO", moviePinDO);
        return list;
    }

    /**
     * 根据主键获取moviePinDO
     * @param id
     * @return moviePinDO
     */
    public MoviePinDO findMoviePinDOByPrimaryKey(Integer id) throws DataAccessException {
        MoviePinDO moviePinDO = (MoviePinDO) getSqlMapClientTemplate().queryForObject("MoviePin.findByPrimaryKey", id);
        return moviePinDO;
    }

    /**
     * 删除记录
     * @param id
     * @return 受影响的行数
     */
    public Integer deleteMoviePinDOByPrimaryKey(Integer id) throws DataAccessException {
        Integer rows = (Integer) getSqlMapClientTemplate().delete("MoviePin.deleteByPrimaryKey", id);
        return rows;
    }

}